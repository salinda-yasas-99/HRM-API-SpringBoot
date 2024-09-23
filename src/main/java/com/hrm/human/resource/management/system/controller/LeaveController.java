package com.hrm.human.resource.management.system.controller;

import com.hrm.human.resource.management.system.entity.Leave;
import com.hrm.human.resource.management.system.entity.ResponseMessage;
import com.hrm.human.resource.management.system.service.LeaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/leave")
@RequiredArgsConstructor
public class LeaveController {

    private final LeaveService leaveService;

    @PostMapping
    public ResponseMessage createLeave(@RequestBody Leave leave) {
        return leaveService.createLeave(leave);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getLeaveById(@PathVariable Long id) {
        try {
            Leave leave = leaveService.getLeaveById(id);
            return ResponseEntity.ok(leave);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ResponseMessage.builder().message(e.getMessage()).build());
        }
    }

    @GetMapping
    public List<Leave> getAllLeaves() {
        return leaveService.getAllLeaves();
    }

    @PutMapping("/{id}")
    public ResponseMessage updateLeave(@PathVariable Long id, @RequestBody Leave leaveDetails) {
        return leaveService.updateLeave(id, leaveDetails);
    }

    @DeleteMapping("/{id}")
    public ResponseMessage deleteLeave(@PathVariable Long id) {
        return leaveService.deleteLeave(id);
    }
}
