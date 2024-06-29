package demo.com.db_final_project_frontend.service;

import java.util.List;
import demo.com.db_final_project_frontend.model.StockSymbol;
import demo.com.db_final_project_frontend.model.APIStockDataDTO.FiveMinData;

public interface APIservice {

  public List<FiveMinData> fetchStockFiveMinData(String symbol);
  public List<StockSymbol> fetchStockSymbols();
  
}
