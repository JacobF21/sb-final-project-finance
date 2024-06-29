package demo.com.sb_final_project.model.dto;

import java.util.List;
import demo.com.sb_final_project.entity.TStockQuoteYahooEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FiveMinData {
  public String regularMarketTime;
  public List<TStockQuoteYahooEntity> data;
}
