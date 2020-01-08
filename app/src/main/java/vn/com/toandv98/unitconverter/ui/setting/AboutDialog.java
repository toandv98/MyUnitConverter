package vn.com.toandv98.unitconverter.ui.setting;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Objects;

import vn.com.toandv98.unitconverter.R;

public class AboutDialog extends DialogFragment implements View.OnClickListener {

    private FloatingActionButton mFabCancel;
    private Button mBtnOk;

    @Override
    public final View onCreateView(LayoutInflater inflater, ViewGroup container,
                                   Bundle savedInstanceState) {
        return inflater.inflate(R.layout.dialog_about, container);
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
        mFabCancel = view.findViewById(R.id.fab_about_cancel);
        mBtnOk = view.findViewById(R.id.btn_about_ok);
        mBtnOk.setOnClickListener(this);
        mFabCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        dismiss();
    }
}

