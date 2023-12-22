package com.organisation.orgationisation.controller;


import com.organisation.orgationisation.dto.ModuleDTO;
import com.organisation.orgationisation.entity.Module;
import com.organisation.orgationisation.entity.Module;
import com.organisation.orgationisation.service.ModuleService;
import com.organisation.orgationisation.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/module")
public class ModuleController {

    @Autowired
    private ModuleService moduleService;
    @PostMapping("/addModule")
    public  Module addModule(@RequestBody ModuleDTO module) {
            return moduleService.addModule(module);
    }


    @GetMapping("/getModuleList")
    public ResponseEntity<?> getModuleList() {

        return moduleService.getModuleList();
    }

    @GetMapping("/getModuleDetail/{id}")
    public ResponseEntity<?> getModuleDetail(@PathVariable Long id) {

        return moduleService.getModuleDetail(id);
    }

    @PutMapping("/updateModule/{id}")
    public ResponseEntity<?> updateModule(@PathVariable Long id, @RequestBody Module module) {
        return moduleService.updateModule(module,id);

    }

    @DeleteMapping("/deleteModule/{id}")
    public ResponseEntity<?> deleteModule(@PathVariable Long id) {

        return moduleService.deleteModule(id);

    }

}
