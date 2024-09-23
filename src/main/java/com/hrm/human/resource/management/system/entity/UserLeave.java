package com.hrm.human.resource.management.system.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_leave")
public class UserLeave {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userLeaveId;

    @ManyToOne
    @JoinColumn(name = "employeeId")
    private User employee;

    @ManyToOne
    @JoinColumn(name = "leaveId")
    private Leave leave;

    private Integer noOfLeaves;
}
