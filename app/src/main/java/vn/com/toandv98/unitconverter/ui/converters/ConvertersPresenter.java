package vn.com.toandv98.unitconverter.ui.converters;

import java.util.List;

import vn.com.toandv98.unitconverter.data.IDataManager;
import vn.com.toandv98.unitconverter.data.entities.Unit;
import vn.com.toandv98.unitconverter.ui.base.BasePresenter;
import vn.com.toandv98.unitconverter.utils.AppUtils;
import vn.com.toandv98.unitconverter.utils.ConvertUtils;
import vn.com.toandv98.unitconverter.utils.StateUtils;
import vn.com.toandv98.unitconverter.utils.UnitUtils;

import static vn.com.toandv98.unitconverter.utils.Constrants.CURRENCY;
import static vn.com.toandv98.unitconverter.utils.Constrants.DEFAULT_INPUT;
import static vn.com.toandv98.unitconverter.utils.Constrants.INPUT_UNIT;
import static vn.com.toandv98.unitconverter.utils.Constrants.RESULT_UNIT;

public class ConvertersPresenter extends BasePresenter<ConvertersContract.View, IDataManager> implements ConvertersContract.Presenter {

    public ConvertersPresenter(ConvertersContract.View view, IDataManager dataManager) {
        super(view, dataManager);
    }

    @Override
    public void onReceivedConversionId(int id) {

        StateUtils.setConversionId(id);
        StateUtils.setCurrentInput(DEFAULT_INPUT);
        view.setTitle(AppUtils.getTitleResFromId(id));
        view.updateResultValue(DEFAULT_INPUT);
        view.focusInput();
        List<Unit> units = UnitUtils.getInstance().getUnitById(id);

        if (id == CURRENCY) {
            dataManager.updateFromRemote();
            units = dataManager.getCurrencyUnits();
        }

        Unit unit = units.get(0);
        StateUtils.setInputUnit(unit);
        StateUtils.setResultUnit(unit);

        view.loadUnit(units);
        view.updateInputUnit(unit.getLabelRes());
        view.updateResultUnit(unit.getLabelRes());
    }

    @Override
    public void onReceiveUpdateRates(String msg) {
        view.showMessage(msg);
    }

    @Override
    public void onSwapButtonClick() {
        StateUtils.swapUnit();
        view.swapConversion();
    }

    @Override
    public void onClearItemClick() {
        StateUtils.setCurrentInput(DEFAULT_INPUT);
        view.clearInputValue();
    }

    @Override
    public void onUnitItemClick(int typeUnit, Unit unit) {
        switch (typeUnit) {
            case INPUT_UNIT:
                view.updateInputUnit(unit.getLabelRes());
                StateUtils.setInputUnit(unit);
                this.convert(StateUtils.getCurrentInput());
                break;

            case RESULT_UNIT:
                view.updateResultUnit(unit.getLabelRes());
                StateUtils.setResultUnit(unit);
                this.convert(StateUtils.getCurrentInput());
                break;
        }
    }

    @Override
    public void onInputValueChanged(CharSequence s) {
        StateUtils.setCurrentInput(s);
        convert(s);
    }

    private void convert(CharSequence s) {
        if (s.length() != 0 && !s.toString().equals(".")) {
            view.updateResultValue(ConvertUtils.convert(Double.parseDouble(s.toString())));
        } else {
            view.updateResultValue(DEFAULT_INPUT);
        }
    }
}
