package vn.com.toandv98.unitconverter.ui.conversion;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import vn.com.toandv98.unitconverter.R;
import vn.com.toandv98.unitconverter.data.entities.ConversionItem;
import vn.com.toandv98.unitconverter.utils.AppUtils;

public class ConversionAdapter extends RecyclerView.Adapter<ConversionAdapter.ConversionViewHolder>
        implements Filterable {

    private Context context;
    private ConversionContract.Presenter presenter;
    private List<ConversionItem> data;
    private List<ConversionItem> dataFilter;

    public ConversionAdapter(Context context, ConversionContract.Presenter presenter,
                             List<ConversionItem> data) {
        this.context = context;
        this.presenter = presenter;
        this.data = data;
        this.dataFilter = data;
    }

    @NonNull
    @Override
    public ConversionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_conversion, parent, false);
        return new ConversionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ConversionViewHolder holder, int position) {
        ConversionItem item = dataFilter.get(position);
        holder.tvTitle.setText(item.getTitleRes());
        holder.ivIcon.setImageDrawable(context.getDrawable(item.getImageRes()));
    }

    @Override
    public int getItemCount() {
        return dataFilter == null ? 0 : dataFilter.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String str = constraint.toString();
                FilterResults results = new FilterResults();
                if (str.isEmpty()) {
                    results.values = data;
                } else {
                    List<ConversionItem> items = new ArrayList<>();
                    for (ConversionItem item : data) {
                        if (AppUtils.removeAccent(context.getString(item.getTitleRes())).toLowerCase()
                                .contains(AppUtils.removeAccent(str).toLowerCase())) {
                            items.add(item);
                        }
                    }
                    results.values = items;
                }
                return results;
            }

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                dataFilter = (List<ConversionItem>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    public class ConversionViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvTitle;
        ImageView ivIcon;

        public ConversionViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_item_name_conversion);
            ivIcon = itemView.findViewById(R.id.iv_item_conversion);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            presenter.onRecyclerViewItemClick(dataFilter.get(getAdapterPosition()).getId());
        }
    }
}
