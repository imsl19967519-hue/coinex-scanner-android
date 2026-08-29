package ir.coinexscanner;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Candle {
    private final long timestamp;
    private final double open;
    private final double close;
    private final double high;
    private final double low;
    private final double volume;

    public Candle(long timestamp, double open, double close, double high, double low, double volume) {
        this.timestamp = timestamp;
        this.open = open;
        this.close = close;
        this.high = high;
        this.low = low;
        this.volume = volume;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public double getOpen() {
        return open;
    }

    public double getClose() {
        return close;
    }

    public double getHigh() {
        return high;
    }

    public double getLow() {
        return low;
    }

    public double getVolume() {
        return volume;
    }
}
