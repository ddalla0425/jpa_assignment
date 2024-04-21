package com.ohgiraffers.assignment.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;


@ToString
@Entity
@Table(name = "employee")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Employee {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int empId;
    private String empName;
    private String empNo;
    private String email;
    private String phone;
    private String deptCode;
    private String jobCode;
    private String salLevel;
    private BigDecimal salary;
    private BigDecimal bonus;
    private String managerId;
    private LocalDate hireDate;
    private LocalDate entDate;
    private String entYn;

    public void setEmpId(int empId) {
        this.empId = empId;
    }

}
