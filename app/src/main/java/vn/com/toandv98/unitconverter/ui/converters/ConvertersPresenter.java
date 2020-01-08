package vn.com.toandv98.unitconverter.ui.converters;

import java.text.DecimalFormat;
import java.util.List;

import vn.com.toandv98.unitconverter.data.IDataManager;
import vn.com.toandv98.unitconverter.data.entities.Conversion;
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
    private Unit mInputUnit, mResultUnit;
    private DecimalFormat mDecimalFormat;


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

        Conversion conversion = dataManager.getConversionById(mConversionId);
        view.setTitle(conversion.getTitleRes(), conversion.getTitleCustom());
        view.focusInput();
        view.loadUnit(mUnits);
        view.updateInputUnit(mInputUnit.getLabelRes(), mInputUnit.getLabelCustom());
        view.updateResultUnit(mResultUnit.getLabelRes(), mResultUnit.getLabelCustom());
        view.updateResultValue(DEFAULT_INPUT);

        mDecimalFormat = ConvertUtils.getDecimalFormat(dataManager.getDecimalPlaces(),
                dataManager.getDecimalSeparator(), dataManager.getGroupingSeparator());
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
        view.swapConversion(mInputUnit.getLabelRes(), mResultUnit.getLabelRes(),
                mInputUnit.getLabelCustom(), mResultUnit.getLabelCustom());
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
        view.updateInputUnit(unit.getLabelRes(), unit.getLabelCustom());
        view.updateRadioInput(position);
        mInputUnit = unit;
        convert(mCurrentInput);
    }

    @Override
    public void onResultUnitSelect(int position) {
        Unit unit = mUnits.get(position);
        view.updateResultUnit(unit.getLabelRes(), unit.getLabelCustom());
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
            view.updateInputUnit(mInputUnit.getLabelRes(), mInputUnit.getLabelCustom());
        } else if (resultCode == RESULT_UNIT) {
            mResultUnit = mUnits.get(position);
            view.updateRadioResult(position);
            view.updateResultUnit(mResultUnit.getLabelRes(), mResultUnit.getLabelCustom());
        }
        convert(mCurrentInput);
    }

    private void convert(CharSequence s) {
        String result = DEFAULT_INPUT;
        if (s.length() != 0 && !s.toString().equals(".")) {
            if (mConversionId == TEMPERATURE) {
                result = ConvertUtils.convertTemperature(Double.parseDouble(s.toString()), mInputUnit, mResultUnit, mDecimalFormat);
            } else {
                result = ConvertUtils.convert(Double.parseDouble(s.toString()), mInputUnit, mResultUnit, mDecimalFormat);
            }
        }
        view.updateResultValue(result);
    }
}
