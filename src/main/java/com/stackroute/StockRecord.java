package com.stackroute;

import java.util.Date;

public class StockRecord {

    private Date date;
    private double openingPrice;
    private double dayHighPrice;
    private double dayLowPrice;
    private double dayClosingPrice;
    private double adjustedDayClosingPrice;
    private long volume;

    public StockRecord() {

    }

    /**
     * Complete the constructor and required getter/setter methods
     */
    public StockRecord(Date parse, double v, double v1, double v2, double v3, double v4, int i) {
        this.date = parse;
        this.openingPrice = v;
        this.dayHighPrice = v1;
        this.dayLowPrice = v2;
        this.dayClosingPrice = v3;
        this.adjustedDayClosingPrice = v4;
        this.volume = i;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getOpeningPrice() {
        return openingPrice;
    }

    public void setOpeningPrice(double openingPrice) {
        this.openingPrice = openingPrice;
    }

    public double getDayHighPrice() {
        return dayHighPrice;
    }

    public void setDayHighPrice(double dayHighPrice) {
        this.dayHighPrice = dayHighPrice;
    }

    public double getDayLowPrice() {
        return dayLowPrice;
    }

    public void setDayLowPrice(double dayLowPrice) {
        this.dayLowPrice = dayLowPrice;
    }

    public double getDayClosingPrice() {
        return dayClosingPrice;
    }

    public void setDayClosingPrice(double dayClosingPrice) {
        this.dayClosingPrice = dayClosingPrice;
    }

    public double getAdjustedDayClosingPrice() {
        return adjustedDayClosingPrice;
    }

    public void setAdjustedDayClosingPrice(double adjustedDayClosingPrice) {
        this.adjustedDayClosingPrice = adjustedDayClosingPrice;
    }

    public long getVolume() {
        return volume;
    }

    public void setVolume(long volume) {
        this.volume = volume;
    }
}
