package com.ohgiraffers.assignment.repository;

import com.ohgiraffers.assignment.entity.Department;
import com.ohgiraffers.assignment.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {
    @Query(
            value = "SELECT dept_id, dept_title, location_id FROM department ORDER BY dept_id",
            nativeQuery = true
    )
    List<Department> findAllDepartment();
}
