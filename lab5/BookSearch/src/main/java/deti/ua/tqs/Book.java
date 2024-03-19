package deti.ua.tqs;

import java.time.LocalDate;
import java.util.Objects;

public class Book {
    private final String title;
    private final String author;
    private final LocalDate published;

    public Book(String title, String author, LocalDate published) {
        this.title = title;
        this.author = author;
        this.published= published;
    }
    public String getTitle(){
        return title;
    }
    public String getAuthor() {
        return author;
    }
    public LocalDate getPublished(){
        return published;
    }
    // constructors, getter, setter ommitted

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        Book book = (Book) o;
        return Objects.equals(title, book.title) &&
               Objects.equals(author, book.author) &&
               Objects.equals(published, book.published);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, author, published);
    }
}