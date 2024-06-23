package com.demo.demo_sb_thymleaf.service;

import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.demo.demo_sb_thymleaf.model.CoinData;

@Service
public class ApiService {
  
  private final RestTemplate restTemplate;

  @Autowired
  public ApiService(RestTemplate restTemplate){
    this.restTemplate = restTemplate;
  }

  public List<CoinData> fetchCoinData(){
    String apiUrl = 
    "https://api.coingecko.com/api/v3/coins/markets?ids=bitcoin,ethereum,tether&vs_currency=usd";
    CoinData[] response = restTemplate.getForObject(apiUrl,CoinData[].class);
    return Arrays.asList(response);
  }
}
