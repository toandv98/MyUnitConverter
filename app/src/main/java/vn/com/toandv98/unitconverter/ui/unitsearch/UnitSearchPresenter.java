package vn.com.toandv98.unitconverter.ui.unitsearch;

import java.util.List;

import vn.com.toandv98.unitconverter.data.IDataManager;
import vn.com.toandv98.unitconverter.data.entities.Unit;
import vn.com.toandv98.unitconverter.ui.base.BasePresenter;

import static vn.com.toandv98.unitconverter.utils.Constrants.INPUT_UNIT;
import static vn.com.toandv98.unitconverter.utils.StateUtils.CURRENT_INPUT_POSITION;
import static vn.com.toandv98.unitconverter.utils.StateUtils.CURRENT_INPUT_UNIT;
import static vn.com.toandv98.unitconverter.utils.StateUtils.CURRENT_RESULT_POSITION;
import static vn.com.toandv98.unitconverter.utils.StateUtils.CURRENT_RESULT_UNIT;

public class UnitSearchPresenter extends BasePresenter<UnitSearchContract.View, IDataManager> implements UnitSearchContract.Presenter {

    private int unitType;

    public UnitSearchPresenter(UnitSearchContract.View view, IDataManager dataManager) {
        super(view, dataManager);
    }

    @Override
    public void onReceivedBundle(int unitType, int conversionId) {
        this.unitType = unitType;
        List<Unit> units = dataManager.getUnitsByConversionId(conversionId);
        view.focusInput();
        view.loadUnit(units);
    }

    @Override
    public void onItemSearchClick(Unit unit, int position) {
        if (unitType == INPUT_UNIT) {
            CURRENT_INPUT_UNIT = unit;
            CURRENT_INPUT_POSITION = position;
        } else {
            CURRENT_RESULT_UNIT = unit;
            CURRENT_RESULT_POSITION = position;
        }
        view.finishFragment();
    }
}
