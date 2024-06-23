package demo.com.sb_final_project.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import demo.com.sb_final_project.entity.StockListEntity;
import demo.com.sb_final_project.repository.StockListReposiotry;
import demo.com.sb_final_project.service.StockListService;

@Component
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
  private RestTemplate restTemplate;

  @Override
  public void run(String... args){
    if(stockListReposiotry.count() ==0){
      stockListService.save("0388.HK","HKEX");
      stockListService.save("9988.HK", "BABA-SW");
    }
  }  

}
