// ShoppingCart.java
package library;

import java.util.*;

public class ShoppingCart {
    private Map<Book, Integer> items;
    private double total;

    public ShoppingCart() {
        items = new HashMap<>();
        total = 0.0;
    }

    public void addBook(Book book, int quantity) {
        if (book.getQuantity() >= quantity) {
            items.put(book, items.getOrDefault(book, 0) + quantity);
            total += book.getCost() * quantity;
        }
    }

    public void removeBook(Book book) {
        Integer quantity = items.get(book);
        if (quantity != null) {
            total -= book.getCost() * quantity;
            items.remove(book);
        }
    }

    public double getTotal() {
        return total;
    }

    public Map<Book, Integer> getItems() {
        return items;
    }

    public void checkout() {
        for (Map.Entry<Book, Integer> entry : items.entrySet()) {
            Book book = entry.getKey();
            int quantity = entry.getValue();
            book.setQuantity(book.getQuantity() - quantity);
        }
        items.clear();
        total = 0.0;
    }
}