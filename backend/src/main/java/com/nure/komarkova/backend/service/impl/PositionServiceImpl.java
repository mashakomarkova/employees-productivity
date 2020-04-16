package com.nure.komarkova.backend.service.impl;

import com.nure.komarkova.backend.entity.Position;
import com.nure.komarkova.backend.repository.PositionRepository;
import com.nure.komarkova.backend.service.PositionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PositionServiceImpl implements PositionService {

    private PositionRepository positionRepository;

    public PositionServiceImpl(PositionRepository positionRepository) {
        this.positionRepository = positionRepository;
    }

    @Override
    public void createPosition(Position position) {
        positionRepository.save(position);
    }

    @Override
    public List<Position> findAllPositions() {
        return positionRepository.findAll();
    }
}
