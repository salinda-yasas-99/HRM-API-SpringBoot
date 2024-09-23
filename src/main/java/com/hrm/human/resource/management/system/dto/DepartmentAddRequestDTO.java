package com.hrm.human.resource.management.system.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DepartmentAddRequestDTO {
    private String departmentName;
    private String departmentDesc;
    private Long departmentHeadId;
}
