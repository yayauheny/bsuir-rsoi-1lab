package com.github.rsoi.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
//@Builder
public class Waiter extends Employee {
    private int totalTablesPerMonth;
    private Map<LocalDate, Integer> tableList = new HashMap<>();

    public Waiter(int id, String name, String surName) {
        super(id, name, surName);
    }

    @Override
    public String toString() {
        return getName() + " " + getSurName();
    }
}
