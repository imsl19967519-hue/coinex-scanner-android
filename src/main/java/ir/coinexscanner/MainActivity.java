package ir.coinexscanner;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import ir.coinexscanner.ui.ScanResultsAdapter;
import ir.coinexscanner.ui.ScannerViewModel;

/**
 * اکتیویتی اصلی برنامه جهت تنظیم پارامترها، شروع اسکن و مشاهده نتایج.
 */
public class MainActivity extends AppCompatActivity {

    private ScannerViewModel viewModel;
    private ScanResultsAdapter adapter;

    private Spinner spQuote;
    private Spinner spPeriod;
    private Button btnScan;
    private ProgressBar progressBar;
    private TextView tvStatus;
    private RecyclerView rvResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        setupSpinners();
        setupRecyclerView();
        setupViewModel();
    }

    private void initViews() {
        spQuote = findViewById(R.id.spQuote);
        spPeriod = findViewById(R.id.spPeriod);
        btnScan = findViewById(R.id.btnScan);
        progressBar = findViewById(R.id.progressBar);
        tvStatus = findViewById(R.id.tvStatus);
        rvResults = findViewById(R.id.rvResults);

        btnScan.setOnClickListener(v -> {
            String quote = spQuote.getSelectedItem().toString();
            String period = spPeriod.getSelectedItem().toString();
            viewModel.startScan(quote, period, 100);
        });
    }

    private void setupSpinners() {
        ArrayAdapter<String> quoteAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, new String[]{"USDT", "USDC", "BTC"});
        spQuote.setAdapter(quoteAdapter);

        ArrayAdapter<String> periodAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, new String[]{"1hour", "4hour", "1day", "15min"});
        spPeriod.setAdapter(periodAdapter);
    }

    private void setupRecyclerView() {
        adapter = new ScanResultsAdapter();
        rvResults.setLayoutManager(new LinearLayoutManager(this));
        rvResults.setAdapter(adapter);
    }

    private void setupViewModel() {
        viewModel = new ViewModelProvider(this).get(ScannerViewModel.class);

        viewModel.getUiState().observe(this, state -> {
            if (state.isLoading()) {
                progressBar.setVisibility(View.VISIBLE);
                btnScan.setEnabled(false);
                if (state.getProgressTotal() > 0) {
                    tvStatus.setText("در حال اسکن: " + state.getCurrentMarket() + " (" + state.getProgressCurrent() + "/" + state.getProgressTotal() + ")");
                } else {
                    tvStatus.setText("در حال دریافت لیست جفت‌ارزها...");
                }
            } else {
                progressBar.setVisibility(View.GONE);
                btnScan.setEnabled(true);

                if (state.getErrorMessage() != null) {
                    tvStatus.setText("خطا: " + state.getErrorMessage());
                    Toast.makeText(this, state.getErrorMessage(), Toast.LENGTH_SHORT).show();
                } else if (state.getResults() != null) {
                    tvStatus.setText("تعداد نتایج: " + state.getResults().size());
                    adapter.submitList(state.getResults());
                } else {
                    tvStatus.setText("آماده برای اسکن");
                }
            }
        });

        viewModel.loadSavedResults();
    }
}
