package com.github.rsoi.domain;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Waiter extends Employee{
    private double salary;

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    private Map<LocalDate, Integer> tableList = new HashMap<>();
    public Waiter(int id, String name, String surName) {
        super(id, name, surName);
    }

    public Map<LocalDate, Integer> getTableList() {
        return tableList;
    }

    public void setTableList(Map<LocalDate, Integer> tableList) {
        this.tableList = tableList;
    }



    @Override
    public String toString() {
        return getName() + " " + getSurName();
    }
}
