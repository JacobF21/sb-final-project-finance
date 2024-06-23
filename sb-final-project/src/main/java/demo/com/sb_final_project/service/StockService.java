package demo.com.sb_final_project.service;

import demo.com.sb_final_project.entity.TStockQuoteYahooEntity;

public interface StockService {
    
  public TStockQuoteYahooEntity getStockData(String symbol, String type);
}
