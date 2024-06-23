package com.demo.demo_sb_thymleaf.view;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.demo.demo_sb_thymleaf.controller.GreetingOperation;
import com.demo.demo_sb_thymleaf.controller.HelloWorldController;
import com.demo.demo_sb_thymleaf.model.CoinData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestParam;


//Thymeleaf
//1. Server Side Rendering
//2. 


//Return HTML
@Slf4j
@Controller
public class ABCViewController {
  
  @Autowired
  private GreetingOperation greetingOperation;

  @GetMapping(value="/abc")
  public String abc(Model model){
    model.addAttribute("message", greetingOperation.hello());
    return "abc"; // abc.html
  }

  @GetMapping(value = "/coin-data")
  public String abc2(Model model) {
    List<CoinData> coinDataList = greetingOperation.fetchCoinData();
    // int i =0;
    // for(CoinData coinData : coinDataList){
    //   log.info("Loop" + i++);
    //   model.addAttribute("coinData.id", coinData.getId());
    //   model.addAttribute("coinData.image", coinData.getImage());
    //   model.addAttribute("coinData.currentPrice", coinData.getCurrent_price());
    // }
    // log.info(model.toString());
    model.addAttribute("coinDataList", coinDataList);
    model.addAttribute("message", greetingOperation.hello());
    return "abc";
  }
  


}
