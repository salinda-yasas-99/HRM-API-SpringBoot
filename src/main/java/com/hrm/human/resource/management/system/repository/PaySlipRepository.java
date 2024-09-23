package com.hrm.human.resource.management.system.repository;

import com.hrm.human.resource.management.system.entity.PaySlip;
import com.hrm.human.resource.management.system.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PaySlipRepository extends JpaRepository<PaySlip, Long> {

    boolean existsByEmployeeAndMonth(User employee, String month);
    List<PaySlip> findByEmployee(Optional<User> employee);
    PaySlip findByPaySlipId(Long paySlipId);

    List<PaySlip> findByMonth(String month);

    @Query("SELECT p FROM PaySlip p WHERE YEAR(p.issuedDate) = :year AND p.month = :month")
    List<PaySlip> findByYearAndMonth(@Param("year") int year, @Param("month") String month);
}
