package com.nure.komarkova.backend.repository;

import com.nure.komarkova.backend.entity.Position;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PositionRepository extends JpaRepository<Position, Long> {
}
