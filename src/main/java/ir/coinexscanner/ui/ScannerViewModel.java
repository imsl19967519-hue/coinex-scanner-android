package ir.coinexscanner.ui;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import ir.coinexscanner.database.ScanResultEntity;
import ir.coinexscanner.repository.ScannerRepository;

/**
 * ViewModel لایه Presentation: تنها نقطه اتصال UI به Repository.
 * تمام نتایج از طریق LiveData به صورت immutable می‌گیرار Activity قرار می‌گیرد.
 */
public class ScannerViewModel extends AndroidViewModel {

    private final ScannerRepository repository;
    private final MutableLiveData<ScannerUiState> uiState = new MutableLiveData<>(ScannerUiState.idle());

    public ScannerViewModel(Application application) {
        super(application);
        this.repository = new ScannerRepository(application);
    }

    public LiveData<ScannerUiState> getUiState() {
        return uiState;
    }

    public void startScan(String quoteCurrency, String period, int limit) {
        uiState.setValue(ScannerUiState.loading(0, 0, quoteCurrency));

        repository.startScan(quoteCurrency, period, limit, new ScannerRepository.ScanProgressCallback() {
            @Override
            public void onProgress(int current, int total, String currentMarket) {
                uiState.postValue(ScannerUiState.loading(current, total, currentMarket));
            }

            @Override
            public void onComplete(List<ScanResultEntity> results) {
                uiState.postValue(ScannerUiState.success(toListItems(results)));
            }

            @Override
            public void onError(String message) {
                uiState.postValue(ScannerUiState.error(message));
            }
        });
    }

    public void loadSavedResults() {
        repository.getSavedResults(new ScannerRepository.ScanProgressCallback() {
            @Override
            public void onProgress(int current, int total, String currentMarket) { /* not used */ }

            @Override
            public void onComplete(List<ScanResultEntity> results) {
                uiState.postValue(ScannerUiState.success(toListItems(results)));
            }

            @Override
            public void onError(String message) {
                uiState.postValue(ScannerUiState.error(message));
            }
        });
    }

    private List<ResultListItem> toListItems(List<ScanResultEntity> entities) {
        List<ResultListItem> items = new ArrayList<>();
        for (ScanResultEntity e : entities) {
            items.add(new ResultListItem(
                    e.getMarket(),
                    CompressionScoreFormatter.formatScore(e.getCompressionScore()),
                    CompressionScoreFormatter.formatState(e.getState()),
                    CompressionScoreFormatter.formatPrice(e.getCurrentPrice())
            ));
        }
        return items;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        // آزادسازی منابع ترد در پایان چرخه حیات ViewModel
    }
}
