package ru.job4j.additionaltask;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * Class is bank.
 *
 * @since 22/10/2017
 * @version 1
 */
public class Bank {

    /**
     * Collection of rush hours in form of TimeDistance class, value is counter.
     */
    private final ConcurrentHashMap<TimeDistance, Integer> rushHours = new ConcurrentHashMap<>();

    /**
     * Customers of bank.
     */
    private final List<Customer> customers = new ArrayList<>();

    /**
     * Adds customer.
     *
     * @param customer is customer
     */
    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    /**
     * Finds rush hours.
     *
     * @return list of rush hours in form of TimeDistance class.
     */
    public List<TimeDistance> findRushHour() {

        ExecutorService exec = Executors.newCachedThreadPool();

        for (Customer customer : customers) {
            long start = customer.getTimeInBank().start;
            long end = customer.getTimeInBank().end;

            exec.submit(new Runnable() {
                @Override
                public void run() {
                    find(start, end);
                }
            });
        }
        exec.shutdown();

        // ждем окончания работы exec'a 5 минут
        try {
            exec.awaitTermination(5, SECONDS);
        } catch (InterruptedException e) {
            System.out.println("Computation was interrupted!");
        }

        // ищем максимальное значение
        int max = Integer.MIN_VALUE;
        for (int i : rushHours.values()) {
            if(i > max) max = i;
        }

        // по значению Integer. являющегося счетчиком,
        // в карте забираем TimeDistance
        List<TimeDistance> result = new ArrayList<>();
        for(Map.Entry<TimeDistance, Integer> pair : rushHours.entrySet()) {
            if (pair.getValue() == max) result.add(pair.getKey());
        }

        return result;
    }

    /**
     * Finds rush hour from start time interval.
     *
     * @param start of time interval
     * @param end of time interval
     */
    private void find(long start, long end) {
        int count = 0;
        long s = start;
        long e = end;
        for(int i = 0; i < customers.size(); i++) {
            long newStart = customers.get(i).getTimeInBank().start;
            long newEnd = customers.get(i).getTimeInBank().end;

            if(s <= newStart && e > newStart) {
                s = newStart;
                count++;
                if(newEnd < e) {
                    e = newEnd;
                }
            } else if (s > newStart && e > newStart && newEnd > s) {
                count++;
                if (newEnd <= e) {
                    e = newEnd;
                }
            }
        }
        rushHours.put(new TimeDistance(s, e), count);
    }
}
