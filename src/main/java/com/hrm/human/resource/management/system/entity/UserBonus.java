package com.hrm.human.resource.management.system.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "user_bonus")
public class UserBonus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userBonusId;
    private String month;
    private Float amount;

    @ManyToOne
    @JoinColumn(name = "bonusId")
    private Bonus bonus;

    @ManyToOne
    @JoinColumn(name = "employeeId")
    private User employee;
}
