package demo.com.db_final_project_frontend.view;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class ViewController {
  
  @GetMapping("/")
  public String index(Model model) {
      DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, Locale.getDefault());
      model.addAttribute("serverTime", dateFormat.format(new Date()));
      return "index";
  }

  @GetMapping("/realTime")
  public String realTimePage(Model model) {
      DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, Locale.getDefault());
      model.addAttribute("serverTime", dateFormat.format(new Date()));
      return "realTimeData";
  }
  
}
