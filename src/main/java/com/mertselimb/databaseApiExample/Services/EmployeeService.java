package com.mertselimb.databaseApiExample.Services;

import com.mertselimb.databaseApiExample.Entities.Employee;
import com.mertselimb.databaseApiExample.Exceptions.EmployeeNotFoundError;

import java.util.ArrayList;

public interface EmployeeService {
    ArrayList<Employee> getEmployees();

    Employee getEmployeeById(Long id);

    Employee insert(Employee todo);

    void updateEmployee(Long id, Employee todo);

    void deleteEmployee(Long todoId);
}
