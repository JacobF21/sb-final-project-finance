package demo.com.sb_final_project.service;

import java.util.List;
import demo.com.sb_final_project.entity.TStockQuoteYahooEntity;
import demo.com.sb_final_project.model.dto.SystemDate;

public interface StockService {
    
  public TStockQuoteYahooEntity getStockData(String symbol, String type);
  
  public SystemDate getSystemDate(String symbol);

  public List<TStockQuoteYahooEntity> getAllSystemDate();
}
