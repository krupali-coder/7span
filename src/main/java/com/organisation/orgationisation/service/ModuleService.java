package com.organisation.orgationisation.service;

import com.organisation.orgationisation.ResourceNotFoundException;
import com.organisation.orgationisation.dto.ModuleDTO;
import com.organisation.orgationisation.entity.Module;
import com.organisation.orgationisation.repository.ModuleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ModuleService {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);
    
    @Autowired
    public ModuleRepository moduleRepository;

    public Module addModule(ModuleDTO module) {
        try {
            Module moduleData = moduleRepository
                    .save(new Module(module.getModuleName()));
            return moduleData;
        } catch (Exception e) {
            logger.error("Error:: ", e.getMessage());
            return null;
        }
    }

    public ResponseEntity<?> getModuleList() {
        try {
            List<Module> moduleList = moduleRepository
                    .findAll();
            return new ResponseEntity<>(moduleList, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Error:: ", e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> getModuleDetail(Long id) {
        try {
            Module _tutorial = moduleRepository
                    .findByModuleId(id).orElseThrow(() -> new ResourceNotFoundException("Module not found for this id. " +id));
            return new ResponseEntity<>(_tutorial, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Error:: ", e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> deleteModule(Long id) {
        try {
            Module module = moduleRepository
                    .findById(id).orElseThrow(() -> new ResourceNotFoundException("Module not found for this id. " +id));;
            moduleRepository.delete(module);
            return new ResponseEntity<>(id, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error:: ", e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<?> updateModule(Module module,Long id) {

        try {
            Module moduleData = moduleRepository
                    .findById(id).orElseThrow(() -> new ResourceNotFoundException("Module not found for this id. " +id));;
            moduleData.setModuleName(module.getModuleName());
            moduleRepository.save(moduleData);
            return new ResponseEntity<>(moduleData, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error:: ", e.getMessage());
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    
}
