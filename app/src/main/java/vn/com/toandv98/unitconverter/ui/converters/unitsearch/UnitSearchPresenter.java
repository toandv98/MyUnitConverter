package vn.com.toandv98.unitconverter.ui.converters.unitsearch;

import java.util.List;

import vn.com.toandv98.unitconverter.data.IDataManager;
import vn.com.toandv98.unitconverter.data.entities.Unit;
import vn.com.toandv98.unitconverter.ui.base.BasePresenter;

public class UnitSearchPresenter extends BasePresenter<UnitSearchContract.View, IDataManager> implements UnitSearchContract.Presenter {

    private int mResultCode;

    public UnitSearchPresenter(UnitSearchContract.View view, IDataManager dataManager) {
        super(view, dataManager);
    }

    @Override
    public void onReceivedBundle(int resultCode, int conversionId) {
        this.mResultCode = resultCode;
        List<Unit> units = dataManager.getUnitsByConversionId(conversionId);
        view.focusInput();
        view.loadUnit(units);
    }

    @Override
    public void onItemSearchClick(Unit unit, int position) {
        view.finishFragment(mResultCode, position);
    }
}