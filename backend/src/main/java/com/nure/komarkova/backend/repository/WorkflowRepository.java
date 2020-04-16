package com.nure.komarkova.backend.repository;

import com.nure.komarkova.backend.entity.Workflow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.util.List;

public interface WorkflowRepository extends JpaRepository<Workflow, Long> {

    List<Workflow> findByWorkDate(Date workDate);

    List<Workflow> findByEmployee_Id(Long id);
}
