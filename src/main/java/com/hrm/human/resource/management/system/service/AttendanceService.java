package com.hrm.human.resource.management.system.service;

import com.hrm.human.resource.management.system.dto.AttendanceDTO;
import com.hrm.human.resource.management.system.dto.AttendanceRequestDTO;
import com.hrm.human.resource.management.system.entity.Attendance;
import com.hrm.human.resource.management.system.entity.ResponseMessage;
import com.hrm.human.resource.management.system.entity.User;
import com.hrm.human.resource.management.system.repository.AttendanceRepository;
import com.hrm.human.resource.management.system.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AttendanceService {

    private final AttendanceRepository attendanceRepository;
    private final UserRepository userRepository;

    public ResponseMessage recordAttendance(AttendanceRequestDTO request) {
        try {
            User employee = userRepository.findById(request.getEmployeeId())
                    .orElseThrow(() -> new RuntimeException("Employee not found"));

            Optional<Attendance> existingAttendance = attendanceRepository.findByEmployeeAndAttendDate(employee, request.getAttendDate());

            if (existingAttendance.isPresent()) {
                Attendance attendance = existingAttendance.get();
                if (attendance.getInTime() == null) {
                    attendance.setInTime(request.getTime());
                    attendance.setStatus("checkingIn");
                } else if (attendance.getOutTime() == null) {
                    attendance.setOutTime(request.getTime());
                    attendance.setStatus("checkedOut");
                    attendance.setDuration(LocalTime.ofNanoOfDay(attendance.getOutTime().toNanoOfDay() - attendance.getInTime().toNanoOfDay()));
                } else {
                    throw new RuntimeException("Attendance is already recorded");
                }
                attendanceRepository.save(attendance);
            } else {
                Attendance attendance = new Attendance();
                attendance.setEmployee(employee);
                attendance.setAttendDate(request.getAttendDate());
                attendance.setInTime(request.getTime());
                attendance.setStatus("checkingIn");
                attendanceRepository.save(attendance);
            }
            return ResponseMessage.builder()
                    .message("Attendance recorded successfully")
                    .build();
        } catch (Exception e) {
            return ResponseMessage.builder()
                    .message("Failed to record attendance: " + e.getMessage())
                    .build();
        }
    }

    public List<AttendanceDTO> getAllAttendanceForEmployee(Long employeeId) {
        User employee = userRepository.findById(employeeId)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        List<Attendance> attendances = attendanceRepository.findByEmployee(employee);
        return attendances.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public List<AttendanceDTO> getAllAttendance() {
        List<Attendance> attendances = attendanceRepository.findAll();
        return attendances.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private AttendanceDTO mapToDTO(Attendance attendance) {
        AttendanceDTO dto = new AttendanceDTO();
        dto.setAttendanceId(attendance.getAttendanceId());
        dto.setEmployeeId(attendance.getEmployee().getEmployeeId()); // Assuming you have an Employee entity with an employeeId field
        dto.setAttendDate(attendance.getAttendDate());
        dto.setInTime(attendance.getInTime());
        dto.setOutTime(attendance.getOutTime());
        dto.setStatus(attendance.getStatus());
        dto.setDuration(attendance.getDuration());
        return dto;
    }
}
