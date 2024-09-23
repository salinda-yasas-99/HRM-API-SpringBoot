package com.hrm.human.resource.management.system.config;

import com.hrm.human.resource.management.system.entity.*;
import com.hrm.human.resource.management.system.repository.DepartmentRepository;
import com.hrm.human.resource.management.system.repository.LeaveRepository;
import com.hrm.human.resource.management.system.repository.PositionRepository;
import com.hrm.human.resource.management.system.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;

@Configuration
@RequiredArgsConstructor
public class DataInitializer {

    private final UserRepository userRepository;
    private final DepartmentRepository departmentRepository;
    private final PositionRepository positionRepository;

    private final LeaveRepository leaveRepository;

//    private final UserLeave userLeave;
//
//    private final WorkExperience workExperience;


    @Bean
    public ApplicationRunner initializer() {
        return args -> {
            if (positionRepository.count() == 0) {
                Position developer = Position.builder()
                        .positionName("Developer")
                        .positionDesc("Software Developer")
                        .build();
                Position manager = Position.builder()
                        .positionName("Manager")
                        .positionDesc("Project Manager")
                        .build();
                Position analyst = Position.builder()
                        .positionName("Analyst")
                        .positionDesc("Business Analyst")
                        .build();
                Position tester = Position.builder()
                        .positionName("Tester")
                        .positionDesc("Quality Assurance")
                        .build();

                positionRepository.saveAll(Arrays.asList(developer, manager, analyst, tester));
            }

            if(leaveRepository.count() ==0){
                Leave leave1 = Leave.builder()
                        .leaveTypeName("Annual")
                        .noOfLeaves(14)
                        .build();
                Leave leave2 = Leave.builder()
                        .leaveTypeName("Casual")
                        .noOfLeaves(7)
                        .build();
                Leave leave3 = Leave.builder()
                        .leaveTypeName("Medical")
                        .noOfLeaves(7)
                        .build();

                leaveRepository.saveAll(Arrays.asList(leave1, leave2, leave3));

            }

            if (departmentRepository.count() == 0) {
                Department it = Department.builder()
                        .departmentName("IT")
                        .departmentDesc("Information Technology")
                        .build();
                Department hr = Department.builder()
                        .departmentName("HR")
                        .departmentDesc("Human Resources")
                        .build();
                Department finance = Department.builder()
                        .departmentName("Finance")
                        .departmentDesc("Finance Department")
                        .build();

                departmentRepository.saveAll(Arrays.asList(it, hr, finance));
            }

            if (userRepository.count() == 0) {
                Department it = departmentRepository.findByDepartmentName("IT").get();
                Department hr = departmentRepository.findByDepartmentName("HR").get();
                Department finance = departmentRepository.findByDepartmentName("Finance").get();

                Position developer = positionRepository.findByPositionName("Developer").get();
                Position manager = positionRepository.findByPositionName("Manager").get();
                Position analyst = positionRepository.findByPositionName("Analyst").get();
                Position tester = positionRepository.findByPositionName("Tester").get();

                User user1 = User.builder()
                        .firstName("test")
                        .lastName("admin")
                        .motherName("Jane Doe")
                        .spouseName("Emily Doe")
                        .fatherName("Robert Doe")
                        .maritalStatus("Married")
                        .nic("123456789V")
                        .mobilePhoneNo("0771234567")
                        .homePhoneNo("0111234567")
                        .gender("Male")
                        .epfNo("EPF123")
                        .address("123, Elm Street")
                        .dob(LocalDate.of(1990, 1, 1))
                        .workEmail("testAdmin@example.com")
                        .password("$2a$10$k3lio4hNfZJXfx7EDgwM2OCRtTfQ2krqc4RPgNpmEM0IUH/ASYUnK")
                        .age(34)
                        .employmentType("Full-time")
                        .joinedDate(new Date())
                        .basicSalary(60000.00f)
                        .role(Role.ADMIN)
                        .department(it)
                        .position(developer)
                        .build();

                User user2 = User.builder()
                        .firstName("test")
                        .lastName("user")
                        .motherName("Alice Smith")
                        .spouseName("None")
                        .fatherName("Peter Smith")
                        .maritalStatus("Single")
                        .nic("987654321V")
                        .mobilePhoneNo("0779876543")
                        .homePhoneNo("0119876543")
                        .gender("Female")
                        .epfNo("EPF124")
                        .address("456, Maple Street")
                        .dob(LocalDate.of(1985, 2, 2))
                        .workEmail("testUser@example.com")
                        .password("$2a$10$k3lio4hNfZJXfx7EDgwM2OCRtTfQ2krqc4RPgNpmEM0IUH/ASYUnK")
                        .age(39)
                        .employmentType("Full-time")
                        .joinedDate(new Date())
                        .basicSalary(75000.00f)
                        .role(Role.EMPLOYEE)
                        .department(hr)
                        .position(manager)
                        .build();

                User user3 = User.builder()
                        .firstName("Jim")
                        .lastName("Brown")
                        .motherName("Susan Brown")
                        .spouseName("Mia Brown")
                        .fatherName("Henry Brown")
                        .maritalStatus("Married")
                        .nic("456789123V")
                        .mobilePhoneNo("0774567891")
                        .homePhoneNo("0114567891")
                        .gender("Male")
                        .epfNo("EPF125")
                        .address("789, Oak Street")
                        .dob(LocalDate.of(1988, 3, 3))
                        .workEmail("jim.brown@example.com")
                        .password("$2a$10$k3lio4hNfZJXfx7EDgwM2OCRtTfQ2krqc4RPgNpmEM0IUH/ASYUnK")
                        .age(36)
                        .employmentType("Full-time")
                        .joinedDate(new Date())
                        .basicSalary(55000.00f)
                        .role(Role.EMPLOYEE)
                        .department(finance)
                        .position(analyst)
                        .build();

                User user4 = User.builder()
                        .firstName("Sara")
                        .lastName("Lee")
                        .motherName("Lily Lee")
                        .spouseName("None")
                        .fatherName("Michael Lee")
                        .maritalStatus("Single")
                        .nic("321654987V")
                        .mobilePhoneNo("0773216549")
                        .homePhoneNo("0113216549")
                        .gender("Female")
                        .epfNo("EPF126")
                        .address("321, Pine Street")
                        .dob(LocalDate.of(1992, 4, 4))
                        .workEmail("sara.lee@example.com")
                        .password("$2a$10$k3lio4hNfZJXfx7EDgwM2OCRtTfQ2krqc4RPgNpmEM0IUH/ASYUnK")
                        .age(32)
                        .employmentType("Part-time")
                        .joinedDate(new Date())
                        .basicSalary(45000.00f)
                        .role(Role.EMPLOYEE)
                        .department(it)
                        .position(tester)
                        .build();

                User user5 = User.builder()
                        .firstName("Emily")
                        .lastName("Jones")
                        .motherName("Lucy Jones")
                        .spouseName("Jack Jones")
                        .fatherName("David Jones")
                        .maritalStatus("Married")
                        .nic("789456123V")
                        .mobilePhoneNo("0777894561")
                        .homePhoneNo("0117894561")
                        .gender("Female")
                        .epfNo("EPF127")
                        .address("654, Cedar Street")
                        .dob(LocalDate.of(1987, 5, 5))
                        .workEmail("emily.jones@example.com")
                        .password("$2a$10$k3lio4hNfZJXfx7EDgwM2OCRtTfQ2krqc4RPgNpmEM0IUH/ASYUnK")
                        .age(37)
                        .employmentType("Full-time")
                        .joinedDate(new Date())
                        .basicSalary(70000.00f)
                        .role(Role.EMPLOYEE)
                        .department(hr)
                        .position(manager)
                        .build();

                userRepository.saveAll(Arrays.asList(user1, user2, user3, user4, user5));

                it.setDepartmentHead(user4);
                hr.setDepartmentHead(user2);
                finance.setDepartmentHead(user3);

                departmentRepository.saveAll(Arrays.asList(it, hr, finance));

                developer.setUsers(Arrays.asList(user4));
                manager.setUsers(Arrays.asList(user2, user5));
                analyst.setUsers(Arrays.asList(user3));
                tester.setUsers(Arrays.asList(user4));

                positionRepository.saveAll(Arrays.asList(developer, manager, analyst, tester));

                it.setUsers(Arrays.asList(user4));
                hr.setUsers(Arrays.asList(user2, user5));
                finance.setUsers(Arrays.asList(user3));

                departmentRepository.saveAll(Arrays.asList(it, hr, finance));
            }
        };
    }
}