package com.ohgiraffers.assignment.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@ToString
public class EmployeeDTO {

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

}
