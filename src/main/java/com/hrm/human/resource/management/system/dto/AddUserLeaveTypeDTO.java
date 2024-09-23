package com.hrm.human.resource.management.system.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddUserLeaveTypeDTO {
    private Long employeeId;
    private Long leaveId;
    private Integer noOfLeaves;
}
