package com.shakuro.restcrudapp.service.serviceImpl;

import com.shakuro.restcrudapp.dao.DepartmentRepository;
import com.shakuro.restcrudapp.dao.EmployeeRepository;
import com.shakuro.restcrudapp.entity.Department;
import com.shakuro.restcrudapp.entity.Employee;
import com.shakuro.restcrudapp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public List<Employee> getAllEmployee() {
        List<Employee> employees = employeeRepository.findAll();
        return employees;
    }

    @Override
    public Optional<Employee> getEmployeeById(int id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        return employee;
    }

    @Override
    public void saveEmployee(Employee employee) {
        Optional<Department> department = departmentRepository.findByDepartmentName(employee.getDepartment().getDepartmentName());
        if (department.isPresent()){
            employee.setDepartment(department.get());
            employeeRepository.save(employee);
        } else {
            employeeRepository.save(employee);
        }
    }

    @Override
    public void deleteEmployeeById(int id) {
        employeeRepository.deleteById(id);
    }

}
