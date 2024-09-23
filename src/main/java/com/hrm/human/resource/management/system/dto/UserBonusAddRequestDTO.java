package com.hrm.human.resource.management.system.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserBonusAddRequestDTO {
    private Long bonusId;
    private Long employeeId;
    private String month;
    private Float bonusAmount;
}
