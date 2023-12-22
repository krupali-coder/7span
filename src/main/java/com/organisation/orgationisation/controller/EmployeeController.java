package com.organisation.orgationisation.controller;


import com.organisation.orgationisation.dto.EmployeeDTO;
import com.organisation.orgationisation.entity.Employee;
import com.organisation.orgationisation.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);
    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/addEmployee")
    public Employee addEmployee(@RequestBody Employee employee) {

            return employeeService.addEmployee(employee);

    }

    @GetMapping("/getEmployeeList")
    public ResponseEntity<?> getEmployeeList() {
        logger.info("fetching all employees");
        return employeeService.getEmployeeList();
    }

    @GetMapping("/getEmployeeDetail/{id}")
    public ResponseEntity<?> getEmployeeDetail(@PathVariable Long id) {

        return employeeService.getEmployeeDetail(id);
    }

    @PutMapping("/updateEmployee/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable Long id, @RequestBody Employee employee) {
        return employeeService.updateEmployee(employee,id);

    }

    @DeleteMapping("/deleteEmployee/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long id) {

        return employeeService.deleteEmployee(id);

    }

}
