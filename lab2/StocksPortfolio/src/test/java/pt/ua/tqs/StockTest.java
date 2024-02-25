package pt.ua.tqs;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;

@ExtendWith(MockitoExtension.class)
public class StockTest {

    @InjectMocks
    StocksPortfolio portfolio;

    @Mock
    IStockmarketService stockmarketService;

    @Test
    void testTotalValue(){
        when(stockmarketService.lookUpPrice("AMZ")).thenReturn(1000.0);
        //when(stockmarketService.lookUpPrice("RIVN")).thenReturn(12.45);
        when(stockmarketService.lookUpPrice("LCID")).thenReturn(3.08);

        portfolio.addStock(new Stock("AMZ", 2));
        portfolio.addStock(new Stock("LCID", 1));

        //assertEquals(2003.08, portfolio.totalValue());
        assertThat(portfolio.totalValue(),equalTo(2003.08) );
        verify(stockmarketService, times(2)).lookUpPrice(anyString());
    }
}
