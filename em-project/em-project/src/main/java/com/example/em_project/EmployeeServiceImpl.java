package com.example.em_project;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public String createEmployee(Employee employee) {
        EmployeeEntity employeeEntity = new EmployeeEntity();
        BeanUtils.copyProperties(employee, employeeEntity);
        employeeRepository.save(employeeEntity);
        return "Saved Successfully";
    }

    @Override
    public List<Employee> readAllEmployees() {
        List<EmployeeEntity> employeeEntities = employeeRepository.findAll();
        List<Employee> employees = new ArrayList<>();

        for (EmployeeEntity employeeEntity : employeeEntities) {
            Employee employee = new Employee();
            BeanUtils.copyProperties(employeeEntity, employee);
            employees.add(employee);
        }
        return employees;
    }

    @Override
    public Employee readEmployee(Long id) {
        Optional<EmployeeEntity> optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isPresent()) {
            Employee employee = new Employee();
            BeanUtils.copyProperties(optionalEmployee.get(), employee);
            return employee;
        } else {
            // Handle case where employee is not found
            // You can either throw a custom exception or return null, depending on your design
            return null; // Or throw an exception
        }
    }

    @Override
    public boolean deleteEmployee(Long id) {
        Optional<EmployeeEntity> optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isPresent()) {
            employeeRepository.delete(optionalEmployee.get());
            return true;
        } else {
            return false; // Employee not found
        }
    }

    @Override
    public String updateEmployee(Long id, Employee employee) {
        Optional<EmployeeEntity> optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isPresent()) {
            EmployeeEntity existingEmployee = optionalEmployee.get();
            BeanUtils.copyProperties(employee, existingEmployee, "id"); // Exclude the id field from copying
            employeeRepository.save(existingEmployee);
            return "Updated Successfully";
        } else {
            return "Employee Not Found";
        }
    }
}
