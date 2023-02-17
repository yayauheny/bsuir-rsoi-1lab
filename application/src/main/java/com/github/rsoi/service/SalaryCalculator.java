package com.github.rsoi.service;

import com.github.rsoi.domain.Employee;
import com.github.rsoi.domain.Waiter;

import java.time.LocalDate;
import java.time.Month;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SalaryCalculator {
    private static int profitPerTable = 30;
    private static int tablesToServePerMonth = 100;
    private static double lowestSalary = 500;
    private static double defaultSalary = 1000;
    private static double fine = 20;
    private static double bonus = 5;

    public static void calculateSalary(List<Waiter> waiterList, Month month) {
        waiterList.stream()
                .forEach(waiter -> {
                    int totalTablesPerMonth = waiter.getTableList().entrySet().stream()
                            .filter(map -> month.equals(map.getKey().getMonth()))
                            .mapToInt(Map.Entry::getValue)
                            .sum();
                    waiter.setTotalTablesPerMonth(totalTablesPerMonth);
                    waiter.setSalary(calculateBonuses(totalTablesPerMonth));
                });
    }

    private static double calculateBonuses(int tablesQuantity) {
        double totalFine;

        if (tablesQuantity == tablesToServePerMonth)
            return defaultSalary;
        else if (tablesQuantity > tablesToServePerMonth)
            return defaultSalary + (tablesQuantity - tablesToServePerMonth) * bonus;
        else {
            totalFine = (tablesToServePerMonth - tablesQuantity) * fine;
            return ((defaultSalary - totalFine) > lowestSalary) ? defaultSalary - totalFine : defaultSalary;
        }
    }

    public static HashMap<Month, Double> calculateTotalNetProfit(List<Waiter> employees, Month month) {
        HashMap<Month, Double> profitList = new HashMap<>();

        double totalSalary = employees.stream().mapToDouble(Waiter::getSalary).sum();
        double totalProfit = employees.stream().mapToInt(Waiter::getTotalTablesPerMonth).sum() * profitPerTable;
        double totalNetProfit = totalProfit - totalSalary;
        profitList.put(month, totalNetProfit);
        return profitList;
    }
}
