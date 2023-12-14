package com.jarspeed.api.sex;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/sexes")
public class SexController {

    @Autowired
    private SexRepository sexRepository;

    // Get tous les sexes
    @GetMapping
    public List<Sex> getAllSexes() {
        return sexRepository.findAll();
    }

    // Get un sexe par ID
    @GetMapping("/{id}")
    public Sex getSexById(@PathVariable int id) {
        return sexRepository.findById(id).orElse(null);
    }

    // Ajouter un nouveau sexe
    @PostMapping
    public Sex createSex(@RequestBody Sex sex) {
        return sexRepository.save(sex);
    }

    // Mettre à jour un sexe
    @PutMapping("/{id}")
    public Sex updateSex(@PathVariable int id, @RequestBody Sex sexDetails) {
        Sex sex = sexRepository.findById(id).orElse(null);
        if (sex != null) {
            sex.setLabel(sexDetails.getLabel());
            return sexRepository.save(sex);
        }
        return null;
    }

    // Supprimer un sexe
    @DeleteMapping("/{id}")
    public void deleteSex(@PathVariable int id) {
        sexRepository.deleteById(id);
    }
}
