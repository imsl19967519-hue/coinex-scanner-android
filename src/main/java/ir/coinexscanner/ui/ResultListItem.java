package ir.coinexscanner.ui;

/**
 * آیتم نمایشی هر سطر در لیست نتایج اسکن (مدل Presentation، جداشده از Entity دیتابیس).
 */
public class ResultListItem {
    private final String market;
    private final String scoreText;
    private final String stateText;
    private final String priceText;

    public ResultListItem(String market, String scoreText, String stateText, String priceText) {
        this.market = market;
        this.scoreText = scoreText;
        this.stateText = stateText;
        this.priceText = priceText;
    }

    public String getMarket() { return market; }
    public String getScoreText() { return scoreText; }
    public String getStateText() { return stateText; }
    public String getPriceText() { return priceText; }
}
