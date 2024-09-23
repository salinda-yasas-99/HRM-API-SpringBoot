package com.hrm.human.resource.management.system.repository;

import com.hrm.human.resource.management.system.entity.Attendance;
import com.hrm.human.resource.management.system.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    Optional<Attendance> findByEmployeeAndAttendDate(User employee, Date attendDate);
    List<Attendance> findByEmployee(User employee);
}
