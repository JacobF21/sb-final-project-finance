package demo.com.sb_final_project.config;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import demo.com.sb_final_project.entity.StockListEntity;
import demo.com.sb_final_project.service.StockListService;
import demo.com.sb_final_project.service.StockService;

@Component
public class AppScheduler {
  
  @Autowired
  StockService stockService;

  @Autowired
  StockListService stockListService;


  @Scheduled (cron = "0 */5 * ? * MON-FRI")
  public void runTask(){
    List<StockListEntity> temp = stockListService.getStockList();
    for(int i =0;i<temp.size();i++){
      stockService.getStockData(temp.get(i).getSymbol(),"5M");
    }
  }
}
