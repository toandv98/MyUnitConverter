package vn.com.toandv98.unitconverter.ui.unitsearch;

import vn.com.toandv98.unitconverter.data.IDataManager;
import vn.com.toandv98.unitconverter.ui.base.BasePresenter;

public class UnitSearchPresenter extends BasePresenter<UnitSearchContract.View, IDataManager> implements UnitSearchContract.Presenter {
    public UnitSearchPresenter(UnitSearchContract.View view, IDataManager dataManager) {
        super(view, dataManager);
    }
}
