package com.github.rsoi;

import com.github.rsoi.domain.Waiter;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SalaryRunner {
    public static String outputSeparator = "\n-----------------------------------\n";

    public static void main(String[] args) {
        List<Waiter> waiterList = initWaiters(10);

        StringBuilder output = new StringBuilder();


        waiterList.stream()
                .peek(waiter -> {
                    output.append("\nname:\t\tmonth:\t\tday:\ttablesQuantity:");
                    output.append(outputSeparator + waiter
                            + outputSeparator);
                })
                .flatMap(waiter -> waiter.getTableList().entrySet().stream())
                .forEach(map -> output.append("\t\t\t"
                        + map.getKey().getMonth()
                        + "\t"
                        + map.getKey().getDayOfMonth()
                        + "\t\t"
                        + map.getValue()
                        + "\n"
                ));

        System.out.println(output);

    }

    public static List<Waiter> initWaiters(int waitersQuantity) {
        List<Waiter> waiterList = new ArrayList<>();
        fillWaitersList(waiterList, 3);
        fillWaitersTableLists(waiterList);
        return waiterList;
    }

    public static void fillWaitersList(List<Waiter> waiterList, int waitersQuantity) {
        final String[] FIRST_NAMES = {"John", "Jane", "Jack", "Jill", "Joe", "Janet", "James", "Jasmine", "Jerry", "Julia"};
        final String[] LAST_NAMES = {"Doe", "Smith", "Johnson", "Williams", "Jones", "Brown", "Davis", "Garcia", "Martinez", "Hernandez"};
        Random rand = new Random();

        for (int i = 0; i < waitersQuantity; i++) {
            int id = i + 1;
            String firstName = FIRST_NAMES[rand.nextInt(FIRST_NAMES.length)];
            String lastName = LAST_NAMES[rand.nextInt(LAST_NAMES.length)];
            Waiter waiter = new Waiter(id, firstName, lastName);
            waiterList.add(waiter);
        }
    }

    //fill HashMap for each waiter by quantity of served tables per day of current month
    public static void fillWaitersTableLists(List<Waiter> waiterList) {
        int currentYar = LocalDate.now().getYear();
        int currentDayOfMonth = LocalDate.now().getDayOfMonth();
        int[] tableQuantityPerDay = {1, 3, 5, 7, 9, 10, 12, 2};
        Month currentMonth = LocalDate.now().getMonth();
        Random rand = new Random();

        for (Waiter waiter : waiterList) {
            for (int day = 1; day < currentDayOfMonth; day++) {
                waiter.getTableList().put(LocalDate.of(currentYar, currentMonth, day),
                        tableQuantityPerDay[rand.nextInt(tableQuantityPerDay.length)]);
            }
        }
    }
}
