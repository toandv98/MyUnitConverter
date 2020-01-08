package vn.com.toandv98.unitconverter.ui.customs.editconversion;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import vn.com.toandv98.unitconverter.R;
import vn.com.toandv98.unitconverter.data.entities.CustomUnit;

public class AddedUnitsAdapter extends RecyclerView.Adapter<AddedUnitsAdapter.AddedUnitViewHolder> {

    private Context mContext;
    private List<CustomUnit> mData;
    private EditConversionContract.Presenter mPresenter;

    public AddedUnitsAdapter(Context mContext, List<CustomUnit> mData, EditConversionContract.Presenter mPresenter) {
        this.mContext = mContext;
        this.mData = mData;
        this.mPresenter = mPresenter;
    }

    @NonNull
    @Override
    public AddedUnitViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View view = layoutInflater.inflate(R.layout.item_custom_unit, parent, false);
        return new AddedUnitViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AddedUnitViewHolder holder, int position) {
        CustomUnit unit = mData.get(position);
        holder.tvUnitName.setText(unit.getLabel());
        holder.tvUnitValue.setText(mContext.getString(R.string.label_unit_rate, String.valueOf(unit.getToBase())));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class AddedUnitViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tvUnitName, tvUnitValue;
        ImageButton btnDel;

        public AddedUnitViewHolder(@NonNull View itemView) {
            super(itemView);
            tvUnitName = itemView.findViewById(R.id.tv_item_unit_name_custom);
            tvUnitValue = itemView.findViewById(R.id.tv_item_unit_value);
            btnDel = itemView.findViewById(R.id.btn_delete_unit);
            itemView.setOnClickListener(this);
            btnDel.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.btn_delete_unit) {
                mPresenter.onItemDelClick(getAdapterPosition());
            } else {
                mPresenter.onItemUnitClick(getAdapterPosition());
            }
        }
    }
}
