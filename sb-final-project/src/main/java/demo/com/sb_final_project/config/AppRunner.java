package demo.com.sb_final_project.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import demo.com.sb_final_project.entity.StockListEntity;
import demo.com.sb_final_project.infra.RedisHelper;
import demo.com.sb_final_project.repository.StockListReposiotry;
import demo.com.sb_final_project.service.StockListService;

@Component
@EnableScheduling
public class AppRunner implements CommandLineRunner{
  
  @Autowired
  StockListService stockListService;

  @Autowired
  StockListReposiotry stockListReposiotry;

  @Autowired
  ApiConnection apiConnection;

  @Value (value="${api.yahoo-finance.domain}")
  private String domain;

  @Autowired
  private RedisHelper redisHelper;


  @Override
  public void run(String... args){
    if(stockListReposiotry.count() ==0){
      stockListService.save("0388.HK","HKEX");
      stockListService.save("9988.HK", "BABA-SW");
      stockListService.save("0700.HK", "TENCENT");
      stockListService.save("0005.HK", "HSBC HOLDINGS");
      stockListService.save("0001.HK", "CKH HOLDINGS");
      stockListService.save("0066.HK", "MTR CORPORATION");
      stockListService.save("2269.HK", "WUXI BIO");
      stockListService.save("2800.HK", "TRACKER FUND");
      stockListService.save("1211.HK", "BYD COMPANY");
      stockListService.save("0020.HK", "SENSETIME-W");
      stockListService.save("0857.HK", "PETROCHINA");
      stockListService.save("0883.HK", "CNOOC");
      stockListService.save("0728.HK", "CHINA TELECOM");
      stockListService.save("0762.HK", "CHINA UNICOM");
      stockListService.save("0020.HK", "SINOPEC CORP");
    }

    redisHelper.delete("stockSystemDate");
  }  

}
