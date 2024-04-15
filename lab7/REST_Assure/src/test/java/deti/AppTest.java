package deti;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import org.junit.jupiter.api.Test;

import static org.hamcrest.core.IsEqual.equalTo;


public class AppTest{
    @Test
    public void testallToDos(){
       when().get("https://jsonplaceholder.typicode.com/todos").then().statusCode(200);
        
    }

    @Test
    public void testToDo4(){
        when().get("https://jsonplaceholder.typicode.com/todos/4").then().statusCode(200).body("title", equalTo("et porro tempora"));
    }

    @Test
    public void testItems(){
        when().get("https://jsonplaceholder.typicode.com/todos").then().statusCode(200)
                .body("id", hasItems(198,199));
    }

    @Test
    public void testResponseTime(){
        when().get("https://jsonplaceholder.typicode.com/todos").then().statusCode(200)
                .time(lessThan(2000L));
    }
}
