package com.mertselimb.databaseApiExample.Services;

import com.mertselimb.databaseApiExample.Entities.Employee;
import com.mertselimb.databaseApiExample.Exceptions.EmployeeNotFoundException;

import java.util.ArrayList;

public interface EmployeeService {
    ArrayList<Employee> getEmployees();

    Employee getEmployeeById(Long id) throws EmployeeNotFoundException;

    Employee insert(Employee todo);

    void updateEmployee(Long id, Employee todo) throws EmployeeNotFoundException;

    void deleteEmployee(Long todoId);
}
