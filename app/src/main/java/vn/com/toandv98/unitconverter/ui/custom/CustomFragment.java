package vn.com.toandv98.unitconverter.ui.custom;

import android.os.Bundle;
import android.view.View;

import vn.com.toandv98.unitconverter.R;
import vn.com.toandv98.unitconverter.data.DataManager;
import vn.com.toandv98.unitconverter.ui.base.BaseFragment;

public class CustomFragment extends BaseFragment<CustomContract.Presenter>
        implements CustomContract.View {
    @Override
    protected int getLayout() {
        return R.layout.fragment_custom;
    }

    @Override
    protected CustomContract.Presenter initPresenter() {
        return new CustomPresenter(this, new DataManager(getBaseActivity()));
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    protected void setupView(Bundle savedInstanceState) {

    }

    @Override
    protected void initListener() {

    }
}
