package ir.coinexscanner;

import ir.coinexscanner.model.CoinExMarket;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CoinExMarketResponse {

    private int code;
    private String message;
    private List<CoinExMarket> data;

    public CoinExMarketResponse() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<CoinExMarket> getData() {
        return data;
    }

    public void setData(List<CoinExMarket> data) {
        this.data = data;
    }
}
