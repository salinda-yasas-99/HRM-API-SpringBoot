package com.hrm.human.resource.management.system.controller;

import com.hrm.human.resource.management.system.dto.DepartmentAddRequestDTO;
import com.hrm.human.resource.management.system.dto.DepartmentDTO;
import com.hrm.human.resource.management.system.entity.Department;
import com.hrm.human.resource.management.system.entity.ResponseMessage;
import com.hrm.human.resource.management.system.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/department")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    @PostMapping
    public ResponseEntity<ResponseMessage> addDepartment(@RequestBody DepartmentAddRequestDTO departmentAddRequestDTO) {
        return ResponseEntity.ok(departmentService.addDepartment(departmentAddRequestDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDepartmentById(@PathVariable Long id) {
        try {
            Department department = departmentService.getDepartmentById(id);
            if (department != null) {
                return ResponseEntity.ok(department);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseMessage.builder()
                        .message("Department not found with id: " + id)
                        .build());
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseMessage.builder()
                    .message("Failed to retrieve department: " + e.getMessage())
                    .build());
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<DepartmentDTO>> getAllDepartments() {
        List<DepartmentDTO> departments = departmentService.getAllDepartments();
        if (!departments.isEmpty()) {
            return ResponseEntity.ok(departments);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseMessage> updateDepartment(@PathVariable Long id, @RequestBody DepartmentAddRequestDTO departmentRequestDTO) {
        ResponseMessage responseMessage = departmentService.updateDepartment(id, departmentRequestDTO);
        return ResponseEntity.ok(responseMessage);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseMessage> deleteDepartment(@PathVariable Long id) {
        ResponseMessage responseMessage = departmentService.deleteDepartment(id);
        return ResponseEntity.ok(responseMessage);
    }
}
