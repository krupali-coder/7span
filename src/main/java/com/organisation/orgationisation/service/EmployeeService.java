package com.organisation.orgationisation.service;

import com.organisation.orgationisation.ResourceNotFoundException;
import com.organisation.orgationisation.dto.EmployeeDTO;
import com.organisation.orgationisation.entity.Employee;
import com.organisation.orgationisation.entity.Module;
import com.organisation.orgationisation.repository.EmployeeRepository;
import com.organisation.orgationisation.repository.ModuleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);
    @Autowired
    public EmployeeRepository employeeRepository;

    @Autowired
    public ModuleRepository moduleRepository;


//    @Autowired
//    private ModelMapper modelMapper;
    public Employee addEmployee(Employee employee) {
        try {
            ArrayList<Module> moduleList = new ArrayList<>();
            employee.getModuleNames().forEach(module ->{
                Module moduleObj = moduleRepository.findByModuleName(module);
                moduleList.add(moduleObj);
            } );
            Set<Module> moduleSet = moduleList.stream().collect(Collectors.toSet());
            Employee employeeData = employeeRepository
                    .save(new Employee(employee.getEmployeeName(), moduleSet));
            return employeeData;
        } catch (Exception e) {logger.error("Error:: ", e.getMessage());
            return null;
        }
    }

    public ResponseEntity<?> getEmployeeList() {
        try {
            logger.info("get employee list");
            List<Employee> employeeList = employeeRepository
                    .findAll();
            return new ResponseEntity<>(employeeList, HttpStatus.CREATED);
        } catch (Exception e) {logger.error("Error:: ", e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> getEmployeeDetail(Long id) {
        try {
            Employee employee = employeeRepository
                    .findByEmployeeId(id).orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id. " +id));
            EmployeeDTO employeeDTO = convertToDTO(employee);
            return ResponseEntity.ok().body(employeeDTO);
        } catch (Exception e) {
            logger.error("Error:: ", e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> deleteEmployee(Long id) {
//
        try {
            Employee dataRecord = employeeRepository
                    .findByEmployeeId(id).orElse(null);
             employeeRepository
                    .delete(dataRecord);
            return new ResponseEntity<>(id, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error:: ", e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> updateEmployee(Employee employee,Long id) {

        try {
            Employee dataRecord = employeeRepository
                    .findByEmployeeId(id).orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id. " +id));
            dataRecord.setEmployeeName(employee.getEmployeeName());
            employeeRepository.save(dataRecord);
            return new ResponseEntity<>(dataRecord, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error:: ", e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public EmployeeDTO convertToDTO(Employee employee) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setEmployeeId(employee.getEmployeeId());
        employeeDTO.setEmployeeName(employee.getEmployeeName());
        employeeDTO.setModuleNames(employee.getModules().stream().map(Module::getModuleName).collect(Collectors.toSet()));
        // Handle mapping for modules
        return employeeDTO;
    }

    public Employee convertToEntity(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setEmployeeId(employeeDTO.getEmployeeId());
        employee.setEmployeeName(employeeDTO.getEmployeeName());
        // Handle mapping for modules
        return employee;
    }
}
