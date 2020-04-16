package com.nure.komarkova.backend.service;

import com.nure.komarkova.backend.entity.Position;

import java.util.List;

public interface PositionService {

    void createPosition(Position position);

    List<Position> findAllPositions();
}
