package demo.com.sb_final_project.service.Impl;


import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import com.fasterxml.jackson.core.JsonProcessingException;
import demo.com.sb_final_project.config.ApiConnection;
import demo.com.sb_final_project.entity.StockListEntity;
import demo.com.sb_final_project.entity.TStockQuoteYahooEntity;
import demo.com.sb_final_project.infra.RedisHelper;
import demo.com.sb_final_project.mapper.StockInfoMapper;
import demo.com.sb_final_project.model.ApiResponse;
import demo.com.sb_final_project.repository.StockListReposiotry;
import demo.com.sb_final_project.repository.TStockReposiotory;
import demo.com.sb_final_project.service.StockListService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class StockListServiceImpl implements StockListService{

  @Autowired
  StockListReposiotry stockListReposiotry;

  @Value (value="${api.yahoo-finance.domain}")
  private String domain;

  @Autowired
  private ApiConnection apiConnection;

  @Autowired
  private RestTemplate restTemplate;

  @Autowired
  private RedisHelper redisHelper;

  @Override
  public StockListEntity save(String symbol, String name){
    // Optional <StockListEntity> temp = stockListReposiotry.findById(symbol);
    // if(!temp.isPresent()){
    return stockListReposiotry.save(new StockListEntity(symbol,name));
    // }
    // throw new IllegalArgumentException();
  }

  @Override
  public ApiResponse get(String symbol){
    
      HttpHeaders headers = new HttpHeaders();
      String cookie = apiConnection.getCookie();
      String crumb = apiConnection.getCrumb(cookie);
      headers.add("Cookie", cookie);
      String url = UriComponentsBuilder.fromUriString(domain)
      .buildAndExpand(symbol, crumb)
      .toUriString();  
      HttpEntity<ApiResponse> requestEntity = new HttpEntity<>(headers);
      ResponseEntity<ApiResponse> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, ApiResponse.class);
      ApiResponse responseBody = responseEntity.getBody();
      return responseBody;
    // }
    // throw new IllegalArgumentException();
  }

  @Override
  public List<StockListEntity> getStockList(){
    try{
      StockListEntity[] stocklist = redisHelper.get("stock-list", StockListEntity[].class);
      if (stocklist != null) {
        return List.of(stocklist);
      }
      List<StockListEntity> temp = stockListReposiotry.findAll();
      StockListEntity[] redis = new StockListEntity[temp.size()];
      for(int i=0;i<temp.size();i++){
        redis[i] = temp.get(i);
      }
      this.redisHelper.set("stock-list",redis, Duration.ofHours(24));
      return stockListReposiotry.findAll();
    } catch(JsonProcessingException e){
      throw new RuntimeException("Error processing JSON", e);
    }
  }


 
}
