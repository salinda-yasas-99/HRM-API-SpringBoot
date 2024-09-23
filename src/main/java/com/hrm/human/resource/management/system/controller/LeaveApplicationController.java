package com.hrm.human.resource.management.system.controller;

import com.hrm.human.resource.management.system.dto.LeaveApplicationDTO;
import com.hrm.human.resource.management.system.dto.LeaveApplicationReturnDTO;
import com.hrm.human.resource.management.system.entity.LeaveApplicationForm;
import com.hrm.human.resource.management.system.entity.ResponseMessage;
import com.hrm.human.resource.management.system.service.LeaveApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leave-application")
@RequiredArgsConstructor
public class LeaveApplicationController {

    private final LeaveApplicationService leaveApplicationService;

    @GetMapping("/employee/{employeeId}/leave-applications")
    public ResponseEntity<List<LeaveApplicationReturnDTO>> getLeaveApplicationsByEmployeeId(@PathVariable Long employeeId) {
        List<LeaveApplicationReturnDTO> leaveApplicationForms = leaveApplicationService.getLeaveApplicationFormsByEmployeeId(employeeId);
        return ResponseEntity.ok(leaveApplicationForms);
    }

    @GetMapping("/employee/leave-applications")
    public ResponseEntity<List<LeaveApplicationReturnDTO>> getAllPendingLeaveApplications() {
        List<LeaveApplicationReturnDTO> leaveApplicationForms = leaveApplicationService.getAllPendingLeaveApplications();
        return ResponseEntity.ok(leaveApplicationForms);
    }


    @PostMapping("/apply")
    public ResponseEntity<LeaveApplicationReturnDTO> applyForLeave(@RequestBody LeaveApplicationDTO leaveApplicationDTO) {
        LeaveApplicationReturnDTO leaveApplicationReturnDTO = leaveApplicationService.applyForLeave(leaveApplicationDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(leaveApplicationReturnDTO);
    }

    @GetMapping("/employee/{employeeId}/confirmed")
    public ResponseEntity<List<LeaveApplicationReturnDTO>> getConfirmedLeaveApplications(@PathVariable Long employeeId) {
        List<LeaveApplicationReturnDTO> leaveApplicationForms = leaveApplicationService.getApprovedLeaveApplicationFormsByEmployeeId(employeeId);
        return ResponseEntity.ok(leaveApplicationForms);
    }

    @PutMapping("/approve/{leaveApplicationFormId}")
    public ResponseEntity<ResponseMessage> approveLeaveApplication(
            @PathVariable Long leaveApplicationFormId,
            @RequestParam String newStatus) {
        ResponseMessage responseMessage = leaveApplicationService.updateApprovalStatus(leaveApplicationFormId, newStatus);
        return ResponseEntity.ok(responseMessage);
    }

    @GetMapping("/leave-applications-by-month")
    public ResponseEntity<List<LeaveApplicationReturnDTO>> getLeaveApplicationsByMonth(@RequestParam String month) {
        List<LeaveApplicationReturnDTO> leaveApplicationForms = leaveApplicationService.getLeaveApplicationsByMonth(month);
        return ResponseEntity.ok(leaveApplicationForms);
    }
}