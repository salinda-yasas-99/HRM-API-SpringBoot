package com.hrm.human.resource.management.system.controller;

import com.hrm.human.resource.management.system.dto.WorkExperienceDTO;
import com.hrm.human.resource.management.system.entity.ResponseMessage;
import com.hrm.human.resource.management.system.service.WorkExperienceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/work-experiences")
@RequiredArgsConstructor
public class WorkExperienceController {
    private final WorkExperienceService workExperienceService;

    @PostMapping
    public ResponseEntity<ResponseMessage> addExperience(@RequestBody WorkExperienceDTO experienceDTO) {
        return ResponseEntity.ok(workExperienceService.addExperience(experienceDTO));
    }

    @DeleteMapping("/{experienceId}")
    public ResponseEntity<ResponseMessage> deleteExperience(@PathVariable Long experienceId) {
        return ResponseEntity.ok(workExperienceService.deleteExperience(experienceId));
    }

    @PutMapping
    public ResponseEntity<ResponseMessage> updateExperience(@RequestBody WorkExperienceDTO experienceDTO) {
        return ResponseEntity.ok(workExperienceService.updateExperience(experienceDTO));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<WorkExperienceDTO>> getAllExperiencesByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(workExperienceService.getAllExperiencesByUserId(userId));
    }
}
