package com.github.rsoi.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Employee {
    private int id;
    private String name;
    private String surName;
    private double salary;

    public Employee(int id, String name, String surName) {
        this.id = id;
        this.name = name;
        this.surName = surName;
    }
}
