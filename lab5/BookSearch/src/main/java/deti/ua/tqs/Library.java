package deti.ua.tqs;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Library {
    private final List<Book> store = new ArrayList<>();

    public void addBook(final Book book) {
        store.add(book);
    }

    public List<Book> findBooks(LocalDate from, LocalDate to) {

        List<Book> foundBooks = new ArrayList<>();

        for (Book book : store) {
            System.out.println(book.getPublished() + " " + from + " " + to);
            System.out.println(book.getPublished().isAfter(from) + " " + book.getPublished().isBefore(to));
            if (book.getPublished().isAfter(from) && book.getPublished().isBefore(to))
                foundBooks.add(book);

        }

        return foundBooks;
    }

    public List<Book> findBooksByAuthor(String author) {
        List<Book> foundBooks = new ArrayList<>();

        for (Book book : store) {
            if (book.getAuthor().equals(author))
                foundBooks.add(book);
        }

        return foundBooks;
    }

    public List<Book> findBooksByTitle(String title) {
        List<Book> foundBooks = new ArrayList<>();

        for (Book book : store) {
            if (book.getTitle().equals(title))
                foundBooks.add(book);
        }

        return foundBooks;
    }

    public void removeBook(Book book) {
        try {
            store.remove(book);
        } catch (Exception NullPointerException) {
            System.out.println("The book you are trying to remove does no exists");
        }

    }
}
