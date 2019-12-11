package vn.com.toandv98.unitconverter.ui.unitsearch;

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

    private Context context;
    private UnitSearchContract.Presenter presenter;
    private List<Unit> units;
    private List<Unit> filters;

    public UnitSearchAdapter(Context context, UnitSearchContract.Presenter presenter, List<Unit> units) {
        this.context = context;
        this.presenter = presenter;
        this.units = units;
        this.filters = units;
    }

    @NonNull
    @Override
    public UnitSearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_search_unit, parent, false);
        return new UnitSearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UnitSearchViewHolder holder, int position) {
        Unit unit = filters.get(position);
        holder.btnItem.setText(unit.getLabelRes());

        Drawable drawable = context.getResources().getDrawable(unit.getDrawableRes());
        drawable.setBounds(0, 0, 90, 90);
        holder.btnItem.setCompoundDrawables(drawable, null, null, null);

    }

    @Override
    public int getItemCount() {
        return filters == null ? 0 : filters.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String str = constraint.toString();
                FilterResults results = new FilterResults();
                if (str.isEmpty()) {
                    results.values = units;
                } else {
                    List<Unit> items = new ArrayList<>();
                    for (Unit item : units) {
                        if (AppUtils.removeAccent(context.getString(item.getLabelRes())).toLowerCase()
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
                filters = (List<Unit>) results.values;
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
            Unit unit = filters.get(getAdapterPosition());
            int position = units.lastIndexOf(unit);
            presenter.onItemSearchClick(unit, position);
        }
    }
}
