package deti.tqs.ua.ControllerTests;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = "spring.profiles.active=test")
@AutoConfigureTestDatabase
class CurrExchangeControllerTestIT {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void whenListCurrencies_thenGetResponse() {
        ResponseEntity<String> response = restTemplate.getForEntity("/api/currencies/list", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isInstanceOf(String.class);
    }

    @Test
    void whenDoExchange_thenGetResponse() {
        ResponseEntity<String> response = restTemplate.getForEntity("/api/currencies/exchange?from=EUR&to=USD",
                String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(Double.parseDouble(response.getBody())).isInstanceOf(Double.class);
    }
}
