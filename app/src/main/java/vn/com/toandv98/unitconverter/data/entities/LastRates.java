package vn.com.toandv98.unitconverter.data.entities;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class LastRates {

    @SerializedName("date")
    private String date;

    @SerializedName("success")
    private boolean success;

    @SerializedName("rates")
    private Map<String, Double> rates;

    @SerializedName("timestamp")
    private int timestamp;

    @SerializedName("base")
    private String base;

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public boolean isSuccess() {
        return success;
    }

    public Map<String, Double> getRates() {
        return rates;
    }

    public void setRates(Map<String, Double> rates) {
        this.rates = rates;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getBase() {
        return base;
    }
}