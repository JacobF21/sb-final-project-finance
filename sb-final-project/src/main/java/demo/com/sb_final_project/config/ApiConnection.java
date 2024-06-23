package demo.com.sb_final_project.config;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Component
public class ApiConnection {

  @Autowired
  RestTemplate restTemplate;

  public String getCookie() {
    String url = "https://fc.yahoo.com?p=us";
    List<String> cookies = new ArrayList<>();
    try {
      ResponseEntity<String> response =
          restTemplate.getForEntity(url, String.class);
      HttpHeaders headers = response.getHeaders();
      List<String> cookieHeaders = headers.get(HttpHeaders.SET_COOKIE);
      if (cookieHeaders != null) {
        cookies.addAll(cookieHeaders);
      }
    } catch (HttpClientErrorException.NotFound ex) {
      HttpHeaders responseHeaders = ex.getResponseHeaders();
      // ResponseEntity<String> crumbResponse = ResponseEntity.status(404).headers(headers).build();
      cookies = responseHeaders.get(HttpHeaders.SET_COOKIE);
    }
    return cookies.get(0);
  }

  public String getCrumb(String cookie) {
    String url = "https://query1.finance.yahoo.com/v1/test/getcrumb";
    String userAgent = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/125.0.0.0 Safari/537.36";
    HttpHeaders headers = new HttpHeaders();
    headers.add("Cookie", cookie);
    headers.add("User-Agent", userAgent);
    HttpEntity<String> requestEntity = new HttpEntity<>(headers);
    ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);
    String responseBody = responseEntity.getBody();
    return responseBody;
  }
}


