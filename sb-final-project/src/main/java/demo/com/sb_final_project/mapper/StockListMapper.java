package demo.com.sb_final_project.mapper;

import org.springframework.stereotype.Component;
import demo.com.sb_final_project.entity.StockListEntity;
import demo.com.sb_final_project.model.ApiResponse;

@Component
public class StockListMapper {
  public StockListEntity map(ApiResponse apiResponse){
    return StockListEntity.builder()
    .symbol(apiResponse.getQuoteResponse().getResult().get(0).getSymbol())
    .shortName(apiResponse.getQuoteResponse().getResult().get(0).getShortName())
    .build();
  }
}
