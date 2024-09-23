package com.hrm.human.resource.management.system.dto;

import java.time.LocalDate;
import java.util.Date;

import com.hrm.human.resource.management.system.entity.Department;
import com.hrm.human.resource.management.system.entity.Position;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
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
    private String dob;
    private String workEmail;
    private Integer age;
    private String employmentType;
    private Date joinedDate;
    private Float basicSalary;
    private String departmentName;
    private String positionName;
}
