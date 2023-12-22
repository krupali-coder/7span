package com.organisation.orgationisation.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Module {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "module_id")
    private Integer moduleId;

    @Column
    private String moduleName;

    @ManyToMany(mappedBy = "modules")
    private Set<Employee> employees = new HashSet<>();

    public Module(String moduleName) {
        this.moduleName = moduleName;
    }
}
