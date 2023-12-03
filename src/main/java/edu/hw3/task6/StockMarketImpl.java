package edu.hw3.task6;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class StockMarketImpl implements StockMarket {

    private final List<Stock> stocks = new ArrayList<>();

    List<Stock> getStocks() {
        return stocks;
    }

    @Override
    public void add(Stock stock) {
        stocks.add(stock);
    }

    @Override
    public void remove(Stock stock) {
        stocks.remove(stock);
    }

    @Override
    public Stock mostValuableStock() {
        Queue<Stock> valuated = new PriorityQueue<>(Comparator.comparingDouble(Stock::price).reversed());
        valuated.addAll(stocks);
        return valuated.poll();
    }
}
