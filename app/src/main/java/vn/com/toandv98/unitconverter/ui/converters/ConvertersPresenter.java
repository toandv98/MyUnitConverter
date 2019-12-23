package vn.com.toandv98.unitconverter.ui.converters;

import java.util.List;

import vn.com.toandv98.unitconverter.data.IDataManager;
import vn.com.toandv98.unitconverter.data.entities.Unit;
import vn.com.toandv98.unitconverter.ui.base.BasePresenter;
import vn.com.toandv98.unitconverter.utils.ConvertUtils;

import static vn.com.toandv98.unitconverter.utils.Constrants.CURRENCY;
import static vn.com.toandv98.unitconverter.utils.Constrants.DEFAULT_INPUT;
import static vn.com.toandv98.unitconverter.utils.Constrants.DEFAULT_POSITION;
import static vn.com.toandv98.unitconverter.utils.Constrants.INPUT_UNIT;
import static vn.com.toandv98.unitconverter.utils.Constrants.RESULT_UNIT;
import static vn.com.toandv98.unitconverter.utils.Constrants.TEMPERATURE;

public class ConvertersPresenter extends BasePresenter<ConvertersContract.View, IDataManager> implements ConvertersContract.Presenter {

    private List<Unit> mUnits;
    private int mConversionId;
    private CharSequence mCurrentInput = DEFAULT_INPUT;
    private Unit mInputUnit;
    private Unit mResultUnit;

    public ConvertersPresenter(ConvertersContract.View view, IDataManager dataManager) {
        super(view, dataManager);
    }

    @Override
    public void onReceivedConversionId(int id) {
        mConversionId = id;
        if (id == CURRENCY) {
            dataManager.updateFromRemote();
        }
        mUnits = dataManager.getUnitsByConversionId(mConversionId);
        mInputUnit = mUnits.get(DEFAULT_POSITION);
        mResultUnit = mInputUnit;

        view.setTitle(dataManager.getConversionById(mConversionId).getTitleRes());
        view.focusInput();
        view.loadUnit(mUnits);
        view.updateInputUnit(mInputUnit.getLabelRes());
        view.updateResultUnit(mResultUnit.getLabelRes());
        view.updateResultValue(DEFAULT_INPUT);
    }

    @Override
    public void onReceiveUpdateRates(String msg) {
        view.showMessage(msg);
        mUnits = dataManager.getUnitsByConversionId(mConversionId);
    }

    @Override
    public void onSwapButtonClick() {
        Unit temp = mInputUnit;
        mInputUnit = mResultUnit;
        mResultUnit = temp;
        view.swapConversion(mInputUnit.getLabelRes(), mResultUnit.getLabelRes());
        convert(mCurrentInput);
    }

    @Override
    public void onClearItemClick() {
        mCurrentInput = DEFAULT_INPUT;
        view.clearInputValue();
    }

    @Override
    public void onFabInputUnitClick() {
        view.navigateToUnitSearch(INPUT_UNIT, mConversionId);
    }

    @Override
    public void onFabResultUnitClick() {
        view.navigateToUnitSearch(RESULT_UNIT, mConversionId);
    }

    @Override
    public void onInputUnitSelect(int position) {
        Unit unit = mUnits.get(position);
        view.updateInputUnit(unit.getLabelRes());
        view.updateRadioInput(position);
        mInputUnit = unit;
        convert(mCurrentInput);
    }

    @Override
    public void onResultUnitSelect(int position) {
        Unit unit = mUnits.get(position);
        view.updateResultUnit(unit.getLabelRes());
        view.updateRadioResult(position);
        mResultUnit = unit;
        convert(mCurrentInput);
    }

    @Override
    public void onInputValueChanged(CharSequence s) {
        mCurrentInput = s;
        convert(s);
    }

    @Override
    public void onFragmentSearchFinished(int resultCode, int position) {
        view.focusInput();
        if (resultCode == INPUT_UNIT) {
            mInputUnit = mUnits.get(position);
            view.updateRadioInput(position);
            view.updateInputUnit(mInputUnit.getLabelRes());
        } else if (resultCode == RESULT_UNIT) {
            mResultUnit = mUnits.get(position);
            view.updateRadioResult(position);
            view.updateResultUnit(mResultUnit.getLabelRes());
        }
        convert(mCurrentInput);
    }

    private void convert(CharSequence s) {
        String result = DEFAULT_INPUT;
        if (s.length() != 0 && !s.toString().equals(".")) {
            if (mConversionId == TEMPERATURE) {
                result = ConvertUtils.convertTemperature(Double.parseDouble(s.toString()), mInputUnit, mResultUnit);
            } else {
                result = ConvertUtils.convert(Double.parseDouble(s.toString()), mInputUnit, mResultUnit);
            }
        }
        view.updateResultValue(result);
    }
}
