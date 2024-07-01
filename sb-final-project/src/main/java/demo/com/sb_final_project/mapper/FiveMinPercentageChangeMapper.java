package demo.com.sb_final_project.mapper;

import org.springframework.stereotype.Component;
import demo.com.sb_final_project.entity.TStockQuoteYahooEntity;
import demo.com.sb_final_project.entity.TStockQuoteYahooEntityPK;
import demo.com.sb_final_project.model.dto.FiveMinPercentChange;

@Component
public class FiveMinPercentageChangeMapper {
 
  public FiveMinPercentChange map(TStockQuoteYahooEntity tStockQuoteYahooEntity){
    return FiveMinPercentChange.builder()
    .symbol(tStockQuoteYahooEntity.getSymbol())
    .regularMarketChangePercent(tStockQuoteYahooEntity.getRegularMarketChangePercent())
    .build();
  }
}
