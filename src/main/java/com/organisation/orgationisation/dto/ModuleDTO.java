package com.organisation.orgationisation.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ModuleDTO {
    private Long moduleId;
    private String moduleName;
}
