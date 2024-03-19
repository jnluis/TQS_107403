package deti.ua.tqs;

import static java.lang.invoke.MethodHandles.lookup;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.slf4j.LoggerFactory.getLogger;

import org.slf4j.Logger;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.ParameterType;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.DataTableType;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class BookSteps {

    static final Logger log = getLogger(lookup().lookupClass());

    private Library library;
    List<Book> foundedBooks;

    /*
    create a registered type named iso8601Date to map a string pattern from the feature
    into a custom datatype. Extracted parameters should be strings.
 */
    @ParameterType("([0-9]{4})-([0-9]{2})-([0-9]{2})")
    public LocalDate iso8601Date(String year, String month, String day){
        return Utils.localDateFromDateParts(year, month, day);
    }

    /*
     * load a data table from the feature (tabular format) and call this method
     * for each row in the table. Injected parameter is a map with column name --> value
     */
    @DataTableType
    public Book bookEntry(Map<String, String> tableEntry){
        return new Book(
                tableEntry.get("title"),
                tableEntry.get("author"),
                Utils.isoTextToLocalDate( tableEntry.get("published") ) );
    }

    @Given("I have the following books in the library")
    public void setup(DataTable table) {
        List<Map<String, String>> rows = table.asMaps(String.class, String.class);
        library = new Library();    
        for (Map<String, String> row : rows) {
            Book book = bookEntry(row);
            library.addBook(book);
        }
    }

    @When("the customer searches for books published between {iso8601Date} and {iso8601Date}")
    public void searchBooksByYear(LocalDate from, LocalDate to) {
        log.info("Searching for books published between {} and {}", from, to);
        foundedBooks = library.findBooks(from, to);
    }

    @When("the customer searches for the book with the author {string}")
    public void searchBooksByAuthor(String author) {
        log.info("Searching for books published by {}",author);
        foundedBooks = library.findBooksByAuthor(author);
    }

    @When("the customer searches for the book with the title {string}")
    public void searchBooksByTitle(String title) {
        log.info("Searching for books with title {}", title);
        foundedBooks = library.findBooksByTitle(title);
    }


    @Then("{int} books should have been found")
    public void verifyAmountOfBooks(int amount, DataTable books) {
        assertEquals(amount, foundedBooks.size());
        List<Map<String, String>> rows = books.asMaps(String.class, String.class);
        for (int i = 0; i < amount; i++) {
            Book book = foundedBooks.get(i);
            Map<String, String> row = rows.get(i);
            Book expectedBook = bookEntry(row);
            assertEquals(expectedBook, book);
        }
    }

}
