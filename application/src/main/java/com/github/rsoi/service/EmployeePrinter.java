package com.github.rsoi.service;

import com.github.rsoi.domain.Employee;
import com.github.rsoi.domain.Waiter;

import java.time.Month;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class EmployeePrinter {
    private static String currency = "BYN";
    private static String outputSeparator = "\n-------------------------------------------------------\n";

    public void printEmployeeInfo(List<Waiter> employeeList, Month month) {
        StringBuilder output = new StringBuilder();
        output.append(String.format("\nSalaries info for waiters by the %s\n", month.toString()));

        employeeList.stream()
                .forEach(employee -> {
                    output.append("\nname:\t\t\t\tday:\t\tserviced tables:");
                    output.append(outputSeparator + employee + outputSeparator);
                    employee.getTableList().entrySet().stream()
                            .filter(map -> month.equals(map.getKey().getMonth()))
                            .forEach(map -> output.append("\n\t\t\t\t\t"
                                    + map.getKey().getDayOfMonth()
                                    + "\t\t\t" + map.getValue()));
                    output.append(String.format("\n\nTotal serviced tables: %s\nSalary for %s: %s%s\n",
                            employee.getTotalTablesPerMonth(), month, employee.getSalary(), currency));
                });

        System.out.println(output);
    }

    public void printSalaries(List<? extends Employee> employeeList, Month month) {
        System.out.println(outputSeparator + "Printing salaries for " + month + outputSeparator);
        HashMap<? extends Employee, Double> salaryList = employeeList.stream()
                .collect(Collectors.toMap(Function.identity(), Employee::getSalary, (s1, s2) -> s1, HashMap::new));
        salaryList.forEach((key, value) -> System.out.println(key + ": " + value + currency));
    }

    public void printNetProfit(HashMap<Month, Double> profitList) {
        System.out.println(outputSeparator + "List of net profit, grouped by month:" + outputSeparator);
        profitList.entrySet().stream().forEach(map -> System.out.println(map.getKey() + ": " + map.getValue()));
    }
}

