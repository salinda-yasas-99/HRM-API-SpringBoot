package com.hrm.human.resource.management.system.service;

import com.hrm.human.resource.management.system.dto.WorkExperienceDTO;
import com.hrm.human.resource.management.system.entity.ResponseMessage;
import com.hrm.human.resource.management.system.entity.User;
import com.hrm.human.resource.management.system.entity.WorkExperience;
import com.hrm.human.resource.management.system.repository.UserRepository;
import com.hrm.human.resource.management.system.repository.WorkExperienceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WorkExperienceService {
    private final WorkExperienceRepository workExperienceRepository;
    private final UserRepository userRepository;

    @Transactional
    public ResponseMessage addExperience(WorkExperienceDTO experienceDTO) {
        try {
            User employee = userRepository.findById(experienceDTO.getEmployeeId())
                    .orElseThrow(() -> new RuntimeException("User not found"));

            WorkExperience experience = new WorkExperience();
            experience.setCompanyName(experienceDTO.getCompanyName());
            experience.setDesignation(experienceDTO.getDesignation());
            experience.setStartDate(experienceDTO.getStartDate());
            experience.setEndDate(experienceDTO.getEndDate());
            experience.setEmployee(employee);

            workExperienceRepository.save(experience);

            return ResponseMessage.builder()
                    .message("Work experience added successfully")
                    .build();
        } catch (Exception e) {
            return ResponseMessage.builder()
                    .message("Failed to add work experience: " + e.getMessage())
                    .build();
        }
    }

    @Transactional
    public ResponseMessage deleteExperience(Long experienceId) {
        try {
            workExperienceRepository.deleteById(experienceId);
            return ResponseMessage.builder()
                    .message("Work experience deleted successfully")
                    .build();
        } catch (Exception e) {
            return ResponseMessage.builder()
                    .message("Failed to delete work experience: " + e.getMessage())
                    .build();
        }
    }

    @Transactional
    public ResponseMessage updateExperience(WorkExperienceDTO experienceDTO) {
        try {
            WorkExperience experience = workExperienceRepository.findById(experienceDTO.getExperienceId())
                    .orElseThrow(() -> new RuntimeException("Work experience not found"));

            experience.setCompanyName(experienceDTO.getCompanyName());
            experience.setDesignation(experienceDTO.getDesignation());
            experience.setStartDate(experienceDTO.getStartDate());
            experience.setEndDate(experienceDTO.getEndDate());

            workExperienceRepository.save(experience);

            return ResponseMessage.builder()
                    .message("Work experience updated successfully")
                    .build();
        } catch (Exception e) {
            return ResponseMessage.builder()
                    .message("Failed to update work experience: " + e.getMessage())
                    .build();
        }
    }

    @Transactional(readOnly = true)
    public List<WorkExperienceDTO> getAllExperiencesByUserId(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return user.getWorkExperiences().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private WorkExperienceDTO convertToDTO(WorkExperience experience) {
        return new WorkExperienceDTO(
                experience.getExperienceId(),
                experience.getCompanyName(),
                experience.getDesignation(),
                experience.getStartDate(),
                experience.getEndDate(),
                experience.getEmployee().getEmployeeId()
        );
    }
}
