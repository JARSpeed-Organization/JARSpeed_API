package com.jarspeed.api.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * Repository interface for the User entity.
 * <p>
 * This interface extends JpaRepository, providing CRUD methods for the User
 * entity.
 * the User entity. It can also be used to define custom query
 * query methods.
 * </p>
 */

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    // Ici, vous pouvez ajouter des méthodes personnalisées si nécessaire
    // Par exemple, trouver des utilisateurs par nom, âge, etc.
}
