package com.organisation.orgationisation.repository;

import com.organisation.orgationisation.entity.Employee;
import com.organisation.orgationisation.entity.Module;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ModuleRepository extends JpaRepository<Module, Long> {
    Module findByModuleName(String module);

    Optional<Module> findByModuleId(Long id);
}
