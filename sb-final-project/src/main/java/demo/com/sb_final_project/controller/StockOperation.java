package demo.com.sb_final_project.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import demo.com.sb_final_project.entity.HKStockMarketCapEntity;
import demo.com.sb_final_project.entity.StockListEntity;
import demo.com.sb_final_project.entity.TStockQuoteYahooEntity;
import demo.com.sb_final_project.model.ApiResponse;
import demo.com.sb_final_project.model.YahooHistoryData;
import demo.com.sb_final_project.model.dto.StockSystemDate;
import demo.com.sb_final_project.model.dto.FiveMinData;
import demo.com.sb_final_project.model.dto.FiveMinPercentChange;

public interface StockOperation{
  @RequestMapping(value="/yahoo_finance/get")
  public ApiResponse get(@RequestParam String symbol);

  @RequestMapping(value ="/stock_list")
  public List<StockListEntity> getStockList();

  @RequestMapping(value ="/all_stock_in_hk")
  public List<StockListEntity> getAllStockInHK();

  @RequestMapping(value ="/stock_data")
  public TStockQuoteYahooEntity getStockData(@RequestParam String symbol, @RequestParam String type);

  @RequestMapping(value ="/system_date")
  public StockSystemDate getSystemDate(@RequestParam String symbol);

  @RequestMapping(value ="/all_system_date")
  public List<StockSystemDate> getAllSystemDate();

  @RequestMapping(value ="/get_five_mins_data")
  public FiveMinData getFiveMinsData(String symbol);

  @RequestMapping(value ="/history_data")
  public List<YahooHistoryData> getHistoryDatas();

  @RequestMapping(value = "/top_10_mktcap")
  public List<HKStockMarketCapEntity> getTopTenMarketCap();

  @RequestMapping(value ="/latestPercentChange")
  public List<FiveMinPercentChange> getLatestRegularMarketChangePercent();
  
}
