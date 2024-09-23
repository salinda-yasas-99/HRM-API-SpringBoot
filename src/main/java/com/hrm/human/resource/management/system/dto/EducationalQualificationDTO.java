package com.hrm.human.resource.management.system.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EducationalQualificationDTO {
    private Long qualificationId;
    private String courseName;
    private String qualificationDesc;
    private Integer year;
    private String instituteName;
    private Long employeeId;
}
