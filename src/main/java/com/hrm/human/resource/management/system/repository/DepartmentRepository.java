package com.hrm.human.resource.management.system.repository;

import com.hrm.human.resource.management.system.entity.Department;
import com.hrm.human.resource.management.system.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department,Long> {

    Optional<Department> findByDepartmentHead(User departmentHead);
    Optional<Department> findByDepartmentName(String departmentName);

}
