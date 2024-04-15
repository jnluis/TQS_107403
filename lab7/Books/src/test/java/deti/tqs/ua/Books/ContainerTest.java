package deti.tqs.ua.Books;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.Assertions.assertThat;

@Testcontainers
@SpringBootTest
public class ContainerTest {

    @Autowired
    private BookRepo bookRepo;

    @Container
    @Order(1)
    public static PostgreSQLContainer container = new PostgreSQLContainer("postgres:14")
            .withUsername("test")
            .withPassword("test")
            .withDatabaseName("test");

    @DynamicPropertySource
    @Order(2)
    static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", container::getJdbcUrl);
        registry.add("spring.datasource.password", container::getPassword);
        registry.add("spring.datasource.username", container::getUsername);
    }

    @Test
    @DisplayName("Test add Book")
    @Order(3)
    void testAddBook() {
        Book book = new Book();
        book.setId(1);
        book.setTitle("Test Book");
        book.setAuthor("Test Author");
        book.setIsbn("1234567890");
        book.setPublisher("Test Publisher");
        book.setYear(2021);
        book.setPrice(10);
        bookRepo.save(book);

        Book book1 = bookRepo.findById(Integer.valueOf("1")).get();
        assertThat(book1.getTitle()).isEqualTo("Test Book");
        assertThat(book1.getYear()).isEqualTo(2021);
    }

    @Test
    @DisplayName("Test Update Book")
    @Order(3)
    void testUpdateBook() {
        Book book = new Book();
        book.setId(1);
        book.setTitle("Test Book");
        book.setAuthor("Test Author");
        book.setIsbn("1234567890");
        book.setPublisher("Test Publisher");
        book.setYear(2021);
        book.setPrice(10);
        bookRepo.save(book);

        Book book1 = bookRepo.findById(Integer.valueOf("1")).get();
        assertThat(book1.getTitle()).isEqualTo("Test Book");
        assertThat(book1.getYear()).isEqualTo(2021);

        book1.setTitle("Updated Test Book");
        bookRepo.save(book1);

        Book book2 = bookRepo.findById(Integer.valueOf("1")).get();
        assertThat(book2.getTitle()).isEqualTo("Updated Test Book");
    }

    @Test
    @DisplayName("Test Delete Book")
    @Order(3)
    void testDelBook() {
        Book book = new Book();
        book.setId(123456);
        book.setTitle("Test Book");
        book.setAuthor("Test Author");
        book.setIsbn("1234567890");
        book.setPublisher("Test Publisher");
        book.setYear(2021);
        book.setPrice(10);
        bookRepo.save(book);

        bookRepo.deleteById(123456);

        assertThat(bookRepo.findById(123456)).isEmpty();
    }

    @Test
    @DisplayName("Test get book inserted in .sql")
    @Order(6)
    void testget() {

        Book result = bookRepo.findById(2).get();

        assertThat(result.getTitle()).isEqualTo("To Kill a Mockingbird");
    }

}
