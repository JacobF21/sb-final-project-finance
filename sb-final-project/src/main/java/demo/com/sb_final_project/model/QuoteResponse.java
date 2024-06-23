package demo.com.sb_final_project.model;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true) 
public class QuoteResponse{
   
   private List<Result> result;
   private String error;
   
   
}
