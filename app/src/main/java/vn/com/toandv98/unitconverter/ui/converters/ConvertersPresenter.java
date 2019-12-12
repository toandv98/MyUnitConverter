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
import static vn.com.toandv98.unitconverter.utils.Constrants.TEMPERATURE;
import static vn.com.toandv98.unitconverter.utils.StateUtils.CURRENT_INPUT;
import static vn.com.toandv98.unitconverter.utils.StateUtils.CURRENT_INPUT_POSITION;
import static vn.com.toandv98.unitconverter.utils.StateUtils.CURRENT_INPUT_UNIT;
import static vn.com.toandv98.unitconverter.utils.StateUtils.CURRENT_RESULT_POSITION;
import static vn.com.toandv98.unitconverter.utils.StateUtils.CURRENT_RESULT_UNIT;

public class ConvertersPresenter extends BasePresenter<ConvertersContract.View, IDataManager> implements ConvertersContract.Presenter {

    private List<Unit> units;
    private int conversionId;

    public ConvertersPresenter(ConvertersContract.View view, IDataManager dataManager) {
        super(view, dataManager);
    }

    @Override
    public void onReceivedConversionId(int id) {
        conversionId = id;
        if (id == CURRENCY) {
            dataManager.updateFromRemote();
        }
        units = dataManager.getUnitsByConversionId(conversionId);
        Unit unit = units.get(DEFAULT_POSITION);
        StateUtils.setDefaultState(unit);

        view.setTitle(dataManager.getConversionById(conversionId).getTitleRes());
        view.focusInput();
        view.loadUnit(units);
        view.updateInputUnit(unit.getLabelRes());
        view.updateResultUnit(unit.getLabelRes());
        view.updateResultValue(DEFAULT_INPUT);
    }

    @Override
    public void onReceiveUpdateRates(String msg) {
        view.showMessage(msg);
        units = dataManager.getUnitsByConversionId(conversionId);
    }

    @Override
    public void onSwapButtonClick() {
        StateUtils.swapUnit();
        view.swapConversion();
        convert(CURRENT_INPUT);
    }

    @Override
    public void onClearItemClick() {
        CURRENT_INPUT = DEFAULT_INPUT;
        view.clearInputValue();
    }

    @Override
    public void onFabInputUnitClick() {
        view.navigateToUnitSearch(INPUT_UNIT, conversionId);
    }

    @Override
    public void onFabResultUnitClick() {
        view.navigateToUnitSearch(RESULT_UNIT, conversionId);
    }

    @Override
    public void onInputUnitSelect(int position) {
        Unit unit = units.get(position);
        view.updateInputUnit(unit.getLabelRes());
        view.updateRadioInput(position);
        CURRENT_INPUT_UNIT = unit;
        CURRENT_INPUT_POSITION = position;
        convert(CURRENT_INPUT);
    }

    @Override
    public void onResultUnitSelect(int position) {
        Unit unit = units.get(position);
        view.updateResultUnit(unit.getLabelRes());
        view.updateRadioResult(position);
        CURRENT_RESULT_UNIT = unit;
        CURRENT_RESULT_POSITION = position;
        convert(CURRENT_INPUT);
    }

    @Override
    public void onInputValueChanged(CharSequence s) {
        CURRENT_INPUT = s;
        convert(s);
    }

    @Override
    public void onFragmentSearchFinished() {
        view.focusInput();
        view.updateRadioInput(CURRENT_INPUT_POSITION);
        view.updateRadioResult(CURRENT_RESULT_POSITION);
        view.updateInputUnit(CURRENT_INPUT_UNIT.getLabelRes());
        view.updateResultUnit(CURRENT_RESULT_UNIT.getLabelRes());
        convert(CURRENT_INPUT);
    }

    private void convert(CharSequence s) {
        String result = DEFAULT_INPUT;
        if (s.length() != 0 && !s.toString().equals(".")) {
            if (conversionId == TEMPERATURE) {
                result = ConvertUtils.convertTemperature(Double.parseDouble(s.toString()));
            } else {
                result = ConvertUtils.convert(Double.parseDouble(s.toString()));
            }
        }
        view.updateResultValue(result);
    }
}
