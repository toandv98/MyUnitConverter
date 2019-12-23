package vn.com.toandv98.unitconverter.ui.customs.editconversion;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Objects;

import vn.com.toandv98.unitconverter.R;
import vn.com.toandv98.unitconverter.data.entities.CustomUnit;

public class AddUnitDialog extends DialogFragment implements View.OnClickListener {

    public static final String EXTRA_NAME_UNIT = "unit";
    public static final String EXTRA_NAME_IS_ADD = "is_add_unit";
    private boolean isAddUnit;
    private FloatingActionButton mFabCancel;
    private Button mBtnSave;
    private CustomUnit mUnit;
    private EditText mEdtLabel, mEdtValue;
    private RadioButton mRbFromBase, mRbToBase;
    private OnSaveUnitListener listener;

    public void setOnSaveUnitListener(OnSaveUnitListener listener) {
        this.listener = listener;
    }

    public interface OnSaveUnitListener {
        void onAddUnit(CustomUnit unit);

        void onUpdateUnit(CustomUnit unit);

        void onInvalidInput();
    }

    public static AddUnitDialog newInstance(CustomUnit unit, boolean isAdd) {
        AddUnitDialog frag = new AddUnitDialog();
        Bundle args = new Bundle();
        args.putParcelable(EXTRA_NAME_UNIT, unit);
        args.putBoolean(EXTRA_NAME_IS_ADD, isAdd);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public final View onCreateView(LayoutInflater inflater, ViewGroup container,
                                   Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_add_unit, container);
    }

    @NonNull
    @Override
    public final Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        Objects.requireNonNull(dialog.getWindow())
                .setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

    @Override
    public final void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mFabCancel = view.findViewById(R.id.fab_unit_cancel);
        mBtnSave = view.findViewById(R.id.btn_unit_save);
        mEdtLabel = view.findViewById(R.id.edt_dialog_unit_name);
        mEdtValue = view.findViewById(R.id.edt_unit_value);
        mRbFromBase = view.findViewById(R.id.rb_value_from_base);
        mRbToBase = view.findViewById(R.id.rb_value_to_base);

        mBtnSave.setOnClickListener(this);
        mFabCancel.setOnClickListener(this);

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
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.fab_unit_cancel:
                dismiss();
                break;
            case R.id.btn_unit_save:
                if (isValid()) {
                    String label = mEdtLabel.getText().toString();
                    double from, to;
                    if (mRbToBase.isChecked()) {
                        to = Double.parseDouble(mEdtValue.getText().toString());
                        from = 1 / to;
                    } else {
                        from = Double.parseDouble(mEdtValue.getText().toString());
                        to = 10 * from;
                    }

                    mUnit.setLabel(label);
                    mUnit.setFromBase(from);
                    mUnit.setToBase(to);

                    if (isAddUnit) {
                        listener.onAddUnit(mUnit);
                    } else {
                        listener.onUpdateUnit(mUnit);
                    }
                    dismiss();
                } else {
                    listener.onInvalidInput();
                }
                break;
        }
    }

    private boolean isValid() {
        return mEdtLabel.getText().toString().trim().length() != 0
                && mEdtValue.getText().toString().trim().length() != 0;
    }
}
