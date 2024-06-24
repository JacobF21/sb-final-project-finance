package demo.com.sb_final_project.service;

import java.util.List;
import demo.com.sb_final_project.entity.StockListEntity;
import demo.com.sb_final_project.entity.TStockQuoteYahooEntity;
import demo.com.sb_final_project.model.ApiResponse;


public interface StockListService {
  
  public StockListEntity save(String symbol, String name);
  
  public ApiResponse get(String symbol);

  public List<StockListEntity> getStockList();
   
}
