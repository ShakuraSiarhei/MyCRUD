package com.shakuro.restcrudapp.dao;

import com.shakuro.restcrudapp.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
