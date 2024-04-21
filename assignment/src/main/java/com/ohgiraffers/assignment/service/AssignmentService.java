package com.ohgiraffers.assignment.service;
import com.ohgiraffers.assignment.dto.DepartmentDTO;
import com.ohgiraffers.assignment.dto.EmployeeDTO;
import com.ohgiraffers.assignment.dto.JobDTO;
import com.ohgiraffers.assignment.entity.Department;
import com.ohgiraffers.assignment.entity.Employee;
import com.ohgiraffers.assignment.entity.Job;
import com.ohgiraffers.assignment.repository.DepartmentRepository;
import com.ohgiraffers.assignment.repository.EmployeeRepository;
import com.ohgiraffers.assignment.repository.JobRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@RequiredArgsConstructor
public class AssignmentService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final JobRepository jobRepository;
    private final ModelMapper modelMapper;

    public EmployeeDTO findEmployeeByEmpId(Integer empId) {
        Employee foundEmployee = employeeRepository.findById(empId).orElseThrow(IllegalArgumentException::new);
        System.out.println("결과 값좀 보자 : " + foundEmployee);
        return modelMapper.map(foundEmployee, EmployeeDTO.class);
    }

    public Page<EmployeeDTO> findEmployeeList(Pageable pageable) {
        pageable = PageRequest.of(
                pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() - 1,
                pageable.getPageSize(),
                Sort.by("empId").descending()
        );
        Page<Employee> employeeList =  employeeRepository.findAll(pageable);
        return employeeList.map(employee -> modelMapper.map(employee, EmployeeDTO.class));
    }

    public Page<EmployeeDTO> findByEmployeeSalary(Integer salary, Pageable pageable) {
        pageable = PageRequest.of(
                pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() - 1,
                pageable.getPageSize(),
                Sort.by("salary").descending()
        );
        Page<Employee> employeeList = employeeRepository.findBySalaryGreaterThan(salary, pageable);
        employeeList.forEach(System.out::println);
        return employeeList.map(employee -> modelMapper.map(employee, EmployeeDTO.class));

    }

    public List<JobDTO> findAllJob() {
        List<Job> jobList = jobRepository.findAllJob();
        return jobList.stream()
                .map(job -> modelMapper.map(job, JobDTO.class))
                .toList();
    }

    public List<DepartmentDTO> findAllDepartment() {
        List<Department> departmentList = departmentRepository.findAllDepartment();
        return departmentList.stream()
                .map(department -> modelMapper.map(department, DepartmentDTO.class))
                .toList();
    }

    public List<EmployeeDTO> findAllEmployee() {
        List<Employee> employeeList = employeeRepository.findAllEmployee();
        return employeeList.stream()
                .map(employee -> modelMapper.map(employee, EmployeeDTO.class))
                .toList();
    }

    @Transactional
    public void registEmployee(EmployeeDTO employeeDTO) {
        Long maxEmployeeId = employeeRepository.findMaxEmployeeId();
        int newEmployeeId = (maxEmployeeId != null) ? maxEmployeeId.intValue() + 1 : 1;
        Employee employee = modelMapper.map(employeeDTO, Employee.class);
        employee.setEmpId(newEmployeeId);
        employeeRepository.save(employee);
        //employeeRepository.save(modelMapper.map(employeeDTO, Employee.class));
    }

    @Transactional
    public EmployeeDTO modifyEmployee(EmployeeDTO employeeDTO) {
        Employee foundEmp = employeeRepository.findById(employeeDTO.getEmpId()).orElseThrow(IllegalArgumentException::new);
        modelMapper.map(employeeDTO, foundEmp);
        employeeRepository.save(foundEmp);
        EmployeeDTO updateEmployeeDTO = modelMapper.map(foundEmp, EmployeeDTO.class);

        return updateEmployeeDTO;
    }
    @Transactional
    public void deleteEmployee(Integer empId) {
        employeeRepository.deleteById(empId);
    }
}
