package vn.com.toandv98.unitconverter.ui.setting;

import android.os.Bundle;
import android.view.View;

import vn.com.toandv98.unitconverter.R;
import vn.com.toandv98.unitconverter.data.DataManager;
import vn.com.toandv98.unitconverter.ui.base.BaseFragment;

public class SettingFragment extends BaseFragment<SettingContract.Presenter>
        implements SettingContract.View {
    @Override
    protected int getLayout() {
        return R.layout.fragment_setting;
    }

    @Override
    protected SettingContract.Presenter initPresenter() {
        return new SettingPresenter(this, new DataManager(getBaseActivity()));
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
