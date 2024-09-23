package com.hrm.human.resource.management.system.auth;

import com.hrm.human.resource.management.system.dto.DepartmentDTO;
import com.hrm.human.resource.management.system.entity.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private Long employeeId;
    private String firstName;
    private String lastName;
    private String motherName;
    private String spouseName;
    private String fatherName;
    private String maritalStatus;
    private String nic;
    private String mobilePhoneNo;
    private String homePhoneNo;
    private String gender;
    private String epfNo;
    private String address;
    private LocalDate dob;
    private String workEmail;
    private String password;
    private String employmentType;
    private Date joinedDate;
    private Role role;
    private Float basicSalary;
    private Long departmentId;
    private Long positionId;
}
