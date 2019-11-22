package vn.com.toandv98.unitconverter.ui.tool;

import vn.com.toandv98.unitconverter.data.IDataManager;
import vn.com.toandv98.unitconverter.ui.base.BasePresenter;

public class ToolsPresenter extends BasePresenter<ToolsContract.View, IDataManager> implements ToolsContract.Presenter {

    public ToolsPresenter(ToolsContract.View view, IDataManager dataManager) {
        super(view, dataManager);
    }
}
