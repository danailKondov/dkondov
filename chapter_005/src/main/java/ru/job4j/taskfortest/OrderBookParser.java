package ru.job4j.taskfortest;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Class parser.
 *
 * @since 11/09/2017
 * @version 1
 */
public class OrderBookParser {

    /**
     * List of order books
     */
    private ArrayList<OrderBook> orderBookList = new ArrayList<>();

    /**
     * Gets XML file as input stream.
     *
     * @param path to file
     * @return XML file as input stream
     */
    public InputStream getXMLfile(String path) {
        InputStream is = null;
        try {
            is = new FileInputStream(path);
        } catch (FileNotFoundException e) {
            System.out.println("Wrong path or file name!");
            throw new IllegalArgumentException();
        }
        return is;
    }

    /**
     * Parse XML file in form of order and order books.
     *
     * @param is XML file as input stream
     */
    public void parse(InputStream is) {
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLStreamReader parser= null;
        try {
            parser = factory.createXMLStreamReader(is);
            while(parser.hasNext()) {
                int event = parser.next();
                if (event == XMLStreamConstants.START_ELEMENT) {

                    // если тэг addOrder - добавить заказ
                    if (parser.getLocalName().equals("AddOrder")) {
                        String orderBookName = parser.getAttributeValue(null, "book");
                        String operation = parser.getAttributeValue(null, "operation");
                        double price = Double.valueOf(parser.getAttributeValue(null, "price"));
                        int volume = Integer.valueOf(parser.getAttributeValue(null, "volume"));
                        int orderID = Integer.valueOf(parser.getAttributeValue(null, "orderId"));

                        OrderBook book = getBookByName(orderBookName);
                        Order order = new Order(orderBookName, operation, price, volume, orderID);
                        book.addOrder(order);

                        // если тэг DeleteOrder - удалить заказ
                    } else if (parser.getLocalName().equals("DeleteOrder")) {
                        String orderBookName = parser.getAttributeValue(null, "book");
                        int orderID = Integer.valueOf(parser.getAttributeValue(null, "orderId"));
                        OrderBook book = getBookByName(orderBookName);
                        book.deleteOrder(orderID);
                    }
                }
            }
        } catch (XMLStreamException e) {
            e.printStackTrace();
        } finally {

            // безопасно закрываем XMLStreamReader
            if (parser != null) {
                try {
                    parser.close();
                } catch (XMLStreamException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Gets order book by name.
     *
     * @param name of book
     * @return order book with that name
     */
    private OrderBook getBookByName(String name) {

        // проверяем, есть ли в списке orderbook на такую книгу
        for (OrderBook ob : orderBookList) {
            if(ob.getName().equals(name)) {
                return ob;
            }
        }

        // если нет, то создаем и возвращаем новый orderbook
        OrderBook newOrderBook = new OrderBook(name);
        orderBookList.add(newOrderBook);
        return newOrderBook;
    }

    /**
     * Gets list of order books.
     *
     * @return list of order books
     */
    public ArrayList<OrderBook> getOrderBookList() {
        return orderBookList;
    }
}
