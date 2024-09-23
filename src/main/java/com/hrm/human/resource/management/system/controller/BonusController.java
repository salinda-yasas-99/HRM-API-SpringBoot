package com.hrm.human.resource.management.system.controller;

import com.hrm.human.resource.management.system.dto.BonusAddRequestDTO;
import com.hrm.human.resource.management.system.dto.BonusDTO;
import com.hrm.human.resource.management.system.entity.Bonus;
import com.hrm.human.resource.management.system.exception.BonusNotFoundException;
import com.hrm.human.resource.management.system.service.BonusService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/bonus")
@RequiredArgsConstructor
public class BonusController {

    private final BonusService bonusService;

    @PostMapping
    public ResponseEntity<Bonus> addBonus(@RequestBody BonusAddRequestDTO bonusRequestDTO) {
        try {
            Bonus bonus = bonusService.addBonusType(bonusRequestDTO);
            return new ResponseEntity<>(bonus, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<BonusDTO>> getAllBonuses() {
        try {
            List<BonusDTO> bonuses = bonusService.getAllBonuses();
            return new ResponseEntity<>(bonuses, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ExceptionHandler(BonusNotFoundException.class)
    public ResponseEntity<String> handleBonusNotFoundException(BonusNotFoundException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
