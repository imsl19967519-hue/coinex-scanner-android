package ir.coinexscanner;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CoinExCandleResponse {
    private List<Candle> data;

    public List<Candle> getData() {
        return data;
    }

    public void setData(List<Candle> data) {
        this.data = data;
    }
}
