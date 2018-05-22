package com.example.rush.foreignexchange.gson;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;

/**
 * Created by rush on 2018/5/8.
 */

public class ForeignExchange {
    @SerializedName("Id")
    public int id;
    /*币种*/
    @SerializedName("CurrencyName")
    public String currency;
    /*现汇买入价*/
    @SerializedName("BuyingRate")
    public double buyingRate;
    /*现钞买入价*/
    @SerializedName("CashBuyingRate")
    public BigDecimal cashBuyingRate;
    /*现汇卖出价*/
    @SerializedName("SellingRate")
    public BigDecimal sellingRate;
    /*现钞卖出价*/
    @SerializedName("CashSellingRate")
    public BigDecimal cashSellingRate;
    /*中间价*/
    @SerializedName("MiddleRate")
    public BigDecimal middleRate;
    /*更新时间*/
    @SerializedName("PublishDateTime")
    public String publishDateTime;

    public String getCurrency(){
        return currency;
    }
    public void setCurrency(String currency){
        this.currency=currency;
    }
    public double getBuyingRate(){
        return buyingRate;
    }
    public void setBuyingRate(double buyingRate){
        this.buyingRate=buyingRate;
    }
    public BigDecimal getCashBuyingRate(){
        return cashBuyingRate;
    }
    public void setCashBuyingRate(BigDecimal cashBuyingRate){
        this.cashBuyingRate=cashBuyingRate;
    }
    public BigDecimal getSellingRate(){
        return sellingRate;
    }
    public void setSellingRate(BigDecimal sellingRate){
        this.sellingRate=sellingRate;
    }
    public BigDecimal getCashSellingRate(){
        return cashSellingRate;
    }
    public void setCashSellingRate(BigDecimal cashBuyingRate){
        this.cashBuyingRate=cashBuyingRate;
    }
    public BigDecimal getMiddleRate(){
        return middleRate;
    }
    public void setMiddleRate(BigDecimal middleRate){
        this.middleRate=middleRate;
    }
    public String getPublishDateTime(){
        return publishDateTime;
    }
    public void setPublishDateTime(String publishDateTime){
        this.publishDateTime=publishDateTime;
    }
}
