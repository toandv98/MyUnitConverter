package vn.com.toandv98.unitconverter.ui.custom;

import vn.com.toandv98.unitconverter.data.IDataManager;
import vn.com.toandv98.unitconverter.ui.base.BasePresenter;

public class CustomPresenter extends BasePresenter<CustomContract.View, IDataManager> implements CustomContract.Presenter {

    public CustomPresenter(CustomContract.View view, IDataManager dataManager) {
        super(view, dataManager);
    }
}
