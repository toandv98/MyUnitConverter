package vn.com.toandv98.unitconverter.ui.setting;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import vn.com.toandv98.unitconverter.R;
import vn.com.toandv98.unitconverter.ui.base.BaseDialog;

public class AboutDialog extends BaseDialog {

    private FloatingActionButton mFabCancel;
    private Button mBtnOk;

    @Override
    protected int getLayout() {
        return R.layout.dialog_about;
    }

    @Override
    protected void initView(View view) {
        mFabCancel = view.findViewById(R.id.fab_about_cancel);
        mBtnOk = view.findViewById(R.id.btn_about_ok);
    }

    @Override
    protected void setupView(Bundle savedInstanceState) {

    }

    @Override
    protected void initListener() {
        mBtnOk.setOnClickListener(this);
        mFabCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        dismiss();
    }
}

