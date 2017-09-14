package ru.job4j.taskfortest;

import java.util.*;

/**
 * Class order.
 *
 * @since 10/09/2017
 * @version 1
 */
public class OrderBook {

    /**
     * Name of order book.
     */
    private final String orderBookName;

    /**
     * Contains all orders by ID.
     */
    private HashMap<Integer, Order> rawOrders = new HashMap<>();

    /**
     * Contains all sell order's price and volume sorted by price.
     */
    private TreeMap<Double, Integer> sell = new TreeMap<>(); // по возрастанию

    /**
     * Contains all buy order's price and volume sorted by price.
     */
    private TreeMap<Double, Integer> buy = new TreeMap<>(new Comparator<Double>() {
        @Override
        public int compare(Double o1, Double o2) {
            return Double.compare(o2, o1);
        }
    }); // по убыванию

    public OrderBook(String name) {
        this.orderBookName = name;
    }

    /**
     * Adds new order to order book.
     *
     * @param order to add
     */
    public void addOrder(Order order) {
        rawOrders.put(order.getOrderID(), order);
    }


    /**
     * Deletes order from book order by ID.
     *
     * @param id of order to delete
     */
    public void deleteOrder(int id) {
        rawOrders.remove(id);
    }

    public String getName() {
        return orderBookName;
    }

    /**
     * Processing data
     */
    public void processData() {

        // (1) Убираем дублирование цен, суммируя объем, и (2) добавляем в TreeSet,
        // где происходит сортировка
        for (Order order : rawOrders.values()) {
            double price = order.getPrice();
            int volume = order.getVolume();

            if (order.getOperation().equals("SELL")) {
                if (sell.containsKey(price)) {
                    sell.put(price, sell.get(price) + volume); // складываем объем при одинаковых ценах
                } else {
                    sell.put(price, volume);
                }
            } else {
                if (buy.containsKey(price)) {
                    buy.put(price, buy.get(price) + volume); // складываем объем при одинаковых ценах
                } else {
                    buy.put(price, volume);
                }
            }
        }

        // (3) Сравниваем цены покупки и продажи и если они соответствуют
        // условию bid>=ask, то сокращаем по заданному алгоритму
        Map.Entry<Double, Integer> sellEntry = sell.firstEntry();
        Map.Entry<Double, Integer> buyEntry = buy.firstEntry();

        while(buyEntry.getKey() >= sellEntry.getKey()) { // bid>=ask
            double sellPrice = sellEntry.getKey();
            int sellVolume = sellEntry.getValue();
            double buyPrice = buyEntry.getKey();
            int buyVolume = buyEntry.getValue();
            if(sellVolume > buyVolume) {
                sell.put(sellPrice, sellVolume - buyVolume);
                buy.remove(buyPrice);
            } else if(sellVolume < buyVolume) {
                buy.put(buyPrice, buyVolume - sellVolume);
                sell.remove(sellPrice);
            } else if(sellVolume == buyVolume) {
                buy.remove(buyPrice);
                sell.remove(sellPrice);
            }
            sellEntry = sell.firstEntry();
            buyEntry = buy.firstEntry();
        }
    }

    /**
     * Prints data in needed format
     */
    public void print() {
        System.out.println("===================================\n");
        System.out.println("Order book: ${" + orderBookName + "}");
        System.out.println();
        System.out.println("      BID             ASK");
        System.out.println();
        System.out.println("    Volume@Price – Volume@Price");

        Iterator<Map.Entry<Double, Integer>> buyIterator = buy.entrySet().iterator();
        Iterator<Map.Entry<Double, Integer>> sellIterator = sell.entrySet().iterator();

        while(true) {
            if (buyIterator.hasNext() && sellIterator.hasNext()) {
                Map.Entry<Double, Integer> sellEntry = sellIterator.next();
                Map.Entry<Double, Integer> buyEntry = buyIterator.next();
                System.out.printf("%10d@%-5.1f - %7d@%-5.1f", buyEntry.getValue(), buyEntry.getKey(), sellEntry.getValue(), sellEntry.getKey());
                System.out.println();
            } else if(!buyIterator.hasNext() && sellIterator.hasNext()) {
                Map.Entry<Double, Integer> sellEntry = sellIterator.next();
                System.out.printf("---------------- - %7d@%-5.1f", sellEntry.getValue(), sellEntry.getKey());
                System.out.println();
            } else if(buyIterator.hasNext() && !sellIterator.hasNext()) {
                Map.Entry<Double, Integer> buyEntry = buyIterator.next();
                System.out.printf("%10d@%-5.1f - -------------", buyEntry.getValue(), buyEntry.getKey());
                System.out.println();
            } else {
                break;
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrderBook orderBook = (OrderBook) o;

        return orderBookName != null ? orderBookName.equals(orderBook.orderBookName) : orderBook.orderBookName == null;
    }

    @Override
    public int hashCode() {
        return orderBookName != null ? orderBookName.hashCode() : 0;
    }
}
