package vn.com.toandv98.unitconverter.ui.converters.unitsearch;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import vn.com.toandv98.unitconverter.R;
import vn.com.toandv98.unitconverter.data.entities.Unit;
import vn.com.toandv98.unitconverter.utils.AppUtils;

public class UnitSearchAdapter extends RecyclerView.Adapter<UnitSearchAdapter.UnitSearchViewHolder>
        implements Filterable {

    private Context mContext;
    private UnitSearchContract.Presenter mPresenter;
    private List<Unit> mUnits;
    private List<Unit> mFilters;

    public UnitSearchAdapter(Context mContext, UnitSearchContract.Presenter mPresenter, List<Unit> mUnits) {
        this.mContext = mContext;
        this.mPresenter = mPresenter;
        this.mUnits = mUnits;
        this.mFilters = mUnits;
    }

    @NonNull
    @Override
    public UnitSearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.item_search_unit, parent, false);
        return new UnitSearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UnitSearchViewHolder holder, int position) {
        Unit unit = mFilters.get(position);
        holder.btnItem.setText(unit.getLabelRes());

        Drawable drawable = mContext.getResources().getDrawable(unit.getDrawableRes());
        drawable.setBounds(0, 0, 90, 90);
        holder.btnItem.setCompoundDrawables(drawable, null, null, null);

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
                    results.values = mUnits;
                } else {
                    List<Unit> items = new ArrayList<>();
                    for (Unit item : mUnits) {
                        if (AppUtils.removeAccent(mContext.getString(item.getLabelRes())).toLowerCase()
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
                mFilters = (List<Unit>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    public class UnitSearchViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        Button btnItem;

        public UnitSearchViewHolder(@NonNull View itemView) {
            super(itemView);
            btnItem = itemView.findViewById(R.id.btn_item_search_unit);
            btnItem.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Unit unit = mFilters.get(getAdapterPosition());
            int position = mUnits.lastIndexOf(unit);
            mPresenter.onItemSearchClick(unit, position);
        }
    }
}
