package com.hrm.human.resource.management.system.repository;

import com.hrm.human.resource.management.system.entity.LeaveApplicationForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface LeaveApplicationRepository extends JpaRepository<LeaveApplicationForm,Long> {

    List<LeaveApplicationForm> findByEmployee_EmployeeIdAndApprovedStatus(Long employeeId, String approvedStatus);
    List<LeaveApplicationForm> findByEmployee_EmployeeId(Long employeeId);
    List<LeaveApplicationForm> findAllByApprovedStatus(String approvedStatus);

    List<LeaveApplicationForm> findAllByEndDateBetween(LocalDate startDate,LocalDate endDate);

}
