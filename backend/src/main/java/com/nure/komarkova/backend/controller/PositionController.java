package com.nure.komarkova.backend.controller;

import com.google.gson.Gson;
import com.nure.komarkova.backend.entity.Position;
import com.nure.komarkova.backend.service.PositionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PositionController {

    private PositionService positionService;

    public PositionController(PositionService positionService) {
        this.positionService = positionService;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/createPosition")
    public HttpStatus create(@RequestBody String data) {
        Position position = new Gson().fromJson(data, Position.class);
        positionService.createPosition(position);
        return HttpStatus.OK;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/allPositions")
    public ResponseEntity<List<Position>> viewAllPositions() {
        return new ResponseEntity<>(positionService.findAllPositions(), HttpStatus.OK);
    }
}
