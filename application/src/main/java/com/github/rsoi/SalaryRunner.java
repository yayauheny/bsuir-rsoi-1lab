package com.github.rsoi;

import com.github.rsoi.domain.Waiter;
import com.github.rsoi.service.EmployeePrinter;
import com.github.rsoi.service.SalaryCalculator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class SalaryRunner {
    public static void main(String[] args) {
        HashMap<Month, Double> netProfitList;
        List<Waiter> waiterList = initWaiters(3);

        //Spring context
        ApplicationContext context = new AnnotationConfigApplicationContext(SalaryConfiguration.class);
        EmployeePrinter printer = context.getBean(EmployeePrinter.class);
        SalaryCalculator calculator = context.getBean(SalaryCalculator.class);

        Month currentMonth = LocalDate.now().getMonth();
        calculator.calculateSalary(waiterList, currentMonth);
        netProfitList = calculator.calculateTotalNetProfit(waiterList, currentMonth);

        printer.printSalaries(waiterList, currentMonth);
        printer.printNetProfit(netProfitList);
    }

    public static List<Waiter> initWaiters(int waitersQuantity) {
        List<Waiter> waiterList = new ArrayList<>();
//        List<Waiter> waiterList = WaiterDAO.addWaiterName();
        fillWaitersTableLists(waiterList);
        fillWaitersList(waiterList, waitersQuantity);
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

    //fill HashMap for each waiter by quantity of served tables per day for current month
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

@Configuration
class SalaryConfiguration {
    @Bean
    public EmployeePrinter employeePrinter() {
        return new EmployeePrinter();
    }

    @Bean
    public SalaryRunner salaryRunner() {
        return new SalaryRunner();
    }

    @Bean
    public SalaryCalculator salaryCalculator() {
        return new SalaryCalculator();
    }
}
