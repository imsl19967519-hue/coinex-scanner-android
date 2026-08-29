package ir.coinexscanner.ui;

import java.util.List;

/**
 * وضعیت کامل UI برای مشاهده از سمت Activity (الگوی State در معماری MVVM).
 */
public class ScannerUiState {
    private final boolean isLoading;
    private final int progressCurrent;
    private final int progressTotal;
    private final String currentMarket;
    private final List<ResultListItem> results;
    private final String errorMessage;

    public ScannerUiState(boolean isLoading, int progressCurrent, int progressTotal,
                          String currentMarket, List<ResultListItem> results, String errorMessage) {
        this.isLoading = isLoading;
        this.progressCurrent = progressCurrent;
        this.progressTotal = progressTotal;
        this.currentMarket = currentMarket;
        this.results = results;
        this.errorMessage = errorMessage;
    }

    public static ScannerUiState idle() {
        return new ScannerUiState(false, 0, 0, null, null, null);
    }

    public static ScannerUiState loading(int current, int total, String market) {
        return new ScannerUiState(true, current, total, market, null, null);
    }

    public static ScannerUiState success(List<ResultListItem> results) {
        return new ScannerUiState(false, 0, 0, null, results, null);
    }

    public static ScannerUiState error(String message) {
        return new ScannerUiState(false, 0, 0, null, null, message);
    }

    public boolean isLoading() { return isLoading; }
    public int getProgressCurrent() { return progressCurrent; }
    public int getProgressTotal() { return progressTotal; }
    public String getCurrentMarket() { return currentMarket; }
    public List<ResultListItem> getResults() { return results; }
    public String getErrorMessage() { return errorMessage; }
}
