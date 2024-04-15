package deti.tqs.ua.Books;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "book")
@Getter
@Setter
@NoArgsConstructor
public class Book {
    @Id
    private long id;
    private String title;
    private String author;
    private String isbn;
    private String publisher;
    private Integer year;
    private Integer price;

}
