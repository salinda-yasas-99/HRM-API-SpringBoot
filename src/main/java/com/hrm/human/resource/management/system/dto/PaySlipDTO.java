package com.hrm.human.resource.management.system.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaySlipDTO {

    private Long paySlipId;
    private Long employeeId;
    private Float grossAmount;
    private Float netAmount;
    private Float basicAmount;
    private Float totalBonus;
    private Float employeeEpfAmount;
    private Float employerEpfAmount;
    private Float employerEtfAmount;
    private Date payDate;
    private Date issuedDate;
    private String month;
}
