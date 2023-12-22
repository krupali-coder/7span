package com.organisation.orgationisation.repository;

import com.organisation.orgationisation.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.Optional;

public interface EmployeeRepository  extends JpaRepository<Employee, Long> {

    Optional<Employee> findByEmployeeId(Long id);
}
