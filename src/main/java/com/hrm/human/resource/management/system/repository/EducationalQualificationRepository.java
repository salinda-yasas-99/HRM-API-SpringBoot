package com.hrm.human.resource.management.system.repository;

import com.hrm.human.resource.management.system.entity.EducationalQualification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EducationalQualificationRepository extends JpaRepository<EducationalQualification,Long> {
}
