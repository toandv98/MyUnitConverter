package vn.com.toandv98.unitconverter.ui.customs.editconversion;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import vn.com.toandv98.unitconverter.R;
import vn.com.toandv98.unitconverter.data.entities.CustomUnit;
import vn.com.toandv98.unitconverter.ui.base.BaseDialog;

public class AddUnitDialog extends BaseDialog {

    private static final String EXTRA_NAME_UNIT = "unit";
    private static final String EXTRA_NAME_IS_ADD = "is_add_unit";
    private boolean isAddUnit;
    private FloatingActionButton mFabCancel;
    private Button mBtnSave;
    private CustomUnit mUnit;
    private EditText mEdtLabel, mEdtValue;
    private RadioButton mRbToBase;
    private OnSaveUnitListener listener;

    void setOnSaveUnitListener(OnSaveUnitListener listener) {
        this.listener = listener;
    }

    public interface OnSaveUnitListener {
        void onAddUnit(CustomUnit unit);

        void onUpdateUnit(CustomUnit unit);

        void onInvalidInput();
    }

    static AddUnitDialog newInstance(CustomUnit unit, boolean isAdd) {
        AddUnitDialog frag = new AddUnitDialog();
        Bundle args = new Bundle();
        args.putParcelable(EXTRA_NAME_UNIT, unit);
        args.putBoolean(EXTRA_NAME_IS_ADD, isAdd);
        frag.setArguments(args);
        return frag;
    }

    @Override
    protected int getLayout() {
        return R.layout.dialog_add_unit;
    }

    @Override
    protected void initView(View view) {
        mFabCancel = view.findViewById(R.id.fab_unit_cancel);
        mBtnSave = view.findViewById(R.id.btn_unit_save);
        mEdtLabel = view.findViewById(R.id.edt_dialog_unit_name);
        mEdtValue = view.findViewById(R.id.edt_unit_value);
        mRbToBase = view.findViewById(R.id.rb_value_to_base);
    }

    @Override
    protected void setupView(Bundle savedInstanceState) {

        if (getArguments() != null) {
            isAddUnit = getArguments().getBoolean(EXTRA_NAME_IS_ADD);
            mUnit = getArguments().getParcelable(EXTRA_NAME_UNIT);
            if (!isAddUnit && mUnit != null) {
                mEdtLabel.setText(mUnit.getLabel());
                mEdtValue.setText(String.valueOf(mUnit.getFromBase()));
            }
        }

        mEdtLabel.requestFocus();
    }

    @Override
    protected void initListener() {
        mBtnSave.setOnClickListener(this);
        mFabCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.fab_unit_cancel:
                dismiss();
                break;
            case R.id.btn_unit_save:
                if (isValid()) {
                    saveUnit();
                    dismiss();
                } else {
                    listener.onInvalidInput();
                }
                break;
        }
    }

    private void saveUnit() {
        String label = mEdtLabel.getText().toString();
        double from, to;
        if (mRbToBase.isChecked()) {
            from = Double.parseDouble(mEdtValue.getText().toString());
            to = 1 / from;
        } else {
            to = Double.parseDouble(mEdtValue.getText().toString());
            from = 1 / to;
        }

        mUnit.setLabel(label);
        mUnit.setFromBase(from);
        mUnit.setToBase(to);

        if (isAddUnit) {
            listener.onAddUnit(mUnit);
        } else {
            listener.onUpdateUnit(mUnit);
        }
    }

    private boolean isValid() {
        return mEdtLabel.getText().toString().trim().length() != 0
                && mEdtValue.getText().toString().trim().length() != 0;
    }
}
