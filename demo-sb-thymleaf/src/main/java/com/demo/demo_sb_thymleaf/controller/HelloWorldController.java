package com.demo.demo_sb_thymleaf.controller;

import org.springframework.web.bind.annotation.RestController;
import com.demo.demo_sb_thymleaf.model.CoinData;
import com.demo.demo_sb_thymleaf.service.ApiService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

//API
@RestController
@RequestMapping(value="v1")
public class HelloWorldController implements GreetingOperation{
  
  @Autowired
  ApiService apiService;

  @Override
  public String hello(){
    return "on99";
  }

  @Override
  public List<CoinData> fetchCoinData(){
    return apiService.fetchCoinData();
  }
}
