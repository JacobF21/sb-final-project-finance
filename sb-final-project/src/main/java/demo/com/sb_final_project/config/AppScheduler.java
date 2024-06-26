package demo.com.sb_final_project.config;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.core.JsonProcessingException;
import demo.com.sb_final_project.entity.StockListEntity;
import demo.com.sb_final_project.infra.RedisHelper;
import demo.com.sb_final_project.service.StockListService;
import demo.com.sb_final_project.service.StockService;

@Component
public class AppScheduler {
  
  @Autowired
  StockService stockService;

  @Autowired
  StockListService stockListService;

  @Autowired
  private RedisHelper redisHelper;


  @Scheduled (cron = "0 */5 * ? * MON-FRI")
  public void fiveMinsData(){
    List<StockListEntity> temp = stockListService.getStockList();
    for(int i =0;i<temp.size();i++){
      stockService.getStockData(temp.get(i).getSymbol(),"5M");
    }
  }

  @Scheduled (cron = "0 55 8 * * *")
  public void systemDateRedis(){
    redisHelper.delete("stockSystemDate");
  }

  @Scheduled (cron = "0 56 8 * * *")
  public void delStockListRedis(){
    redisHelper.delete("stock-list");
    try{
      redisHelper.set("stock-list", stockListService.getStockList());
    } catch(JsonProcessingException e){
      throw new RuntimeException("Error:Fail to parse Json");
    }
  }


}
