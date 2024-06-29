package demo.com.db_final_project_frontend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class OutputMessage {
  private String dateStr;
  private Message message;
}
