package com.hrm.human.resource.management.system.service;

import com.hrm.human.resource.management.system.dto.PositionRequestDTO;
import com.hrm.human.resource.management.system.entity.Position;
import com.hrm.human.resource.management.system.entity.ResponseMessage;
import com.hrm.human.resource.management.system.repository.PositionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PositionService {

    private final PositionRepository positionRepository;


    @Transactional
    public ResponseMessage addPosition(PositionRequestDTO positionRequestDTO) {
        try {
            Position position = Position.builder()
                    .positionName(positionRequestDTO.getPositionName())
                    .positionDesc(positionRequestDTO.getPositionDesc())
                    .build();

            positionRepository.save(position);
            return ResponseMessage.builder()
                    .message("Position is created successfully")
                    .build();
        } catch (Exception e) {
            return ResponseMessage.builder()
                    .message("Position creation failed: " + e.getMessage())
                    .build();
        }
    }

    @Transactional(readOnly = true)
    public Position getPositionById(Long id) {
        return positionRepository.findById(id).orElse(null);
    }

    @Transactional(readOnly = true)
    public List<Position> getAllPositions() {
        return positionRepository.findAll();
    }

    @Transactional
    public ResponseMessage updatePosition(Long id, PositionRequestDTO positionRequestDTO) {
        try {
            Position position = positionRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Position not found with id: " + id));

            position.setPositionName(positionRequestDTO.getPositionName());
            position.setPositionDesc(positionRequestDTO.getPositionDesc());

            positionRepository.save(position);
            return ResponseMessage.builder()
                    .message("Position updated successfully")
                    .build();
        } catch (Exception e) {
            return ResponseMessage.builder()
                    .message("Failed to update position: " + e.getMessage())
                    .build();
        }
    }

    @Transactional
    public ResponseMessage deletePosition(Long id) {
        try {
            positionRepository.deleteById(id);
            return ResponseMessage.builder()
                    .message("Position deleted successfully")
                    .build();
        } catch (Exception e) {
            return ResponseMessage.builder()
                    .message("Failed to delete position: " + e.getMessage())
                    .build();
        }
    }
}
