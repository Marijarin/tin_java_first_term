package edu.hw3;

import edu.hw3.task6.Stock;
import edu.hw3.task6.StockMarket;
import edu.hw3.task6.StockMarketImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task6Test {
    @Test
    @DisplayName("different stocks")
    void correctStocks() {
        StockMarket s = new StockMarketImpl();
        s.add(new Stock("DFG", 1.0));
        s.add(new Stock("AAA", 2.0));
        s.add(new Stock("YHY", 1.8));

        var s1 = s.mostValuableStock();

        assertThat(s1).isEqualTo(new Stock("AAA", 2.0));
    }

    @Test
    @DisplayName("same stocks")
    void correctStocksSame() {
        StockMarket s = new StockMarketImpl();
        s.add(new Stock("DFG", 2.0));
        s.add(new Stock("AAA", 2.0));
        s.add(new Stock("YHY", 2.0));

        var s1 = s.mostValuableStock();

        assertThat(s1).isEqualTo(new Stock("DFG", 2.0));
    }

    @Test
    @DisplayName("empty stocks")
    void correctStocksEmpty() {
        StockMarket s = new StockMarketImpl();

        var s1 = s.mostValuableStock();

        assertThat(s1).isEqualTo(null);
    }
}
