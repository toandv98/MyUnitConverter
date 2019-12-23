package vn.com.toandv98.unitconverter.ui.base;

import vn.com.toandv98.unitconverter.data.IDataManager;

public abstract class BasePresenter<V extends IBaseView, D extends IDataManager> implements IBasePresenter {
    protected V view;
    protected D dataManager;

    public BasePresenter(V view, D dataManager) {
        this.view = view;
        this.dataManager = dataManager;
    }

    @Override
    public void onViewDetached() {
        view = null;
        dataManager = null;
    }
}