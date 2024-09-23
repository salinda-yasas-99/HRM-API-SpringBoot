package com.hrm.human.resource.management.system.controller;

import com.hrm.human.resource.management.system.dto.PaySlipDTO;
import com.hrm.human.resource.management.system.entity.PaySlip;
import com.hrm.human.resource.management.system.entity.User;
import com.hrm.human.resource.management.system.service.PaySlipService;
import com.hrm.human.resource.management.system.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/payslip")
@RequiredArgsConstructor
public class PaySlipController {

    private final PaySlipService paySlipService;
    private final UserService userService;


    @PostMapping("/backfill/{year}")
    public ResponseEntity<Void> createPreviousPayslips(@PathVariable int year) {
        paySlipService.createBacklogPayslips(year);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<PaySlipDTO>> getAllPayslipsForEmployee(@PathVariable Long employeeId) {
        Optional<User> employee = userService.getUserById(employeeId);
        List<PaySlipDTO> paySlips = paySlipService.getAllPayslipsForEmployee(employee);
        return ResponseEntity.ok(paySlips);
    }

    @GetMapping("/{paySlipId}")
    public ResponseEntity<PaySlipDTO> getPayslipById(@PathVariable Long paySlipId) {
        PaySlipDTO paySlip = paySlipService.getPayslipById(paySlipId);
        return ResponseEntity.ok(paySlip);
    }

//    @GetMapping("/month/{month}")
//    public ResponseEntity<List<PaySlipDTO>> getAllPayslipsForMonth(@PathVariable String month) {
//        List<PaySlipDTO> paySlips = paySlipService.getAllPayslipsForMonth(month);
//        return ResponseEntity.ok(paySlips);
//    }

    @GetMapping("/year/{year}/month/{month}")
    public ResponseEntity<List<PaySlipDTO>> getAllPayslipsForYearAndMonth(@PathVariable int year, @PathVariable String month) {
        List<PaySlipDTO> paySlips = paySlipService.getAllPayslipsForYearAndMonth(year, month);
        return ResponseEntity.ok(paySlips);
    }
}
