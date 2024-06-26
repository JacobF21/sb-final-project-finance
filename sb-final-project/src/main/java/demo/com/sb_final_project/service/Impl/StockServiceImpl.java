package demo.com.sb_final_project.service.Impl;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
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
import demo.com.sb_final_project.mapper.FiveMinsDataMapper;
import demo.com.sb_final_project.mapper.StockInfoMapper;
import demo.com.sb_final_project.mapper.StockSystemDateMapper;
import demo.com.sb_final_project.model.ApiResponse;
import demo.com.sb_final_project.model.dto.FiveMinData;
import demo.com.sb_final_project.model.dto.StockSystemDate;
import demo.com.sb_final_project.repository.StockListReposiotry;
import demo.com.sb_final_project.repository.TStockReposiotory;
import demo.com.sb_final_project.service.StockService;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class StockServiceImpl implements StockService{
    
  @Autowired
  private StockInfoMapper stockInfoMapper;

  @Autowired
  private FiveMinsDataMapper fiveMinsDataMapper;

  @Autowired
  private TStockReposiotory tStockReposiotory;

  @Autowired
  private ApiConnection apiConnection;

  @Autowired
  private RestTemplate restTemplate;

  @Value (value="${api.yahoo-finance.domain}")
  private String domain;

  @Autowired
  private RedisHelper redisHelper;

  @Autowired
  private StockSystemDateMapper stockSystemDateMapper;
  
  @Override
  public TStockQuoteYahooEntity getStockData(String symbol, String type){
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
    TStockQuoteYahooEntity result = stockInfoMapper.map(responseBody,symbol,type);
    tStockReposiotory.save(result);
    return result;
  }

  @Override
  public StockSystemDate getSystemDate(String symbol){
    try{
      StockSystemDate stockSystemDate = redisHelper.get("SYSDATE-"+symbol,StockSystemDate.class);
      if(stockSystemDate != null){
        return stockSystemDate;
      }
      StockSystemDate redis = tStockReposiotory.findMaxMarketTimeBySymbol(symbol);
      redisHelper.set("SYSDATE-"+symbol,redis,Duration.ofHours(24));
      return redis;
    }catch(JsonProcessingException e){
      throw new RuntimeException("Error processing JSON", e);
    }

  }

  @Override
  public List<StockSystemDate> getAllSystemDate(){
    try{
      StockSystemDate[] stockSystemDateList = redisHelper.get("stockSystemDate", StockSystemDate[].class);
      if(stockSystemDateList != null){
        return List.of(stockSystemDateList);
      }
      List<StockSystemDate> redis = tStockReposiotory.findMaxMarketTimeBySymbol().stream().map(stockSystemDateMapper::map).collect(Collectors.toList());
      this.redisHelper.set("stockSystemDate",redis, Duration.ofHours(24));
        return redis;
    }
    catch (JsonProcessingException e){
      throw new RuntimeException("Error processing JSON", e);
    }
  }

  @Override
  public FiveMinData getFiveMinsData(String symbol){
    String date = this.getSystemDate(symbol).getSystemDate();
    log.info(date);
    List<TStockQuoteYahooEntity> temp = tStockReposiotory.findBySymbolAndAPIDatetimeOrderByRegularMarketUnix(symbol, date);
    return fiveMinsDataMapper.map(temp,temp.get(0).getRegularMarketUnix().toString());
  }

}
