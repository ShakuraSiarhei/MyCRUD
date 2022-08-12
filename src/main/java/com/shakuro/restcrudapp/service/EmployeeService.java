package com.shakuro.restcrudapp.service;

import com.shakuro.restcrudapp.entity.Employee;

import java.util.List;
import java.util.Optional;


public interface EmployeeService {

    public List<Employee> getAllEmployee();

    public Optional<Employee> getEmployeeById(int id);
    public void saveEmployee(Employee employee);

    public void deleteEmployeeById(int id);

}
