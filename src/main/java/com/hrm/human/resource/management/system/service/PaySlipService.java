package com.hrm.human.resource.management.system.service;

import com.hrm.human.resource.management.system.dto.PaySlipDTO;
import com.hrm.human.resource.management.system.entity.PaySlip;
import com.hrm.human.resource.management.system.entity.User;
import com.hrm.human.resource.management.system.entity.UserBonus;
import com.hrm.human.resource.management.system.repository.PaySlipRepository;
import com.hrm.human.resource.management.system.repository.UserBonusRepository;
import com.hrm.human.resource.management.system.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PaySlipService {

    private final UserRepository userRepository;
    private final UserBonusRepository userBonusRepository;
    private final PaySlipRepository paySlipRepository;

    @Scheduled(cron = "0 0 0 1 * *") // This will run at midnight on the first day of every month
    public void generatePaySlips() {
        generatePaySlipsForMonth(LocalDate.now());
    }

    public void generatePaySlipsForMonth(LocalDate date) {
        List<User> employees = userRepository.findAll();
        LocalDate payDate = date.withDayOfMonth(date.lengthOfMonth());
        LocalDate issuedDate = payDate.plusDays(1);

        for (User employee : employees) {
            if (!paySlipRepository.existsByEmployeeAndMonth(employee, payDate.getMonth().toString())) {
                PaySlip paySlip = new PaySlip();
                float basicAmount = employee.getBasicSalary();
                float totalBonus = calculateTotalBonus(employee, payDate);
                float employeeEpfAmount = basicAmount * 0.08f;
                float employerEpfAmount = basicAmount * 0.12f;
                float employerEtfAmount = basicAmount * 0.03f;
                float grossAmount = basicAmount + totalBonus;
                float netAmount = grossAmount - employeeEpfAmount;

                paySlip.setEmployee(employee);
                paySlip.setBasicAmount(basicAmount);
                paySlip.setTotalBonus(totalBonus);
                paySlip.setEmployeeEpfAmount(employeeEpfAmount);
                paySlip.setEmployerEpfAmount(employerEpfAmount);
                paySlip.setEmployerEtfAmount(employerEtfAmount);
                paySlip.setGrossAmount(grossAmount);
                paySlip.setNetAmount(netAmount);
                paySlip.setPayDate(Date.from(payDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
                paySlip.setIssuedDate(Date.from(issuedDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
                paySlip.setMonth(payDate.getMonth().toString());

                paySlipRepository.save(paySlip);
            }
        }
    }

    private float calculateTotalBonus(User employee, LocalDate payDate) {
        List<UserBonus> bonuses = userBonusRepository.findByEmployeeAndMonth(employee, payDate.getMonth().toString());
        return bonuses.stream()
                .map(bonus -> bonus.getAmount())
                .reduce(0.0f, Float::sum);
    }

    public void createBacklogPayslips(int year) {
        LocalDate now = LocalDate.now();
        List<User> employees = userRepository.findAll();

        for (User employee : employees) {
            LocalDate joinedDate = employee.getJoinedDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            if (joinedDate.getYear() > year) {
                continue;
            }
            int startMonth = joinedDate.getYear() == year ? joinedDate.getMonthValue() : 1;
            for (int month = startMonth; month <= now.getMonthValue(); month++) {
                LocalDate date = LocalDate.of(year, month, 1);
                generatePaySlipsForMonth(date);
            }
        }
    }

    public List<PaySlipDTO> getAllPayslipsForEmployee(Optional<User> employee) {
        return paySlipRepository.findByEmployee(employee).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public PaySlipDTO getPayslipById(Long paySlipId) {
        PaySlip paySlip = paySlipRepository.findByPaySlipId(paySlipId);
        return convertToDto(paySlip);
    }

    public List<PaySlipDTO> getAllPayslipsForYearAndMonth(int year, String month) {
        List<PaySlip> paySlips = paySlipRepository.findByYearAndMonth(year, month.toUpperCase());
        return paySlips.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }


//    public List<PaySlipDTO> getAllPayslipsForMonth(String month) {
//        List<PaySlip> paySlips = paySlipRepository.findByMonth(month.toUpperCase());
//        return paySlips.stream()
//                .map(this::convertToDto)
//                .collect(Collectors.toList());
//    }

    private PaySlipDTO convertToDto(PaySlip paySlip) {
        return PaySlipDTO.builder()
                .paySlipId(paySlip.getPaySlipId())
                .employeeId(paySlip.getEmployee().getEmployeeId())
                .grossAmount(paySlip.getGrossAmount())
                .netAmount(paySlip.getNetAmount())
                .basicAmount(paySlip.getBasicAmount())
                .totalBonus(paySlip.getTotalBonus())
                .employeeEpfAmount(paySlip.getEmployeeEpfAmount())
                .employerEpfAmount(paySlip.getEmployerEpfAmount())
                .employerEtfAmount(paySlip.getEmployerEtfAmount())
                .payDate(paySlip.getPayDate())
                .issuedDate(paySlip.getIssuedDate())
                .month(paySlip.getMonth())
                .build();
    }
}
