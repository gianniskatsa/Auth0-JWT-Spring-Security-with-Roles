package com.implementingRolesWithAuth0JWT.repository;

import com.implementingRolesWithAuth0JWT.models.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationUserRepository extends JpaRepository<ApplicationUser, Long> {
    ApplicationUser findByUsername(String username);
}