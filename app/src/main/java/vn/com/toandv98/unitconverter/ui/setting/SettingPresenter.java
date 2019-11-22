package vn.com.toandv98.unitconverter.ui.setting;

import vn.com.toandv98.unitconverter.data.IDataManager;
import vn.com.toandv98.unitconverter.ui.base.BasePresenter;

public class SettingPresenter extends BasePresenter<SettingContract.View, IDataManager> implements SettingContract.Presenter {

    public SettingPresenter(SettingContract.View view, IDataManager dataManager) {
        super(view, dataManager);
    }
}
