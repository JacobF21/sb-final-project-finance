package demo.com.sb_final_project.entity;

import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name="TSTOCK_QUOTE_YAHOO")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
public class TStockQuoteYahooEntity {

  private String symbol;
  @Id
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
