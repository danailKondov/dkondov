package ru.job4j.taskfortest;

import java.util.List;

/**
 * Class execute all needed operations from parsing to printing
 * of order books.
 *
 * @since 14/09/2017
 * @version 1
 */
public class OrderBookExecutor {

    /**
     * Execute all needed operations.
     */
    public void execute() {
        String path = "I:\\Ресурсы Java\\Курс job4j\\Часть 005. Коллекции-про\\orders.xml";
        OrderBookParser bookParser = new OrderBookParser();
        bookParser.parse(bookParser.getXMLfile(path));

        for (OrderBook ob : bookParser.getOrderBookList()) {
            ob.processData();
            ob.print();
        }

    }
}
