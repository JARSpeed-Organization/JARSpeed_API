package com.jarspeed.api.sex;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SexRepository extends JpaRepository<Sex, Integer> {
    // Ici, vous pouvez ajouter des méthodes personnalisées si nécessaire
    // Par exemple, trouver des sexes par label, etc.
}

