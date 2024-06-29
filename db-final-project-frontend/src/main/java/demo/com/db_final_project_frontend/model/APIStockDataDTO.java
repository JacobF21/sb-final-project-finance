package demo.com.db_final_project_frontend.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class APIStockDataDTO {
  public String regularMarketTime;
  public List<FiveMinData> data;


  @Getter
  @AllArgsConstructor
  @Builder
  public static class FiveMinData{
    private String symbol;
    private Long regularMarketUnix;
    private double regularMarketPrice;
    private double regularMarketChangePercent;
    private double bid;
    private int bidSize;
    private double ask;
    private int askSize;
    private String type;
    private String APIDatetime;
    private String MarketTime;
  }


}
