package demo.com.sb_final_project.mapper;

import org.springframework.stereotype.Component;
import demo.com.sb_final_project.entity.TStockQuoteYahooEntity;
import demo.com.sb_final_project.model.dto.StockSystemDate;

@Component
public class StockSystemDateMapper {
  
  public StockSystemDate map(TStockQuoteYahooEntity tStockQuoteYahooEntity){
    
    return StockSystemDate.builder()
                          .symbol(tStockQuoteYahooEntity.getSymbol())
                          .systemDate(tStockQuoteYahooEntity.getAPIDatetime())
                          .build();
  }
}
