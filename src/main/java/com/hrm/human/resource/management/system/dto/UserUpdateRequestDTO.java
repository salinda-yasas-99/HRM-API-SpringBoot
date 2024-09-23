package com.hrm.human.resource.management.system.dto;

import com.hrm.human.resource.management.system.entity.Role;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class UserUpdateRequestDTO {
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
    private Float basicSalary;
    private Role role;
    private Long departmentId;
    private Long positionId;
}
