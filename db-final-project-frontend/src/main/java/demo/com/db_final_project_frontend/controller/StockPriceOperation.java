package demo.com.db_final_project_frontend.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import demo.com.db_final_project_frontend.model.StockSymbol;
import demo.com.db_final_project_frontend.model.APIStockDataDTO.FiveMinData;

public interface StockPriceOperation {
  
  @GetMapping(value = "/five-minute")
  public List<FiveMinData> getFiveMinData(String symbol);

  @GetMapping(value = "/stock_list")
  public List<StockSymbol> getStockList();

}
