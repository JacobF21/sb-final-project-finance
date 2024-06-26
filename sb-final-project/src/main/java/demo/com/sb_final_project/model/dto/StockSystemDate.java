package demo.com.sb_final_project.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StockSystemDate {
  public String symbol;
  public String systemDate;
}
