package demo.com.sb_final_project.mapper;

import java.util.List;
import org.springframework.stereotype.Component;
import demo.com.sb_final_project.entity.TStockQuoteYahooEntity;
import demo.com.sb_final_project.model.dto.FiveMinData;

@Component
public class FiveMinsDataMapper {
  public FiveMinData map(List<TStockQuoteYahooEntity> list, String systemDate){
    return FiveMinData.builder().data(list).regularMarketTime(systemDate).build();
  }
}
