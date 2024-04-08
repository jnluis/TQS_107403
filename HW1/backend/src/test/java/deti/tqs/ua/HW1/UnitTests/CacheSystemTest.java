package deti.tqs.ua.HW1.UnitTests;

import deti.tqs.ua.HW1.service.CurrExchangeService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@Disabled
public class CacheSystemTest {

    private final static CurrExchangeService currencyExchangeService = new CurrExchangeService(1500); // 1.5 seconds of cache ttl

    @Test
    void testAfterTTLExpire() throws Exception {

        currencyExchangeService.exchange("EUR", "USD");
        assertThat(currencyExchangeService.isCacheValid()).isTrue();

        Thread.sleep(4000);
        assertThat(currencyExchangeService.isCacheValid()).isFalse();
    }

    @Test
    void testBeforeTTLExpire() throws Exception {

        currencyExchangeService.exchange("EUR", "USD");
        assertThat(currencyExchangeService.isCacheValid()).isTrue();

        Thread.sleep(1000);

        assertThat(currencyExchangeService.isCacheValid()).isTrue();
    }

    @Test
    void testListCurrencies() throws Exception  {
        assertThat(currencyExchangeService.listCurrencies())
                .contains("EUR","USD");
    }
}
