package com.hrm.human.resource.management.system.service;

import com.hrm.human.resource.management.system.dto.DepartmentAddRequestDTO;
import com.hrm.human.resource.management.system.dto.DepartmentDTO;
import com.hrm.human.resource.management.system.dto.UserDTO;
import com.hrm.human.resource.management.system.entity.Department;
import com.hrm.human.resource.management.system.entity.ResponseMessage;
import com.hrm.human.resource.management.system.entity.User;
import com.hrm.human.resource.management.system.repository.DepartmentRepository;
import com.hrm.human.resource.management.system.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final UserRepository userRepository;

    @Transactional
    public ResponseMessage addDepartment(DepartmentAddRequestDTO departmentRequestDTO) {
        try {
            User departmentHead = userRepository.findById(departmentRequestDTO.getDepartmentHeadId())
                    .orElseThrow(() -> new RuntimeException("Department head is not found"));

            Department department = Department.builder()
                    .departmentName(departmentRequestDTO.getDepartmentName())
                    .departmentDesc(departmentRequestDTO.getDepartmentDesc())
                    .departmentHead(departmentHead)
                    .build();

            departmentRepository.save(department);
            return ResponseMessage.builder()
                    .message("Department is created successfully")
                    .build();
        }
        catch(Exception e) {
            return ResponseMessage.builder()
                    .message("Department creation failed: " + e.getMessage())
                    .build();
        }
    }

    @Transactional(readOnly = true)
    public Department getDepartmentById(Long id) {
        return departmentRepository.findById(id).orElse(null);
    }

    @Transactional(readOnly = true)
    public List<DepartmentDTO> getAllDepartments() {
        return departmentRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public ResponseMessage updateDepartment(Long id, DepartmentAddRequestDTO departmentRequestDTO) {
        try {
            Department department = departmentRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Department not found with id: " + id));

            User departmentHead = userRepository.findById(departmentRequestDTO.getDepartmentHeadId())
                    .orElseThrow(() -> new RuntimeException("Department head is not found"));

            department.setDepartmentName(departmentRequestDTO.getDepartmentName());
            department.setDepartmentDesc(departmentRequestDTO.getDepartmentDesc());
            department.setDepartmentHead(departmentHead);

            departmentRepository.save(department);
            return ResponseMessage.builder()
                    .message("Department updated successfully")
                    .build();
        } catch (Exception e) {
            return ResponseMessage.builder()
                    .message("Failed to update department: " + e.getMessage())
                    .build();
        }
    }

    @Transactional
    public ResponseMessage deleteDepartment(Long id) {
        try {
            departmentRepository.deleteById(id);
            return ResponseMessage.builder()
                    .message("Department deleted successfully")
                    .build();
        } catch (Exception e) {
            return ResponseMessage.builder()
                    .message("Failed to delete department: " + e.getMessage())
                    .build();
        }
    }

    private DepartmentDTO convertToDTO(Department department){

        return DepartmentDTO.builder()
                .departmentId(department.getDepartmentId())
                .departmentName(department.getDepartmentName())
                .departmentDesc(department.getDepartmentDesc())
                .departmentHeadName(department.getDepartmentHead().getFirstName()+ " "+ department.getDepartmentHead().getLastName())
                .build();
    }
}
