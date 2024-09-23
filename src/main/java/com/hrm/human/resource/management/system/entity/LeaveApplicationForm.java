package com.hrm.human.resource.management.system.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "leaveApplicationForm")
public class LeaveApplicationForm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long leaveApplicationFormId;
    private Integer noOfDays;
    private String reason;
    private String approvedStatus = "Pending";
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    @PrePersist
    protected void onCreate() {
        this.approvedStatus = "Pending";
    }

    @ManyToOne
    @JoinColumn(name = "employeeId")
    private User employee;

    @ManyToOne
    @JoinColumn(name = "leaveId")
    private Leave leave;
}
