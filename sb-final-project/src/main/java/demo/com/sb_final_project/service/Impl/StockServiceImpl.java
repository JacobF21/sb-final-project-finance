package demo.com.sb_final_project.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import demo.com.sb_final_project.config.ApiConnection;
import demo.com.sb_final_project.entity.TStockQuoteYahooEntity;
import demo.com.sb_final_project.mapper.StockInfoMapper;
import demo.com.sb_final_project.model.ApiResponse;
import demo.com.sb_final_project.repository.TStockReposiotory;
import demo.com.sb_final_project.service.StockService;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class StockServiceImpl implements StockService{
    
  @Autowired
  private StockInfoMapper stockInfoMapper;

  @Autowired
  private TStockReposiotory tStockReposiotory;

  @Autowired
  private ApiConnection apiConnection;

  @Autowired
  private RestTemplate restTemplate;

  @Value (value="${api.yahoo-finance.domain}")
  private String domain;
  
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
}
