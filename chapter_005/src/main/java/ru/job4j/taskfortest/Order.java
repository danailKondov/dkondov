package ru.job4j.taskfortest;

/**
 * Class order.
 *
 * @since 10/09/2017
 * @version 1
 */
public class Order {

    /**
     * Book name.
     */
    private final String book;

    /**
     * Operation with book - sell or buy.
     */
    private final String operation;

    /**
     * Price of book.
     */
    private final double price;

    /**
     * Volume of books to sell or buy.
     */
    private final int volume;

    /**
     * Order ID.
     */
    private final int orderID;


    /**
     * Constructor.
     *
     * @param book name
     * @param operation sell or buy
     * @param price of book
     * @param volume of books in order
     * @param orderID identification number
     */
    public Order(String book, String operation, double price, int volume, int orderID) {
        this.book = book;
        this.operation = operation;
        this.price = price;
        this.volume = volume;
        this.orderID = orderID;
    }


    public String getBook() {
        return book;
    }

    public String getOperation() {
        return operation;
    }

    public double getPrice() {
        return price;
    }

    public int getVolume() {
        return volume;
    }

    public int getOrderID() {
        return orderID;
    }

    @Override
    public String toString() {
        return volume + "@" + price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (Double.compare(order.getPrice(), getPrice()) != 0) return false;
        if (getVolume() != order.getVolume()) return false;
        if (getOrderID() != order.getOrderID()) return false;
        if (getBook() != null ? !getBook().equals(order.getBook()) : order.getBook() != null) return false;
        return getOperation() != null ? getOperation().equals(order.getOperation()) : order.getOperation() == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = getBook() != null ? getBook().hashCode() : 0;
        result = 31 * result + (getOperation() != null ? getOperation().hashCode() : 0);
        temp = Double.doubleToLongBits(getPrice());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + getVolume();
        result = 31 * result + getOrderID();
        return result;
    }
}
