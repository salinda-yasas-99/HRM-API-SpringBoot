package com.hrm.human.resource.management.system.service;

import com.hrm.human.resource.management.system.dto.DepartmentDTO;
import com.hrm.human.resource.management.system.dto.PositionDTO;
import com.hrm.human.resource.management.system.dto.UserDTO;
import com.hrm.human.resource.management.system.dto.UserUpdateRequestDTO;
import com.hrm.human.resource.management.system.entity.Department;
import com.hrm.human.resource.management.system.entity.Position;
import com.hrm.human.resource.management.system.entity.ResponseMessage;
import com.hrm.human.resource.management.system.entity.User;
import com.hrm.human.resource.management.system.repository.DepartmentRepository;
import com.hrm.human.resource.management.system.repository.PositionRepository;
import com.hrm.human.resource.management.system.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final DepartmentRepository departmentRepository;

    private final PositionRepository positionRepository;


    public Optional<User> getUserById(Long employeeId) {
        return userRepository.findById(employeeId);
    }

    public Optional<UserDTO> getUserDetailsByIdAndRole(Long employeeId, String role) {
        Optional<User> userOptional = getUserById(employeeId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (user.getRole().name().equalsIgnoreCase(role)) {
                return Optional.of(convertToDTO(user));
            }
        }
        return Optional.empty();
    }

    public List<UserDTO> getAllEmployees() {
        return userRepository.findAll().stream()
                .filter(user -> user.getRole().name().equalsIgnoreCase("EMPLOYEE"))
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public ResponseMessage updateUser(Long employeeId, UserUpdateRequestDTO request) {
        Optional<User> userOptional = getUserById(employeeId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            updateUserData(user, request);
            userRepository.save(user);
            return ResponseMessage.builder()
                    .message("User is updated Successfully")
                    .build();
        }
        else{
            return ResponseMessage.builder()
                    .message("User is not found")
                    .build();
        }

    }

    private void updateUserData(User user, UserUpdateRequestDTO request) {
        Department department= departmentRepository.findById(request.getDepartmentId())
                .orElseThrow(() -> new IllegalArgumentException("Department not found"));
        Position position= positionRepository.findById(request.getDepartmentId())
                .orElseThrow(() -> new IllegalArgumentException("Position not found"));
        if (request.getFirstName() != null) user.setFirstName(request.getFirstName());
        if (request.getLastName() != null) user.setLastName(request.getLastName());
        if (request.getMotherName() != null) user.setMotherName(request.getMotherName());
        if (request.getSpouseName() != null) user.setSpouseName(request.getSpouseName());
        if (request.getFatherName() != null) user.setFatherName(request.getFatherName());
        if (request.getMaritalStatus() != null) user.setMaritalStatus(request.getMaritalStatus());
        if (request.getNic() != null) user.setNic(request.getNic());
        if (request.getMobilePhoneNo() != null) user.setMobilePhoneNo(request.getMobilePhoneNo());
        if (request.getHomePhoneNo() != null) user.setHomePhoneNo(request.getHomePhoneNo());
        if (request.getGender() != null) user.setGender(request.getGender());
        if (request.getEpfNo() != null) user.setEpfNo(request.getEpfNo());
        if (request.getAddress() != null) user.setAddress(request.getAddress());
        if (request.getDob() != null) user.setDob(request.getDob());
        if (request.getWorkEmail() != null) user.setWorkEmail(request.getWorkEmail());
        if (request.getPassword() != null) user.setPassword(request.getPassword());
        if (request.getEmploymentType() != null) user.setEmploymentType(request.getEmploymentType());
        if (request.getJoinedDate() != null) user.setJoinedDate(request.getJoinedDate());
        if (request.getBasicSalary() != null) user.setBasicSalary(request.getBasicSalary());
        if (request.getRole() != null) user.setRole(request.getRole());
        if (request.getDepartmentId() != null) {
            user.setDepartment(department);
        }
        if (request.getPositionId() != null) {
            user.setPosition(position);
        }
    }

    public ResponseMessage deleteUserById(Long employeeId) {
        Optional<User> userOptional = getUserById(employeeId);
        if (userOptional.isPresent()) {

            User user = userOptional.get();

            departmentRepository.findByDepartmentHead(user).ifPresent(department -> {
                department.setDepartmentHead(null);
                departmentRepository.save(department);
            });

            userRepository.deleteById(employeeId);
            return ResponseMessage.builder()
                    .message("User is deleted Successfully")
                    .build();
        }
        else
        {
            return ResponseMessage.builder()
                    .message("User is not found")
                    .build();
        }
    }
    private UserDTO convertToDTO(User user) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String dobString = user.getDob().format(formatter);

        return UserDTO.builder()
                .employeeId(user.getEmployeeId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .gender(user.getGender())
                .workEmail(user.getWorkEmail())
                .mobilePhoneNo(user.getMobilePhoneNo())
                .employmentType(user.getEmploymentType())
                .joinedDate(user.getJoinedDate())
                .epfNo(user.getEpfNo())
                .dob(dobString)
                .maritalStatus(user.getMaritalStatus())
                .address(user.getAddress())
                .spouseName(user.getSpouseName())
                .fatherName(user.getFatherName())
                .motherName(user.getMotherName())
                .basicSalary(user.getBasicSalary())
                .nic(user.getNic())
                .homePhoneNo(user.getHomePhoneNo())
                .age(user.getAge())
                .positionName(user.getPosition().getPositionName())
                .departmentName(user.getDepartment().getDepartmentName())
                .build();
    }
}
