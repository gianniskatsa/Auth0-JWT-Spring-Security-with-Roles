package com.implementingRolesWithAuth0JWT.repository;

import com.implementingRolesWithAuth0JWT.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}