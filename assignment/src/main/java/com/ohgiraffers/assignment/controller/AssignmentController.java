package com.ohgiraffers.assignment.controller;

import com.ohgiraffers.assignment.common.Pagenation;
import com.ohgiraffers.assignment.common.PagingButton;
import com.ohgiraffers.assignment.dto.DepartmentDTO;
import com.ohgiraffers.assignment.dto.EmployeeDTO;
import com.ohgiraffers.assignment.dto.JobDTO;
import com.ohgiraffers.assignment.service.AssignmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;

import java.text.DecimalFormat;
import java.util.List;


@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("assignment")
public class AssignmentController {

    private final AssignmentService service;

    @GetMapping("/code")
    public String findEmployeeByCodePage() {
        return "assignment/detail";
    }

    @PostMapping("/code")
    public String findEmployeeByCode(Integer empId, Model model){
        EmployeeDTO employee = service.findEmployeeByEmpId(empId);
        model.addAttribute("employee", employee);
        return "assignment/list";
    }

    @GetMapping("/list")
    public String findEmployeeList(Model model, @PageableDefault Pageable pageable) {
        Page<EmployeeDTO> employeeList = service.findEmployeeList(pageable);
        PagingButton paging = Pagenation.getPagingButtonInfo(employeeList);

        model.addAttribute("employeeList", employeeList);
        model.addAttribute("paging", paging);

        return "assignment/list";
    }

    @GetMapping("/querymethod")
    public void queryMethodPage() {}

    @GetMapping("/search")
    public String findByEmployeeSalaryGet(@RequestParam(value = "salary", required = false) Integer salary, Model model, @PageableDefault Pageable pageable) {
        if (salary == null) {
            salary = 0; // 기본값으로 0 설정
        }
        return setupModelAndReturnView(salary, model, pageable);
    }

    @PostMapping("/search")
    public String findByEmployeeSalary(@RequestParam("salary") Integer salary, Model model, @PageableDefault Pageable pageable) {
        setupModelAndReturnView(salary, model, pageable);
        return "redirect:/assignment/search?salary=" + salary;
    }

    private String setupModelAndReturnView(Integer salary, Model model, @PageableDefault Pageable pageable) {
        Page<EmployeeDTO> employeeList = service.findByEmployeeSalary(salary, pageable);
        PagingButton paging = Pagenation.getPagingButtonInfo(employeeList);
        model.addAttribute("employeeList", employeeList);
        model.addAttribute("paging", paging);
        model.addAttribute("searchList", "검색");
        model.addAttribute("salary", salary);

        DecimalFormat df = new DecimalFormat("###,###");
        String formattedSalary = df.format(salary);
        model.addAttribute("inputSalary", formattedSalary);

        return "assignment/searchList";
    }

    @GetMapping("/job")
    @ResponseBody
    public List<JobDTO> findJobList() {
        return service.findAllJob();
    }

    @GetMapping("/dept")
    @ResponseBody
    public List<DepartmentDTO> findDepartmentList() {
        return service.findAllDepartment();
    }

    @GetMapping("/manager")
    @ResponseBody
    public List<EmployeeDTO> findEmployeeList() {
        return service.findAllEmployee();
    }


    @GetMapping("/regist")
    public void registPage(){}

    @PostMapping("/regist")
    public String registNewEmployee(@ModelAttribute EmployeeDTO employeeDTO){
        service.registEmployee(employeeDTO);

        return "redirect:/assignment/list";
    }

    @GetMapping("/modifyDetail")
    public void modifyDetailPage(){
    }

    @PostMapping("/modifyDetail")
    public String modifyEmployeeByCode(Integer empId, Model model) {
        EmployeeDTO employee = service.findEmployeeByEmpId(empId);
        model.addAttribute("employee", employee);
        return "assignment/modify";
    }

    @PostMapping("/modify")
    public String modifyEmployee(@ModelAttribute EmployeeDTO employeeDTO, Model model){
        EmployeeDTO employee = service.modifyEmployee(employeeDTO);
        model.addAttribute("employee", employee);
        return "assignment/list";
    }

    @GetMapping("/delete")
    public void deletePage(){}

    @PostMapping("/delete")
    public String deleteEmployee(@RequestParam Integer empId){
        service.deleteEmployee(empId);
        return "redirect:/assignment/list";
    }
}
