package demo.com.sb_final_project.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import demo.com.sb_final_project.service.StockService;

@Component
public class AppScheduler {
  
  @Autowired
  StockService stockService;


  @Scheduled (cron = "0 */ * ? * MON-FRI")
  public void runTask(){
    stockService.getStockData("0388.HK","5M");
  }
}
