package vn.com.toandv98.unitconverter.ui.unitsearch;

import java.util.List;

import vn.com.toandv98.unitconverter.data.IDataManager;
import vn.com.toandv98.unitconverter.data.entities.Unit;
import vn.com.toandv98.unitconverter.ui.base.BasePresenter;
import vn.com.toandv98.unitconverter.utils.StateUtils;
import vn.com.toandv98.unitconverter.utils.UnitUtils;

import static vn.com.toandv98.unitconverter.utils.Constrants.CURRENCY;

public class UnitSearchPresenter extends BasePresenter<UnitSearchContract.View, IDataManager> implements UnitSearchContract.Presenter {
    public UnitSearchPresenter(UnitSearchContract.View view, IDataManager dataManager) {
        super(view, dataManager);
    }

    @Override
    public void onReceivedBundle(int typeUnit) {
        int id = StateUtils.getConversionId();
        view.focusInput();
        List<Unit> units = UnitUtils.getInstance().getUnitById(id);

        if (id == CURRENCY) {
            units = dataManager.getCurrencyUnits();
        }

        view.loadUnit(units);
    }
}
