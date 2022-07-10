package com.mertselimb.databaseApiExample.Controllers;

import com.mertselimb.databaseApiExample.Entities.Employee;
import com.mertselimb.databaseApiExample.Enums.Role;
import com.mertselimb.databaseApiExample.Repositories.EmployeeRepository;
import com.mertselimb.databaseApiExample.Services.EmployeeService;
import com.mertselimb.databaseApiExample.Services.Impl.EmployeeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

class EmployeeServiceTest {


    EmployeeRepository mockEmployeeRepository = Mockito.mock(EmployeeRepository.class);
    EmployeeService employeeService = new EmployeeServiceImpl(mockEmployeeRepository);
    Employee employee;

    @BeforeEach()
    public void setup() {
        employee = new Employee();
        employee.setRole(Role.DEVELOPER);
        employee.setName("Mert SELIMBEYOGLU");
        employee.setId(1L);
    }

    @Test
    public void testGet() {
        Mockito.when(mockEmployeeRepository.findById(1L)).thenReturn(java.util.Optional.ofNullable(employee));
        Employee response = employeeService.getEmployeeById(1L);
        assertEquals(response, employee);
    }

    @Test
    public void testGetAll() {
        ArrayList<Employee> employeeList = new ArrayList<>();
        employeeList.add(employee);
        Mockito.when(mockEmployeeRepository.findAll()).thenReturn(employeeList);

        ArrayList<Employee> response = employeeService.getEmployees();
        assertEquals(response, employeeList);
    }

    @Test
    public void testInsert() {
        employeeService.insert(employee);
        verify(mockEmployeeRepository).save(employee);
    }

    @Test
    public void testDelete() {
        employeeService.deleteEmployee(employee.getId());
        verify(mockEmployeeRepository).deleteById(employee.getId());
    }

    @Test
    public void testUpdate() {
        Mockito.when(mockEmployeeRepository.findById(1L)).thenReturn(java.util.Optional.ofNullable(employee));

        Employee updatedEmployee = new Employee();
        updatedEmployee.setId(1L);
        updatedEmployee.setName("Mert");
        updatedEmployee.setRole(Role.DESIGNER);

        employeeService.updateEmployee(employee.getId(), updatedEmployee);

        ArgumentCaptor<Employee> argumentCaptor = ArgumentCaptor.forClass(Employee.class);
        verify(mockEmployeeRepository).save(argumentCaptor.capture());

        Employee capturedArgument = argumentCaptor.getValue();
        assertEquals(capturedArgument.getName(), updatedEmployee.getName());
        assertEquals(capturedArgument.getRole(), updatedEmployee.getRole());
    }


}