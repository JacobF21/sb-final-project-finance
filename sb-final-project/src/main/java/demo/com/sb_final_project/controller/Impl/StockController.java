package demo.com.sb_final_project.controller.Impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import demo.com.sb_final_project.controller.StockOperation;
import demo.com.sb_final_project.entity.StockListEntity;
import demo.com.sb_final_project.entity.TStockQuoteYahooEntity;
import demo.com.sb_final_project.model.ApiResponse;
import demo.com.sb_final_project.service.StockListService;
import demo.com.sb_final_project.service.StockService;

@RestController
public class StockController implements StockOperation {

  @Autowired
  StockListService stockListService;

  @Autowired
  StockService stockService;

    @Override
    public ApiResponse get(String symbol){
      return stockListService.get(symbol);
    }

    @Override
    public List<StockListEntity> getStockList(){
      return stockListService.getStockList();
    }

    @Override
    public TStockQuoteYahooEntity getStockData(String symbol, String type){
      return stockService.getStockData(symbol, type);
    }
}