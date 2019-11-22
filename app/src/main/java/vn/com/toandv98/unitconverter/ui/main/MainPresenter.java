package vn.com.toandv98.unitconverter.ui.main;

import vn.com.toandv98.unitconverter.data.IDataManager;
import vn.com.toandv98.unitconverter.ui.base.BasePresenter;

public class MainPresenter extends BasePresenter<MainContract.View, IDataManager> implements MainContract.Presenter {

    public MainPresenter(MainContract.View view, IDataManager dataManager) {
        super(view, dataManager);
    }
}