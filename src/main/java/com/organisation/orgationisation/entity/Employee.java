package com.organisation.orgationisation.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Employee {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "employee_id")
    public Integer employeeId;

    @Column
    public String employeeName;


    @ManyToMany()
    @JoinTable(
            name = "employee_module",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "module_id")
    )
    private Set<Module> modules = new HashSet<>();

    public Employee(String employeeName, Set<Module> modules){
        this.employeeName = employeeName;
        this.modules = modules;
    }

    @Transient
    private List<String> moduleNames;
}
