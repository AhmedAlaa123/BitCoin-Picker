package com.example.cointicker.data;

import com.google.gson.annotations.SerializedName;

public class CoinData {
    @SerializedName("time")
    private String updateTime;
    @SerializedName("asset_id_base")
    private String base;
    @SerializedName("asset_id_quote")
    private String quote;
    @SerializedName("rate")
    private double rate;

    public CoinData(String updateTime, String base, String quote, double rate) {
        this.updateTime = updateTime;
        this.base = base;
        this.quote = quote;
        this.rate = rate;
    }

    public CoinData() {
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public String getBase() {
        return base;
    }

    public String getQuote() {
        return quote;
    }

    public double getRate() {
        return rate;
    }
}
