package com.ohgiraffers.assignment.repository;

import com.ohgiraffers.assignment.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Page<Employee> findBySalaryGreaterThan(Integer salary, Pageable pageable);
    @Query(
            value = "SELECT emp_id, emp_name, emp_no, email, phone, dept_code, job_code, sal_level, salary, bonus, manager_id, hire_date, ent_date, ent_yn FROM employee.employee ORDER BY emp_id",
            nativeQuery = true
    )
    List<Employee> findAllEmployee();

    @Query("SELECT MAX(e.empId) FROM Employee e")
    Long findMaxEmployeeId();
}
