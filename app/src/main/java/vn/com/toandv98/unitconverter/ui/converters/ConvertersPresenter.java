package vn.com.toandv98.unitconverter.ui.converters;

import java.util.List;

import vn.com.toandv98.unitconverter.data.IDataManager;
import vn.com.toandv98.unitconverter.data.entities.Unit;
import vn.com.toandv98.unitconverter.ui.base.BasePresenter;
import vn.com.toandv98.unitconverter.utils.ConvertUtils;
import vn.com.toandv98.unitconverter.utils.StateUtils;

import static vn.com.toandv98.unitconverter.utils.Constrants.CURRENCY;
import static vn.com.toandv98.unitconverter.utils.Constrants.DEFAULT_INPUT;
import static vn.com.toandv98.unitconverter.utils.Constrants.DEFAULT_POSITION;
import static vn.com.toandv98.unitconverter.utils.Constrants.INPUT_UNIT;
import static vn.com.toandv98.unitconverter.utils.Constrants.RESULT_UNIT;

public class ConvertersPresenter extends BasePresenter<ConvertersContract.View, IDataManager> implements ConvertersContract.Presenter {

    private List<Unit> units;

    public ConvertersPresenter(ConvertersContract.View view, IDataManager dataManager) {
        super(view, dataManager);
    }

    @Override
    public void onReceivedConversionId(int id) {

        units = dataManager.getConversion(id).getUnits();
        if (id == CURRENCY) {
            dataManager.updateFromRemote();
            units = dataManager.getCurrencyUnits();
        }
        Unit unit = units.get(DEFAULT_POSITION);
        StateUtils.setCurrentState(id, DEFAULT_POSITION, DEFAULT_POSITION, DEFAULT_INPUT, unit, unit);

        view.setTitle(dataManager.getConversion(id).getTitleRes());
        view.focusInput();
        view.loadUnit(units);
        view.updateInputUnit(unit.getLabelRes());
        view.updateResultUnit(unit.getLabelRes());
        view.updateResultValue(DEFAULT_INPUT);
    }

    @Override
    public void onReceiveUpdateRates(String msg) {
        view.showMessage(msg);
        units = dataManager.getCurrencyUnits();
    }

    @Override
    public void onSwapButtonClick() {
        StateUtils.swapUnit();
        view.swapConversion();
        convert(StateUtils.getCurrentInput());
    }

    @Override
    public void onClearItemClick() {
        StateUtils.setCurrentInput(DEFAULT_INPUT);
        view.clearInputValue();
    }

    @Override
    public void onFabInputUnitClick() {
        view.navigateToUnitSearch(INPUT_UNIT);
    }

    @Override
    public void onFabResultUnitClick() {
        view.navigateToUnitSearch(RESULT_UNIT);
    }

    @Override
    public void onInputUnitSelect(int position) {
        Unit unit = units.get(position);
        view.updateInputUnit(unit.getLabelRes());
        view.updateRadioInput(position);
        StateUtils.setInputUnit(unit, position);
        convert(StateUtils.getCurrentInput());
    }

    @Override
    public void onResultUnitSelect(int position) {
        Unit unit = units.get(position);
        view.updateResultUnit(unit.getLabelRes());
        view.updateRadioResult(position);
        StateUtils.setResultUnit(unit, position);
        convert(StateUtils.getCurrentInput());
    }

    @Override
    public void onInputValueChanged(CharSequence s) {
        StateUtils.setCurrentInput(s);
        convert(s);
    }

    @Override
    public void onFragmentSearchFinished() {
        view.focusInput();
        view.updateRadioInput(StateUtils.getCurrentInputPosition());
        view.updateRadioResult(StateUtils.getCurrentResultPosition());
        view.updateInputUnit(StateUtils.getInputUnit().getLabelRes());
        view.updateResultUnit(StateUtils.getResultUnit().getLabelRes());
        convert(StateUtils.getCurrentInput());
    }

    private void convert(CharSequence s) {
        if (s.length() != 0 && !s.toString().equals(".")) {
            view.updateResultValue(ConvertUtils.convert(Double.parseDouble(s.toString())));
        } else {
            view.updateResultValue(DEFAULT_INPUT);
        }
    }
}
