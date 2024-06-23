package demo.com.sb_final_project.mapper;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import org.springframework.stereotype.Component;
import demo.com.sb_final_project.entity.TStockQuoteYahooEntity;
import demo.com.sb_final_project.model.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import java.time.LocalDateTime;

@Slf4j
@Component
public class StockInfoMapper {
  
  public TStockQuoteYahooEntity map(ApiResponse apiResponse,String symbol, String type){
    
    long marketTimeUnix = apiResponse.getQuoteResponse().getResult().get(0).getRegularMarketTime();
    LocalDateTime marketDateTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(marketTimeUnix), ZoneId.systemDefault());


    return TStockQuoteYahooEntity.builder()
    .symbol(apiResponse.getQuoteResponse().getResult().get(0).getSymbol())
    .regularMarketUnix(apiResponse.getQuoteResponse().getResult().get(0).getRegularMarketTime())
    .regularMarketPrice(apiResponse.getQuoteResponse().getResult().get(0).getRegularMarketPrice())
    .regularMarketChangePercent(apiResponse.getQuoteResponse().getResult().get(0).getRegularMarketChangePercent())
    .bid(apiResponse.getQuoteResponse().getResult().get(0).getBid())
    .bidSize(apiResponse.getQuoteResponse().getResult().get(0).getBidSize())
    .ask(apiResponse.getQuoteResponse().getResult().get(0).getAsk())
    .askSize(apiResponse.getQuoteResponse().getResult().get(0).getAskSize())
    .APIDatetime(LocalDate.now().toString())
    .type(type)
    .MarketTime(String.valueOf(marketDateTime.toString()))
    .build();
  }

}
