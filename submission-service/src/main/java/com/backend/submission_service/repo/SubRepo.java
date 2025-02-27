package com.backend.submission_service.repo;

import com.backend.submission_service.entity.Submission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubRepo extends JpaRepository<Submission,Long> {
    List<Submission> findByTaskId(Long taskid);
    List<Submission> findByUserId(Long userid);
}
