package com.hrm.human.resource.management.system.service;

import com.hrm.human.resource.management.system.dto.AddUserLeaveTypeDTO;
import com.hrm.human.resource.management.system.dto.LeaveDTO;
import com.hrm.human.resource.management.system.entity.Leave;
import com.hrm.human.resource.management.system.entity.ResponseMessage;
import com.hrm.human.resource.management.system.entity.User;
import com.hrm.human.resource.management.system.entity.UserLeave;
import com.hrm.human.resource.management.system.repository.LeaveRepository;
import com.hrm.human.resource.management.system.repository.UserLeaveRepository;
import com.hrm.human.resource.management.system.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserLeaveService {
    private final UserLeaveRepository userLeaveRepository;
    private final UserRepository userRepository;
    private final LeaveRepository leaveRepository;

    public ResponseMessage addUserLeave(AddUserLeaveTypeDTO addUserLeaveTypeDTO) {
        User employee = userRepository.findById(addUserLeaveTypeDTO.getEmployeeId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Leave leave = leaveRepository.findById(addUserLeaveTypeDTO.getLeaveId())
                .orElseThrow(() -> new RuntimeException("Leave type not found"));

        UserLeave userLeave = new UserLeave();
        userLeave.setEmployee(employee);
        userLeave.setLeave(leave);
        userLeave.setNoOfLeaves(leave.getNoOfLeaves());

        userLeaveRepository.save(userLeave);

        return ResponseMessage.builder()
                .message("Leave type assigned successfully to employee")
                .build();
    }

    public List<LeaveDTO> getLeaveTypesForEmployee(Long employeeId) {
        User employee = userRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<UserLeave> userLeaves = userLeaveRepository.findByEmployee(employee);

        return userLeaves.stream().map(userLeave -> LeaveDTO.builder()
                .employeeId(employee.getEmployeeId())
                .leaveTypeName(userLeave.getLeave().getLeaveTypeName())
                .noOfLeaves(userLeave.getNoOfLeaves())
                .build()).collect(Collectors.toList());
    }
}
