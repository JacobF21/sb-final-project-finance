package demo.com.sb_final_project.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name ="StockList")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class StockListEntity {
  
  @Id
  private String symbol;
  private String shortName;
}
