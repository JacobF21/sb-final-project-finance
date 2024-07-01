package demo.com.sb_final_project.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import demo.com.sb_final_project.entity.TStockQuoteYahooEntity;
import demo.com.sb_final_project.model.dto.StockSystemDate;

@Repository
public interface TStockReposiotory extends JpaRepository<TStockQuoteYahooEntity, demo.com.sb_final_project.entity.TStockQuoteYahooEntityPK>{
  
  @Query(value = "SELECT NEW demo.com.sb_final_project.model.dto.StockSystemDate(symbol, MAX(APIDatetime)) FROM TStockQuoteYahooEntity WHERE symbol = :symbol GROUP BY symbol")
  StockSystemDate findMaxMarketTimeBySymbol(@Param("symbol") String symbol);

  @Query(value = "SELECT * FROM tstock_quote_yahoo sq JOIN (SELECT symbol as symbol2, MAX(market_time) as marketTime FROM tstock_quote_yahoo GROUP BY symbol) t ON sq.symbol = t.symbol2 AND sq.market_time = t.marketTime", nativeQuery = true)
  List<TStockQuoteYahooEntity> findMaxMarketTimeBySymbol();

  List<TStockQuoteYahooEntity> findBySymbolAndAPIDatetimeOrderByRegularMarketUnix(String symbol,String apiDatetime);

  @Query(value = "SELECT t FROM TStockQuoteYahooEntity t WHERE t.regularMarketUnix = (SELECT MAX(t2.regularMarketUnix) FROM TStockQuoteYahooEntity t2 WHERE t2.symbol = :symbol) AND t.symbol = :symbol")
  TStockQuoteYahooEntity findLatestRegularMarketChangePercent(@Param("symbol") String symbol);
}
