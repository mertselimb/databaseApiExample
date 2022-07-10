package com.mertselimb.databaseApiExample.Services.Impl;

import com.mertselimb.databaseApiExample.Entities.Employee;
import com.mertselimb.databaseApiExample.Repositories.EmployeeRepository;
import com.mertselimb.databaseApiExample.Services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public ArrayList<Employee> getEmployees() {
        ArrayList<Employee> employees = new ArrayList<>();
        employeeRepository.findAll().forEach(employees::add);
        return employees;
    }

    @Override
    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).get();
    }

    @Override
    public Employee insert(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public void updateEmployee(Long id, Employee employee) {
        Employee employeeFromDb = employeeRepository.findById(id).get();
        System.out.println(employeeFromDb);
        employeeFromDb.setId(employee.getId());
        employeeFromDb.setName(employee.getName());
        employeeFromDb.setRole(employee.getRole());
        employeeRepository.save(employeeFromDb);
    }

    @Override
    public void deleteEmployee(Long employeeId) {
        employeeRepository.deleteById(employeeId);
    }

}
