package com.hrm.human.resource.management.system.service;

import com.hrm.human.resource.management.system.entity.Leave;
import com.hrm.human.resource.management.system.entity.ResponseMessage;
import com.hrm.human.resource.management.system.repository.LeaveRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LeaveService {
    private final LeaveRepository leaveRepository;

    @Transactional
    public ResponseMessage createLeave(Leave leave) {
        try {
            leaveRepository.save(leave);
            return ResponseMessage.builder()
                    .message("Leave created successfully")
                    .build();
        } catch (Exception e) {
            return ResponseMessage.builder()
                    .message("Leave creation failed: " + e.getMessage())
                    .build();
        }
    }

    public Leave getLeaveById(Long id) {
        return leaveRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Leave not found"));
    }

    public List<Leave> getAllLeaves() {
        return leaveRepository.findAll();
    }

    @Transactional
    public ResponseMessage updateLeave(Long id, Leave leaveDetails) {
        try {
            Leave leave = leaveRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Leave not found"));

            leave.setLeaveTypeName(leaveDetails.getLeaveTypeName());
            leave.setNoOfLeaves(leaveDetails.getNoOfLeaves());

            leaveRepository.save(leave);
            return ResponseMessage.builder()
                    .message("Leave updated successfully")
                    .build();
        } catch (Exception e) {
            return ResponseMessage.builder()
                    .message("Leave update failed: " + e.getMessage())
                    .build();
        }
    }

    @Transactional
    public ResponseMessage deleteLeave(Long id) {
        try {
            Leave leave = leaveRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Leave not found"));

            leaveRepository.delete(leave);
            return ResponseMessage.builder()
                    .message("Leave deleted successfully")
                    .build();
        } catch (Exception e) {
            return ResponseMessage.builder()
                    .message("Leave deletion failed: " + e.getMessage())
                    .build();
        }
    }
}
