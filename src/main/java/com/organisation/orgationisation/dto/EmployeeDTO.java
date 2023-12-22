package com.organisation.orgationisation.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.organisation.orgationisation.entity.Module;
import lombok.Data;

import java.util.Set;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployeeDTO {
    private Integer employeeId;
    private String employeeName;
    private Set<String> moduleNames;
    private Set<Module>  modules;
}
