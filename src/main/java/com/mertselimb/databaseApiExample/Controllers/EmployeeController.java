package com.mertselimb.databaseApiExample.Controllers;

import com.mertselimb.databaseApiExample.Entities.Employee;
import com.mertselimb.databaseApiExample.Exceptions.EmployeeNotFoundException;
import com.mertselimb.databaseApiExample.Services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {
    EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    //The function receives a GET request, processes it and gives back a list of Employee as a response.
    @GetMapping
    public ResponseEntity<ArrayList<Employee>> getAllEmployees() {
        ArrayList<Employee> employees = employeeService.getEmployees();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    //The function receives a GET request, processes it, and gives back a list of Employee as a response.
    @GetMapping({"/{employeeId}"})
    public ResponseEntity<Employee> getEmployee(@PathVariable Long employeeId) {
        try {
            return new ResponseEntity<>(employeeService.getEmployeeById(employeeId), HttpStatus.OK);
        } catch (EmployeeNotFoundException exception) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    //The function receives a POST request, processes it, creates a new Employee and saves it to the database, and returns a resource link to the created employee.           @PostMapping
    @PostMapping
    public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee) {
        Employee employee1 = employeeService.insert(employee);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("employee", "/api/v1/employee/" + employee1.getId().toString());
        return new ResponseEntity<>(employee1, httpHeaders, HttpStatus.CREATED);
    }

    //The function receives a PUT request, updates the Employee with the specified Id and returns the updated Employee
    @PutMapping({"/{employeeId}"})
    public ResponseEntity<Employee> updateEmployee(@PathVariable("employeeId") Long employeeId, @RequestBody Employee employee) {
        try {
            employeeService.updateEmployee(employeeId, employee);
            return new ResponseEntity<>(employeeService.getEmployeeById(employeeId), HttpStatus.OK);
        } catch (EmployeeNotFoundException exception) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    //The function receives a DELETE request, deletes the Employee with the specified Id.
    @DeleteMapping({"/{employeeId}"})
    public ResponseEntity<Employee> deleteEmployee(@PathVariable("employeeId") Long employeeId) {
        employeeService.deleteEmployee(employeeId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
