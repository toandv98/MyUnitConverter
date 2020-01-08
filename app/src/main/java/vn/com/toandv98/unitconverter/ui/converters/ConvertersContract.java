package vn.com.toandv98.unitconverter.ui.converters;

import androidx.annotation.StringRes;

import java.util.List;

import vn.com.toandv98.unitconverter.data.entities.Unit;
import vn.com.toandv98.unitconverter.ui.base.IBasePresenter;
import vn.com.toandv98.unitconverter.ui.base.IBaseView;

public interface ConvertersContract {
    interface View extends IBaseView {
        void setTitle(@StringRes int resId, String title);

        void focusInput();

        void swapConversion(@StringRes int inputLabelRes, @StringRes int resultLabelRes, String inputLabel, String resultLabel);

        void updateInputUnit(@StringRes int labelRes, String label);

        void updateResultUnit(@StringRes int labelRes, String label);

        void updateRadioInput(int position);

        void updateRadioResult(int position);

        void loadUnit(List<Unit> units);

        void clearInputValue();

        void updateResultValue(String result);

        void navigateToUnitSearch(int resultCode, int conversionId);
    }

    interface Presenter extends IBasePresenter {
        void onReceivedConversionId(int id);

        void onReceiveUpdateRates(String msg);

        void onSwapButtonClick();

        void onClearItemClick();

        void onFabInputUnitClick();

        void onFabResultUnitClick();

        void onInputUnitSelect(int position);

        void onResultUnitSelect(int position);

        void onInputValueChanged(CharSequence s);

        void onFragmentSearchFinished(int resultCode, int position);
    }
}
