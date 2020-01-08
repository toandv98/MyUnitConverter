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
import vn.com.toandv98.unitconverter.data.entities.Conversion;
import vn.com.toandv98.unitconverter.utils.AppUtils;

public class ConversionAdapter extends RecyclerView.Adapter<ConversionAdapter.ConversionViewHolder>
        implements Filterable {

    private Context mContext;
    private ConversionContract.Presenter mPresenter;
    private List<Conversion> mData;
    private List<Conversion> mDataFilters;

    public ConversionAdapter(Context mContext, ConversionContract.Presenter mPresenter,
                             List<Conversion> mData) {
        this.mContext = mContext;
        this.mPresenter = mPresenter;
        this.mData = mData;
        this.mDataFilters = mData;
    }

    @NonNull
    @Override
    public ConversionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.item_conversion, parent, false);
        return new ConversionViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ConversionViewHolder holder, int position) {
        Conversion item = mDataFilters.get(position);
        holder.tvTitle.setText(item.getTitleRes());
        holder.tvTitle.append(item.getTitleCustom());
        holder.ivIcon.setImageDrawable(mContext.getDrawable(item.getImageRes()));
    }

    @Override
    public int getItemCount() {
        return mDataFilters == null ? 0 : mDataFilters.size();
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
                    List<Conversion> items = new ArrayList<>();
                    for (Conversion item : mData) {
                        if (AppUtils.removeAccent(mContext.getString(item.getTitleRes()) + item.getTitleCustom()).toLowerCase()
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
                mDataFilters = (List<Conversion>) results.values;
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
            mPresenter.onRecyclerViewItemClick(mDataFilters.get(getAdapterPosition()).getId());
        }
    }
}
