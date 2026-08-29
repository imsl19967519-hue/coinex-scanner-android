package ir.coinexscanner.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CoinExMarket {

    @JsonProperty("market")
    private String market;

    @JsonProperty("base_ccy")
    private String baseCcy;

    @JsonProperty("quote_ccy")
    private String quoteCcy;

    @JsonProperty("min_amount")
    private String minAmount;

    @JsonProperty("is_api_trading")
    private boolean isApiTrading;

    public CoinExMarket() {
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public String getBaseCcy() {
        return baseCcy;
    }

    public void setBaseCcy(String baseCcy) {
        this.baseCcy = baseCcy;
    }

    public String getQuoteCcy() {
        return quoteCcy;
    }

    public void setQuoteCcy(String quoteCcy) {
        this.quoteCcy = quoteCcy;
    }

    public String getMinAmount() {
        return minAmount;
    }

    public void setMinAmount(String minAmount) {
        this.minAmount = minAmount;
    }

    public boolean isApiTrading() {
        return isApiTrading;
    }

    public void setApiTrading(boolean apiTrading) {
        isApiTrading = apiTrading;
    }
}
