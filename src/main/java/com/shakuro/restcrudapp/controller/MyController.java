package com.shakuro.restcrudapp.controller;

import com.shakuro.restcrudapp.entity.Employee;
import com.shakuro.restcrudapp.entity.User;
import com.shakuro.restcrudapp.exception.NoSuchEmployeeException;
import com.shakuro.restcrudapp.security.MyUserDetails;
import com.shakuro.restcrudapp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class MyController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees")
    public List<Employee> getAllEmployees(){
        List<Employee> employees = employeeService.getAllEmployee();
        return employees;
    }

    @GetMapping("/employees/{id}")
    public Employee getEmployeeById(@PathVariable int id){
        Optional<Employee> employee = employeeService.getEmployeeById(id);
        if (employee.isEmpty()) {
            throw new NoSuchEmployeeException("There is no employee with id " + id + " in database.");
        } else {
            return employee.get();
        }
    }

    @PostMapping("/employees")
    public Employee saveEmployee(@RequestBody Employee employee){
        employeeService.saveEmployee(employee);
        return employee;
    }

    @DeleteMapping("/employees/{id}")
    public String deleteEmployee(@PathVariable int id) {
        if (employeeService.getEmployeeById(id) == null){
            throw new NoSuchEmployeeException("There is no employee with id " + id + " in database.");
        } else {
            employeeService.deleteEmployeeById(id);
            return "Employee with id " + id + " was deleted.";
        }
    }

    @PutMapping("/employees")
    public String updateEmployees(@RequestBody Employee employee) {
        if (employeeService.getEmployeeById(employee.getId()) == null){
            throw new NoSuchEmployeeException("There is no employee with id " + employee.getId() + " in database.");
        } else {
            employee.getEmployeeInfo().setId(employeeService.getEmployeeById(employee.getId()).get().getEmployeeInfo().getId());
            employeeService.saveEmployee(employee);
            return "Employee with id " + employee.getId() + " was updated.";
        }
    }

    @GetMapping("/userInfo")
    public User getUserInfo(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        MyUserDetails myUserDetails = (MyUserDetails) authentication.getPrincipal();
        return myUserDetails.getUser();
    }
}
