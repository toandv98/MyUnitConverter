package vn.com.toandv98.unitconverter.ui.tool;

import vn.com.toandv98.unitconverter.data.IDataManager;
import vn.com.toandv98.unitconverter.ui.base.BasePresenter;

import static vn.com.toandv98.unitconverter.ui.tool.ToolsContract.Presenter;
import static vn.com.toandv98.unitconverter.ui.tool.ToolsContract.View;

public class ToolsPresenter extends BasePresenter<View, IDataManager> implements Presenter {

    public ToolsPresenter(View view, IDataManager dataManager) {
        super(view, dataManager);
    }

    @Override
    public void onRandomNumberClick() {
        view.navigateToRandomFragment();
    }
}
