package com.hrm.human.resource.management.system.controller;


import com.hrm.human.resource.management.system.dto.PositionRequestDTO;
import com.hrm.human.resource.management.system.entity.Position;
import com.hrm.human.resource.management.system.entity.ResponseMessage;
import com.hrm.human.resource.management.system.service.PositionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/position")
@RequiredArgsConstructor
public class PositionController {

    private final PositionService positionService;

    @PostMapping("/add")
    public ResponseEntity<ResponseMessage> addPosition(@RequestBody PositionRequestDTO positionRequestDTO) {
        ResponseMessage responseMessage = positionService.addPosition(positionRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseMessage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPositionById(@PathVariable Long id) {
        try {
            Position position = positionService.getPositionById(id);
            if (position != null) {
                return ResponseEntity.ok(position);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseMessage.builder()
                        .message("Position not found with id: " + id)
                        .build());
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseMessage.builder()
                    .message("Failed to retrieve position: " + e.getMessage())
                    .build());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Position>> getAllPositions() {
        List<Position> positions = positionService.getAllPositions();
        if (!positions.isEmpty()) {
            return ResponseEntity.ok(positions);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseMessage> updatePosition(@PathVariable Long id, @RequestBody PositionRequestDTO positionRequestDTO) {
        ResponseMessage responseMessage = positionService.updatePosition(id, positionRequestDTO);
        return ResponseEntity.ok(responseMessage);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseMessage> deletePosition(@PathVariable Long id) {
        ResponseMessage responseMessage = positionService.deletePosition(id);
        return ResponseEntity.ok(responseMessage);
    }
}
