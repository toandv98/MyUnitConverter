package vn.com.toandv98.unitconverter.ui.tool;

import android.os.Bundle;
import android.view.View;

import vn.com.toandv98.unitconverter.R;
import vn.com.toandv98.unitconverter.data.DataManager;
import vn.com.toandv98.unitconverter.ui.base.BaseFragment;

public class ToolsFragment extends BaseFragment<ToolsContract.Presenter>
        implements ToolsContract.View {
    @Override
    protected int getLayout() {
        return R.layout.fragment_tools;
    }

    @Override
    protected ToolsContract.Presenter initPresenter() {
        return new ToolsPresenter(this, new DataManager(getBaseActivity()));
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
