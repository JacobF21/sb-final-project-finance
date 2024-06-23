package demo.com.sb_final_project.controller;

import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import demo.com.sb_final_project.entity.StockListEntity;
import demo.com.sb_final_project.entity.TStockQuoteYahooEntity;
import demo.com.sb_final_project.model.ApiResponse;

public interface StockOperation{
  @RequestMapping(value="/yahoo_finance/get")
  public ApiResponse get(@RequestParam String symbol);

  @RequestMapping(value ="/stock_list")
  public List<StockListEntity> getStockList();

  @RequestMapping(value ="/stock_data")
  public TStockQuoteYahooEntity getStockData(@RequestParam String symbol, @RequestParam String type);
}
