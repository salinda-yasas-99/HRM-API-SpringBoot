package com.hrm.human.resource.management.system.controller;

import com.hrm.human.resource.management.system.dto.EducationalQualificationDTO;
import com.hrm.human.resource.management.system.entity.ResponseMessage;
import com.hrm.human.resource.management.system.service.EducationalQualificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/qualification")
@RequiredArgsConstructor
public class EducationalQualificationController {

    private final EducationalQualificationService qualificationService;

    @PostMapping
    public ResponseEntity<ResponseMessage> addQualification(@RequestBody EducationalQualificationDTO qualificationDTO) {
        return ResponseEntity.ok(qualificationService.addQualification(qualificationDTO));
    }

    @DeleteMapping("/{qualificationId}")
    public ResponseEntity<ResponseMessage> deleteQualification(@PathVariable Long qualificationId) {
        return ResponseEntity.ok(qualificationService.deleteQualification(qualificationId));
    }

    @PutMapping
    public ResponseEntity<ResponseMessage> updateQualification(@RequestBody EducationalQualificationDTO qualificationDTO) {
        return ResponseEntity.ok(qualificationService.updateQualification(qualificationDTO));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<EducationalQualificationDTO>> getAllQualificationsByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(qualificationService.getAllQualificationsByUserId(userId));
    }
}
