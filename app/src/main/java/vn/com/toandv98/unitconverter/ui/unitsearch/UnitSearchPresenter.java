package vn.com.toandv98.unitconverter.ui.unitsearch;

import java.util.List;

import vn.com.toandv98.unitconverter.data.IDataManager;
import vn.com.toandv98.unitconverter.data.entities.Unit;
import vn.com.toandv98.unitconverter.ui.base.BasePresenter;
import vn.com.toandv98.unitconverter.utils.StateUtils;

import static vn.com.toandv98.unitconverter.utils.Constrants.CURRENCY;
import static vn.com.toandv98.unitconverter.utils.Constrants.INPUT_UNIT;

public class UnitSearchPresenter extends BasePresenter<UnitSearchContract.View, IDataManager> implements UnitSearchContract.Presenter {

    private int typeUnit;

    public UnitSearchPresenter(UnitSearchContract.View view, IDataManager dataManager) {
        super(view, dataManager);
    }

    @Override
    public void onReceivedBundle(int typeUnit) {
        this.typeUnit = typeUnit;
        int id = StateUtils.getConversionId();
        view.focusInput();
        List<Unit> units = dataManager.getConversion(id).getUnits();

        if (id == CURRENCY) {
            units = dataManager.getCurrencyUnits();
        }

        view.loadUnit(units);
    }

    @Override
    public void onItemSearchClick(Unit unit, int position) {
        if (typeUnit == INPUT_UNIT) {
            StateUtils.setInputUnit(unit, position);
        } else {
            StateUtils.setResultUnit(unit, position);
        }
        view.finishFragment();
    }
}
