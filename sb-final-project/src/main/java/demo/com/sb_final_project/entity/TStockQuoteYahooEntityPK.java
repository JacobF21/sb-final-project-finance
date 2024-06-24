package demo.com.sb_final_project.entity;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class TStockQuoteYahooEntityPK implements Serializable{
  private String symbol;
  private Long regularMarketUnix;
}
