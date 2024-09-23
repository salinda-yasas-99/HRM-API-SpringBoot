package com.hrm.human.resource.management.system.repository;

import com.hrm.human.resource.management.system.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByWorkEmail(String workEmail);

}
