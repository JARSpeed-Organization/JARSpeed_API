# Use the official Maven image with JDK 17 to create a build artifact.
FROM maven:3.8.4-openjdk-17 AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package -DskipTests

# For Spring Boot application, use an OpenJDK 17 image for runtime.
FROM openjdk:17-slim
COPY --from=build /home/app/target/jarspeed-api-*.jar /usr/local/lib/jarspeed-api.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/local/lib/jarspeed-api.jar"]
