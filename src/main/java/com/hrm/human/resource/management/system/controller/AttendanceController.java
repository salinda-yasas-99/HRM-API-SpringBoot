package com.hrm.human.resource.management.system.controller;

import com.hrm.human.resource.management.system.dto.AttendanceDTO;
import com.hrm.human.resource.management.system.dto.AttendanceRequestDTO;
import com.hrm.human.resource.management.system.entity.Attendance;
import com.hrm.human.resource.management.system.entity.ResponseMessage;
import com.hrm.human.resource.management.system.service.AttendanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/attendance")
@RequiredArgsConstructor
public class AttendanceController {

    private final AttendanceService attendanceService;

    @PostMapping("/record")
    public ResponseEntity<ResponseMessage> recordAttendance(@RequestBody AttendanceRequestDTO request) {
        ResponseMessage responseMessage = attendanceService.recordAttendance(request);
        if (responseMessage.getMessage().contains("successfully")) {
            return ResponseEntity.ok(responseMessage);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseMessage);
        }
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<AttendanceDTO>> getAllAttendanceForEmployee(@PathVariable Long employeeId) {
        List<AttendanceDTO> attendances = attendanceService.getAllAttendanceForEmployee(employeeId);
        return ResponseEntity.ok(attendances);
    }

    @GetMapping("/all")
    public ResponseEntity<List<AttendanceDTO>> getAllAttendance() {
        List<AttendanceDTO> attendances = attendanceService.getAllAttendance();
        return ResponseEntity.ok(attendances);
    }
}
