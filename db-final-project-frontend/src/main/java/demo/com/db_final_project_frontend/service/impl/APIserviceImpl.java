package demo.com.db_final_project_frontend.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import demo.com.db_final_project_frontend.model.APIStockDataDTO;
import demo.com.db_final_project_frontend.model.FiveMinPriceChange;
import demo.com.db_final_project_frontend.model.APIStockDataDTO.FiveMinData;
import demo.com.db_final_project_frontend.model.MarketCap;
import demo.com.db_final_project_frontend.model.StockSymbol;
import demo.com.db_final_project_frontend.service.APIservice;

@Service
public class APIserviceImpl implements APIservice{
  
@Autowired
RestTemplate restTemplate;

  public List<FiveMinData> fetchStockFiveMinData(String symbol){
    String apiUrl= "http://jacobfinalproject.asuscomm.com/get_five_mins_data?symbol="+symbol;
    APIStockDataDTO response = restTemplate.getForObject(apiUrl, APIStockDataDTO.class);
    return response.getData();
  }
  
  public List<StockSymbol> fetchStockSymbols(){
    String apiUrl= "http://jacobfinalproject.asuscomm.com/stock_list";
    StockSymbol[] response = restTemplate.getForObject(apiUrl, StockSymbol[].class);
    return List.of(response);
  }

   public List<MarketCap> fetchTopTenMarketCaps(){
    String apiUrl= "http://jacobfinalproject.asuscomm.com/top_10_mktcap";
    MarketCap[] response = restTemplate.getForObject(apiUrl, MarketCap[].class);
    return List.of(response);
   }

   public List<FiveMinPriceChange> fetchFiveMinPriceChanges(){
    String apiUrl= "http://jacobfinalproject.asuscomm.com/latestPercentChange";
    FiveMinPriceChange[] response = restTemplate.getForObject(apiUrl, FiveMinPriceChange[].class);
    return List.of(response);
   }


}
