package pt.ua.tqs;

import java.util.ArrayList;
import java.util.List;

public class StocksPortfolio{

    private IStockmarketService stockmarket;
    private List<Stock> stocks;
    public StocksPortfolio(IStockmarketService sm){
        this.stockmarket = sm;
        stocks = new ArrayList<>();
    }

    public void addStock(Stock stock){
        stocks.add(stock);
    }

    public double totalValue(){
        double total = 0.0;
        for(Stock s: stocks)
            total += stockmarket.lookUpPrice(s.getLabel()) *  s.getQuantity();
        return total;
    }
}

