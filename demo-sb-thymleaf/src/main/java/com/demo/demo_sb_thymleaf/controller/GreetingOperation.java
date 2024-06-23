package com.demo.demo_sb_thymleaf.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import com.demo.demo_sb_thymleaf.model.CoinData;

public interface GreetingOperation {
  @GetMapping(value = "/hello")
  String hello();

  @GetMapping(value = "/coin-data")
  List<CoinData> fetchCoinData();

  
}
