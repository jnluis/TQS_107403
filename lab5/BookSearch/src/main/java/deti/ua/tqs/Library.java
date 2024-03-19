package deti.ua.tqs;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;
import java.util.stream.Collectors;

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
            if (book.getPublished().isAfter(from) && book.getPublished().isBefore(to)) foundBooks.add(book);
            
        }

        return foundBooks;
    }
    public List<Book> findBooksByTitle(String title) {
        return store.stream().filter( book -> {return title.equals(book.getTitle());}).sorted(Comparator.comparing(Book::getTitle).reversed()).collect(Collectors.toList());
    }

    public List<Book> findBooksByAuthor(String author) {
        return store.stream().filter( book -> {return author.equals(book.getAuthor());}).sorted(Comparator.comparing(Book::getAuthor).reversed()).collect(Collectors.toList());
    }

    public void removeBook(Book book) {
        try {
            store.remove(book);
        }catch (Exception NullPointerException){
            System.out.println("The book you are trying to remove does no exists");
        }

    }
}
