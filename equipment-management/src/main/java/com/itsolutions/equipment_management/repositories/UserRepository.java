package com.itsolutions.equipment_management.repositories;

import com.itsolutions.equipment_management.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
