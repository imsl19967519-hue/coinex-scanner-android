package ir.coinexscanner.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ir.coinexscanner.R;

/**
 * آداپتور RecyclerView جهت نمایش نتایج فیلتر و اسکن بازارها.
 */
public class ScanResultsAdapter extends RecyclerView.Adapter<ScanResultsAdapter.ViewHolder> {

    private final List<ResultListItem> items = new ArrayList<>();

    public void submitList(List<ResultListItem> newItems) {
        items.clear();
        if (newItems != null) {
            items.addAll(newItems);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_scan_result, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ResultListItem item = items.get(position);
        holder.tvMarket.setText(item.getMarket());
        holder.tvScore.setText("امتیاز: " + item.getScoreText());
        holder.tvState.setText(item.getStateText());
        holder.tvPrice.setText("قیمت: " + item.getPriceText());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView tvMarket;
        final TextView tvScore;
        final TextView tvState;
        final TextView tvPrice;

        ViewHolder(View itemView) {
            super(itemView);
            tvMarket = itemView.findViewById(R.id.tvMarket);
            tvScore = itemView.findViewById(R.id.tvScore);
            tvState = itemView.findViewById(R.id.tvState);
            tvPrice = itemView.findViewById(R.id.tvPrice);
        }
    }
}
