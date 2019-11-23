package vn.com.toandv98.unitconverter.ui.unitsearch;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import vn.com.toandv98.unitconverter.R;
import vn.com.toandv98.unitconverter.data.entities.Unit;

public class UnitSearchAdapter extends RecyclerView.Adapter<UnitSearchAdapter.UnitSearchViewHolder> {

    private Context context;
    private UnitSearchContract.Presenter presenter;
    private List<Unit> units;

    public UnitSearchAdapter(Context context, UnitSearchContract.Presenter presenter, List<Unit> units) {
        this.context = context;
        this.presenter = presenter;
        this.units = units;
    }

    @NonNull
    @Override
    public UnitSearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_unit, parent, false);
        return new UnitSearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UnitSearchViewHolder holder, int position) {
        Unit unit = units.get(position);
        holder.radioButton.setText(unit.getLabelRes());

        Drawable drawable = context.getResources().getDrawable(unit.getDrawableRes());
        drawable.setBounds(0, 0, 70, 70);
        holder.radioButton.setCompoundDrawables(drawable, null, null, null);

    }

    @Override
    public int getItemCount() {
        return units.size();
    }

    public class UnitSearchViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        RadioButton radioButton;

        public UnitSearchViewHolder(@NonNull View itemView) {
            super(itemView);
            radioButton = itemView.findViewById(R.id.radio_item_unit);
            radioButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
