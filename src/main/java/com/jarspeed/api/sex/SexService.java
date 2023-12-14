package com.jarspeed.api.sex;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class SexService {

    @Autowired
    private SexRepository sexRepository;

    // Récupérer tous les sexes
    public List<Sex> getAllSexes() {
        return sexRepository.findAll();
    }

    // Récupérer un sexe par ID
    public Optional<Sex> getSexById(int id) {
        return sexRepository.findById(id);
    }

    // Créer ou mettre à jour un sexe
    public Sex saveOrUpdateSex(Sex sex) {
        return sexRepository.save(sex);
    }

    // Supprimer un sexe
    public void deleteSex(int id) {
        sexRepository.deleteById(id);
    }

    // Autres méthodes métier si nécessaire
}
