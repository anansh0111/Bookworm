// BookDatabase.java
import java.util.*;

public class BookDatabase {
    private final List<Book> books;
    private final Map<Integer, Book> bookMap;
    private static final String[] CATEGORIES = {
        "Fiction", "Non-Fiction", "Science", "History", "Technology",
        "Philosophy", "Biography", "Poetry", "Drama", "Mystery"
    };

    public BookDatabase() {
        this.books = new ArrayList<>();
        this.bookMap = new HashMap<>();
        initializeBooks();
    }

    private void initializeBooks() {
        String[] authors = {
            "George Orwell", "J.K. Rowling", "Stephen King", "Jane Austen",
            "Ernest Hemingway", "Virginia Woolf", "Charles Dickens", "Mark Twain",
            "Agatha Christie", "F. Scott Fitzgerald", "Gabriel García Márquez",
            "Toni Morrison", "Haruki Murakami", "Leo Tolstoy", "Emily Brontë"
        };

        String[] bookPrefixes = {
            "The Great", "Mystery of", "Tales from", "Journey to",
            "Adventures in", "Chronicles of", "Secrets of", "Beyond the",
            "Legacy of", "Dawn of", "Whispers of", "Shadow of", "Light of",
            "Heart of", "Spirit of"
        };

        String[] bookSuffixes = {
            "World", "Kingdom", "Desert", "Mountain", "Ocean",
            "Forest", "City", "Stars", "Dreams", "Time", "Memory",
            "Destiny", "Hope", "Legend", "Mystery"
        };

        Random random = new Random();

        for (int i = 0; i < 100; i++) {
            String title = bookPrefixes[random.nextInt(bookPrefixes.length)] + " " +
                          bookSuffixes[random.nextInt(bookSuffixes.length)];
            String author = authors[random.nextInt(authors.length)];
            double price = 9.99 + random.nextInt(40);
            String isbn = generateISBN(random);
            String category = CATEGORIES[random.nextInt(CATEGORIES.length)];
            
            Book book = new Book(i + 1, title, author, price, isbn, category);
            books.add(book);
            bookMap.put(book.getId(), book);
        }
    }

    private String generateISBN(Random random) {
        StringBuilder isbn = new StringBuilder("978");
        for (int i = 0; i < 10; i++) {
            isbn.append(random.nextInt(10));
        }
        return isbn.toString();
    }

    public List<Book> getAllBooks() {
        return new ArrayList<>(books);
    }

    public Book getBook(int id) {
        return bookMap.get(id);
    }

    public List<Book> searchBooks(String query) {
        if (query == null || query.trim().isEmpty()) {
            return getAllBooks();
        }
        
        query = query.toLowerCase().trim();
        List<Book> results = new ArrayList<>();
        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(query) ||
                book.getAuthor().toLowerCase().contains(query) ||
                book.getCategory().toLowerCase().contains(query) ||
                book.getIsbn().contains(query)) {
                results.add(book);
            }
        }
        return results;
    }

    public List<Book> getBooksByCategory(String category) {
        if (category == null || category.trim().isEmpty()) {
            return getAllBooks();
        }
        
        List<Book> results = new ArrayList<>();
        for (Book book : books) {
            if (book.getCategory().equals(category)) {
                results.add(book);
            }
        }
        return results;
    }

    public String[] getCategories() {
        return CATEGORIES.clone();
    }
}