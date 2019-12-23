package vn.com.toandv98.unitconverter.ui.customs;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import vn.com.toandv98.unitconverter.R;
import vn.com.toandv98.unitconverter.data.entities.CustomConversion;
import vn.com.toandv98.unitconverter.utils.AppUtils;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder>
        implements Filterable {

    private Context mContext;
    private CustomContract.Presenter mPresenter;
    private List<CustomConversion> mData;
    private List<CustomConversion> mFilters;

    public CustomAdapter(Context mContext, CustomContract.Presenter mPresenter, List<CustomConversion> mData) {
        this.mContext = mContext;
        this.mPresenter = mPresenter;
        this.mData = mData;
        this.mFilters = mData;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.item_custom_conversion, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        CustomConversion item = mFilters.get(position);
        holder.tvLabel.setText(item.getLabel());
        holder.tvHistory.setText(String.valueOf(item.getHistory()));
    }

    @Override
    public int getItemCount() {
        return mFilters == null ? 0 : mFilters.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String str = constraint.toString();
                FilterResults results = new FilterResults();
                if (str.isEmpty()) {
                    results.values = mData;
                } else {
                    List<CustomConversion> items = new ArrayList<>();
                    for (CustomConversion item : mData) {
                        if (AppUtils.removeAccent(item.getLabel()).toLowerCase()
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
                mFilters = (List<CustomConversion>) results.values;
                notifyDataSetChanged();
            }
        };
    }


    public class CustomViewHolder extends ViewHolder implements OnClickListener {
        TextView tvLabel, tvHistory;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            tvLabel = itemView.findViewById(R.id.tv_item_name_custom);
            tvHistory = itemView.findViewById(R.id.tv_item_history);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mPresenter.onItemClick(getAdapterPosition());
        }
    }
}
