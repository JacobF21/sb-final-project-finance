package demo.com.sb_final_project.mapper;

import org.springframework.stereotype.Component;
import demo.com.sb_final_project.entity.HKStockMarketCapEntity;
import demo.com.sb_final_project.model.ApiResponse;

@Component
public class HKStockMarketCapMapper {
  
  public HKStockMarketCapEntity map(ApiResponse apiResponse){
    return HKStockMarketCapEntity.builder()
                          .symbol(apiResponse.getQuoteResponse().getResult().get(0).getSymbol())
                          .marketCap(apiResponse.getQuoteResponse().getResult().get(0).getMarketCap())
                          .shortName(apiResponse.getQuoteResponse().getResult().get(0).getShortName())
                          .build();
  }
}
