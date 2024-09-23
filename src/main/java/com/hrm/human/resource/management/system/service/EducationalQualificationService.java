package com.hrm.human.resource.management.system.service;

import com.hrm.human.resource.management.system.dto.EducationalQualificationDTO;
import com.hrm.human.resource.management.system.entity.EducationalQualification;
import com.hrm.human.resource.management.system.entity.ResponseMessage;
import com.hrm.human.resource.management.system.entity.User;
import com.hrm.human.resource.management.system.repository.EducationalQualificationRepository;
import com.hrm.human.resource.management.system.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EducationalQualificationService {
    private final EducationalQualificationRepository qualificationRepository;
    private final UserRepository userRepository;

    @Transactional
    public ResponseMessage addQualification(EducationalQualificationDTO qualificationDTO) {
        try {
            User user = userRepository.findById(qualificationDTO.getEmployeeId())
                    .orElseThrow(() -> new RuntimeException("User not found"));

            EducationalQualification qualification = EducationalQualification.builder()
                    .courseName(qualificationDTO.getCourseName())
                    .qualificationDesc(qualificationDTO.getQualificationDesc())
                    .year(qualificationDTO.getYear())
                    .instituteName(qualificationDTO.getInstituteName())
                    .employee(user)
                    .build();

            qualificationRepository.save(qualification);

            return ResponseMessage.builder()
                    .message("Qualification added successfully")
                    .build();
        } catch (Exception e) {
            return ResponseMessage.builder()
                    .message("Failed to add qualification: " + e.getMessage())
                    .build();
        }
    }

    @Transactional
    public ResponseMessage deleteQualification(Long qualificationId) {
        try {
            qualificationRepository.deleteById(qualificationId);
            return ResponseMessage.builder()
                    .message("Qualification deleted successfully")
                    .build();
        } catch (Exception e) {
            return ResponseMessage.builder()
                    .message("Failed to delete qualification: " + e.getMessage())
                    .build();
        }
    }

    @Transactional
    public ResponseMessage updateQualification(EducationalQualificationDTO qualificationDTO) {
        try {
            EducationalQualification qualification = qualificationRepository.findById(qualificationDTO.getQualificationId())
                    .orElseThrow(() -> new RuntimeException("Qualification not found"));

            qualification.setCourseName(qualificationDTO.getCourseName());
            qualification.setQualificationDesc(qualificationDTO.getQualificationDesc());
            qualification.setYear(qualificationDTO.getYear());
            qualification.setInstituteName(qualificationDTO.getInstituteName());

            qualificationRepository.save(qualification);

            return ResponseMessage.builder()
                    .message("Qualification updated successfully")
                    .build();
        } catch (Exception e) {
            return ResponseMessage.builder()
                    .message("Failed to update qualification: " + e.getMessage())
                    .build();
        }
    }

    @Transactional(readOnly = true)
    public List<EducationalQualificationDTO> getAllQualificationsByUserId(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return user.getEducationalQualifications().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private EducationalQualificationDTO convertToDTO(EducationalQualification qualification) {
        return new EducationalQualificationDTO(
                qualification.getQualificationId(),
                qualification.getCourseName(),
                qualification.getQualificationDesc(),
                qualification.getYear(),
                qualification.getInstituteName(),
                qualification.getEmployee().getEmployeeId()
        );
    }
}
