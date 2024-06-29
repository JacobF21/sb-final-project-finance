package demo.com.db_final_project_frontend.controller.Impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import demo.com.db_final_project_frontend.controller.StockPriceOperation;
import demo.com.db_final_project_frontend.model.StockSymbol;
import demo.com.db_final_project_frontend.model.APIStockDataDTO.FiveMinData;
import demo.com.db_final_project_frontend.service.APIservice;

@RestController
public class StockPriceController implements StockPriceOperation {
  
  @Autowired
  APIservice apIservice;

  @Override
  public List<FiveMinData> getFiveMinData(String symbol){
    return apIservice.fetchStockFiveMinData(symbol);
  }

  @Override
  public List<StockSymbol> getStockList(){
    return apIservice.fetchStockSymbols();
  }
}
