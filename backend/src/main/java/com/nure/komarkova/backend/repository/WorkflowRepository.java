package com.nure.komarkova.backend.repository;

import com.nure.komarkova.backend.entity.Workflow;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkflowRepository extends JpaRepository<Workflow, Long> {
}
