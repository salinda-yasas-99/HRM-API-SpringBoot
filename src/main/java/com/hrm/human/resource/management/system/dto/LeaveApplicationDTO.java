package com.hrm.human.resource.management.system.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hrm.human.resource.management.system.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LeaveApplicationDTO {
    private Long employeeId;
    private String leaveTypeName;
    private Integer noOfDays;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate endDate;
    private String reason;
}
