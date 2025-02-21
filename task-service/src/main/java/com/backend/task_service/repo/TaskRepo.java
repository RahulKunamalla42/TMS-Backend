package com.backend.task_service.repo;

import com.backend.task_service.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepo extends JpaRepository<Task,Long> {
    Optional<List<Task>> findByAsignedUserId(Long id);

}
