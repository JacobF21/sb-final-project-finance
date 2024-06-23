package com.demo.demo_sb_thymleaf.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CoinData {
  private String id;
  private String symbol;
  private String name;
  private String image;
  private double current_price;
  private long market_cap;
  private int market_cap_rank;
  private Long fully_diluted_valuation;
  private long total_volume;
  private double high_24h;
  private double low_24h;
  private double price_change_24h;
  private double price_change_percentage_24h;
  private double market_cap_change_24h;
  private double market_cap_change_percentage_24h;
  private double circulating_supply;
  private double total_supply;
  private Double max_supply;
  private double ath;
  private double ath_change_percentage;
  private String ath_date;
  private double atl;
  private double atl_change_percentage;
  private String atl_date;
  private Roi roi;
  private String last_updated;

  // Constructors, getters, and setters

  @Getter
  public static class Roi {
      private double times;
      private String currency;
      private double percentage;
  }

}
