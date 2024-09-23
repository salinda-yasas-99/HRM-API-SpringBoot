package com.hrm.human.resource.management.system.repository;

import com.hrm.human.resource.management.system.entity.Leave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LeaveRepository extends JpaRepository<Leave, Long> {
    Optional<Leave> findByLeaveTypeName(String leaveTypeName);
}
