package com.ohgiraffers.assignment.repository;

import com.ohgiraffers.assignment.entity.Department;
import com.ohgiraffers.assignment.entity.Employee;
import com.ohgiraffers.assignment.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JobRepository extends JpaRepository<Job, Integer> {
    @Query(
            value = "SELECT job_code, job_name FROM job ORDER BY job_code",
            nativeQuery = true
    )
    List<Job> findAllJob();
}
