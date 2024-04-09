package deti.tqs.ua.HW1.ControllerTests;

import deti.tqs.ua.HW1.controller.CurrExchangeController;
import deti.tqs.ua.HW1.service.CurrExchangeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = CurrExchangeController.class,properties="spring.profiles.active=test")
public class CurrExchangeControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CurrExchangeService currService;

    @BeforeEach
    void setUp() throws Exception {
        when(currService.listCurrencies()).thenReturn(List.of("EUR", "USD", "GBP", "RUB"));
        when(currService.exchange("EUR", "USD")).thenReturn(1.08);
        when(currService.exchange("EUR", "GBP")).thenReturn(0.858);
        when(currService.exchange("EUR", "RUB")).thenReturn(100.34);
    }

    @Test
    void whenGetCurrencies_thenReturnCurrencies() throws Exception {
        mvc.perform(get("/api/currencies/list"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(4)))
                .andExpect(jsonPath("$[0]").value("EUR"));
    }

    @Test
    void whenExchangeEURtoUSD_thenReturnRate() throws Exception {
        mvc.perform(get("/api/currencies/exchange?from=EUR&to=USD"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(1.08));
    }
}
