package com.github.rsoi.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EmployeeCompensation {
    private Map<String, Double> employeeSalary;
    private int tablesToServePerMonth = 100;
    private double lowestSalary = 500;
    private double defaultSalary = 1000;
    private double fine = 20;
    private double bonus = 5;

//    public static void calculateSalary(List<Waiter> waiterList) {
//        String fullName;
//        double salary;
//        waiterList.stream()
//                .peek(list -> list.getTableList().entrySet().stream()
//                        .
//                )
////                .flatMap(waiter -> waiter.getTableList().entrySet().stream())
//                .mapToInt(map -> map.getValue().)
//    }

//    public static double getSalaryByTablesQuantity(int tablesQuantity){
//
//    }

}
