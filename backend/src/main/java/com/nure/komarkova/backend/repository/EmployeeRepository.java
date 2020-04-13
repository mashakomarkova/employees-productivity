package com.nure.komarkova.backend.repository;

import com.nure.komarkova.backend.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
