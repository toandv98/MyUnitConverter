package vn.com.toandv98.unitconverter.ui.tool;

import android.os.Bundle;
import android.view.View;

import androidx.cardview.widget.CardView;

import vn.com.toandv98.unitconverter.R;
import vn.com.toandv98.unitconverter.data.DataManager;
import vn.com.toandv98.unitconverter.ui.base.BaseFragment;
import vn.com.toandv98.unitconverter.ui.tool.random.RandomFragment;

public class ToolsFragment extends BaseFragment<ToolsContract.Presenter>
        implements ToolsContract.View, View.OnClickListener {

    private CardView mCvRandomNumber;

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
        mCvRandomNumber = view.findViewById(R.id.cv_tools_random_number);
    }

    @Override
    protected void setupView(Bundle savedInstanceState) {

    }

    @Override
    protected void initListener() {
        mCvRandomNumber.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cv_tools_random_number:
                presenter.onRandomNumberClick();
                break;
        }
    }

    @Override
    public void navigateToRandomFragment() {
        getBaseActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fl_home, new RandomFragment())
                .addToBackStack("random_fragment")
                .commit();
    }
}
