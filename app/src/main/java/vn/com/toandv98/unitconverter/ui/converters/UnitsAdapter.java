package vn.com.toandv98.unitconverter.ui.converters;

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

import static vn.com.toandv98.unitconverter.utils.Constrants.INPUT_UNIT;
import static vn.com.toandv98.unitconverter.utils.Constrants.RESULT_UNIT;

public class UnitsAdapter extends RecyclerView.Adapter<UnitsAdapter.UnitViewHolder> {

    private Context context;
    private ConvertersContract.Presenter presenter;
    private List<Unit> units;
    private int typeUnit;
    private int lastCheckedPosition = 0;

    public UnitsAdapter(Context context, ConvertersContract.Presenter presenter, List<Unit> units, int typeUnit) {
        this.context = context;
        this.presenter = presenter;
        this.units = units;
        this.typeUnit = typeUnit;
    }

    public void updateRadio(int position) {
        int copyOfLastCheckedPosition = lastCheckedPosition;
        lastCheckedPosition = position;
        notifyItemChanged(copyOfLastCheckedPosition);
        notifyItemChanged(lastCheckedPosition);
    }

    public int getLastCheckedPosition() {
        return lastCheckedPosition;
    }

    @NonNull
    @Override
    public UnitViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_unit, parent, false);
        return new UnitViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UnitViewHolder holder, int position) {
        Unit unit = units.get(position);
        holder.radioButton.setText(unit.getLabelRes());
        holder.radioButton.setChecked(position == lastCheckedPosition);

        Drawable drawable = context.getResources().getDrawable(unit.getDrawableRes());
        drawable.setBounds(0, 0, 70, 70);
        holder.radioButton.setCompoundDrawables(drawable, null, null, null);

    }

    @Override
    public int getItemCount() {
        return units.size();
    }

    public class UnitViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        RadioButton radioButton;

        public UnitViewHolder(@NonNull View itemView) {
            super(itemView);
            radioButton = itemView.findViewById(R.id.radio_item_unit);
            radioButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            if (typeUnit == INPUT_UNIT) {
                presenter.onInputUnitSelect(position);
            } else if (typeUnit == RESULT_UNIT) {
                presenter.onResultUnitSelect(position);
            }
        }
    }
}
