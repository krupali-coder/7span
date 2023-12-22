package com.organisation.orgationisation.testCase;

import com.organisation.orgationisation.entity.Employee;
import com.organisation.orgationisation.entity.Module;
import com.organisation.orgationisation.repository.EmployeeRepository;
import com.organisation.orgationisation.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    private Employee employee;
    private List<Module> modules;

    @BeforeEach
    void setUp() {
        employee = new Employee();
        employee.setEmployeeId(1);
        employee.setEmployeeName("John Doe");

        Module module1 = new Module();
        module1.setModuleId(1);
        module1.setModuleName("Library");

        Module module2 = new Module();
        module2.setModuleId(2);
        module2.setModuleName("Dashboard");

        modules = Arrays.asList(module1, module2);
    }

    @Test
    public void whenAddEmployee_thenEmployeeShouldBeSaved() {
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);

        Employee savedEmployee = employeeService.addEmployee(employee);

        assertNotNull(savedEmployee.getEmployeeId());
        assertEquals("John Doe", savedEmployee.getEmployeeName());
    }
}
