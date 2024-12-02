// Book.java
import java.io.Serializable;
import java.util.Objects;

public class Book implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private final int id;
    private final String title;
    private final String author;
    private final double price;
    private boolean available;
    private String isbn;
    private String category;

    public Book(int id, String title, String author, double price, String isbn, String category) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be null or empty");
        }
        if (author == null || author.trim().isEmpty()) {
            throw new IllegalArgumentException("Author cannot be null or empty");
        }
        if (price < 0) {
            throw new IllegalArgumentException("Price cannot be negative");
        }
        
        this.id = id;
        this.title = title.trim();
        this.author = author.trim();
        this.price = Math.round(price * 100.0) / 100.0;  // Round to 2 decimal places
        this.available = true;
        this.isbn = isbn;
        this.category = category;
    }

    // Getters
    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public double getPrice() { return price; }
    public boolean isAvailable() { return available; }
    public String getIsbn() { return isbn; }
    public String getCategory() { return category; }

    public void setAvailable(boolean available) { this.available = available; }
    public void setIsbn(String isbn) { this.isbn = isbn; }
    public void setCategory(String category) { this.category = category; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        Book book = (Book) o;
        return id == book.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return String.format("%s by %s (ID: %d) - $%.2f", title, author, id, price);
    }
}