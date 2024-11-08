// package com.example.em_project;



package com.example.em_project;

import java.util.List;

public interface EmployeeService {

    String createEmployee(Employee employee);

    List<Employee> readAllEmployees(); // Renamed to clarify it returns all employees

    Employee readEmployee(Long id); // Retrieves a single employee by ID

    boolean deleteEmployee(Long id);

    String updateEmployee(Long id, Employee employee);
}
