package com.github.rsoi.domain.data;

import com.github.rsoi.domain.Waiter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class WaiterDAO {
    private static final String FILENAME = "waiter.json";
    private static final Gson gson = new Gson();

    public static List<Waiter> addWaiterName() {
        System.out.println("Print number of waiters you want to add\n: ");
        try (Scanner scanner = new Scanner(System.in)) {
            final int numberOfWaiters = scanner.nextInt();
            List<Waiter> waiters = new ArrayList<>();

            for (int i = 0; i < numberOfWaiters-1; i++) {
                System.out.println("Print name of waiter you want to add: ");
                String line = scanner.nextLine();
                String[] name = line.split(" ");

                waiters.add(new Waiter(i, name[0], name[1]));
            }
            return waiters;
        }
    }

    public static List<Waiter> getWaiters() throws IOException {
        List<Waiter> waiters = new ArrayList<>();

        try (FileReader reader = new FileReader(FILENAME)) {
            Type type = new TypeToken<List<Waiter>>() {
            }.getType();
            waiters = gson.fromJson(reader, type);
        } catch (IOException e) {
            System.out.println("Error reading a file: " + e.getMessage());
            e.printStackTrace();
        }

        return waiters;
    }

    private static void saveWaiters(List<Waiter> waiters) throws IOException {
        try (FileWriter writer = new FileWriter(FILENAME)) {
            gson.toJson(waiters, writer);
        } catch (IOException e) {
            System.out.println("Error writing a file: " + e.getMessage());
            e.printStackTrace();
        }
    }
}