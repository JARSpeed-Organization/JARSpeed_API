package com.jarspeed.api.route;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jarspeed.api.security.TokenService;
import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class RouteControllerTest {

    @Mock
    private TokenService tokenService;

    @Mock
    private RouteService routeService;

    @InjectMocks
    private RouteController routeController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(routeController).build();
    }

    @Test
    public void getAllRoutesUnauthorizedTest() throws Exception {
        HttpServletRequest mockRequest = mock(HttpServletRequest.class);
        when(mockRequest.getHeader("Authorization")).thenReturn("Bearer invalidToken");
        when(tokenService.validateToken("invalidToken")).thenReturn(false);

        ResponseEntity<?> response = routeController.getAllRoutes(mockRequest);

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertEquals("Unauthorized access", response.getBody());
    }

    @Test
    public void getAllRoutesAuthorizedTest() throws Exception {
        // Simule un token valide
        given(tokenService.validateToken(any())).willReturn(true);
        given(tokenService.getUserIdFromToken(any())).willReturn(4); // Supposons que l'ID de l'utilisateur soit 4
        List<Route> routes = Arrays.asList(new Route(), new Route());
        given(routeService.getAllRoutesByUserId("4")).willReturn(routes);

        mockMvc.perform(get("/routes")
                        .header("Authorization", "Bearer valid_token")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void getRouteByIdTest() throws Exception {
        String routeId = "route1";
        Route route = new Route();
        route.setId(routeId);
        given(routeService.getRouteById(routeId)).willReturn(route);

        mockMvc.perform(get("/routes/{id}", routeId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(routeId)));
    }

    @Test
    public void createRouteTest() throws Exception {
        Route route = new Route();
        route.setId("route1");
        route.setTitle("Test Route");
        given(routeService.createRoute(any(Route.class))).willReturn(route);

        mockMvc.perform(post("/routes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(route)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is("Test Route")));
    }

    @Test
    public void updateRouteTest() throws Exception {
        String routeId = "route1";
        Route updatedRoute = new Route();
        updatedRoute.setId(routeId);
        updatedRoute.setTitle("Updated Title");
        given(routeService.updateRoute(eq(routeId), any(Route.class))).willReturn(updatedRoute);

        mockMvc.perform(put("/routes/{id}", routeId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(updatedRoute)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is("Updated Title")));

        verify(routeService).updateRoute(eq(routeId), any(Route.class));
    }

    @Test
    public void deleteRouteTest() throws Exception {
        String routeId = "route1";
        doNothing().when(routeService).deleteRoute(routeId);

        mockMvc.perform(delete("/routes/{id}", routeId))
                .andExpect(status().isOk());

        verify(routeService).deleteRoute(routeId);
    }

    // Utility method to convert object into JSON string
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
