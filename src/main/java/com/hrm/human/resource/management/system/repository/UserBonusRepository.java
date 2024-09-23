package com.hrm.human.resource.management.system.repository;

import com.hrm.human.resource.management.system.entity.User;
import com.hrm.human.resource.management.system.entity.UserBonus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserBonusRepository extends JpaRepository<UserBonus, Long> {
    List<UserBonus> findByEmployeeAndMonth(User employee, String month);

}
