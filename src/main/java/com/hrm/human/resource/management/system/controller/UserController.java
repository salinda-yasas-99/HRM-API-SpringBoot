package com.hrm.human.resource.management.system.controller;
import com.hrm.human.resource.management.system.dto.UserDTO;
import com.hrm.human.resource.management.system.dto.UserUpdateRequestDTO;
import com.hrm.human.resource.management.system.entity.ResponseMessage;
import com.hrm.human.resource.management.system.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/{employeeId}/{role}")
    public ResponseEntity<UserDTO> getUserDetailsById(@PathVariable Long employeeId,@PathVariable String role) {
        Optional<UserDTO> userDetailsOptional = userService.getUserDetailsByIdAndRole(employeeId, role);
        if (userDetailsOptional.isPresent()) {
            return ResponseEntity.ok(userDetailsOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/employees")
    public ResponseEntity<List<UserDTO>> getAllEmployees() {
        List<UserDTO> employees = userService.getAllEmployees();
        if (!employees.isEmpty()) {
            return ResponseEntity.ok(employees);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @PutMapping("/{employeeId}")
    public ResponseEntity<ResponseMessage> updateUser(@PathVariable Long employeeId, @RequestBody UserUpdateRequestDTO request) {
        return ResponseEntity.ok(userService.updateUser(employeeId, request));
    }

    @DeleteMapping("/{employeeId}")
    public ResponseEntity<ResponseMessage> deleteUser(@PathVariable Long employeeId) {
        return ResponseEntity.ok( userService.deleteUserById(employeeId));
    }
}
