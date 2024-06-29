package demo.com.sb_final_project.service;

import java.util.List;
import demo.com.sb_final_project.entity.StockListEntity;
import demo.com.sb_final_project.entity.TStockQuoteYahooEntity;
import demo.com.sb_final_project.model.dto.StockSystemDate;
import demo.com.sb_final_project.model.YahooHistoryData;
import demo.com.sb_final_project.model.dto.FiveMinData;

public interface StockService {
    
  public TStockQuoteYahooEntity getStockData(String symbol, String type);
  
  public StockSystemDate getSystemDate(String symbol);

  public List<StockSystemDate> getAllSystemDate();

  public FiveMinData getFiveMinsData(String Symbol);

  public List<YahooHistoryData> getHistoryData();
}
