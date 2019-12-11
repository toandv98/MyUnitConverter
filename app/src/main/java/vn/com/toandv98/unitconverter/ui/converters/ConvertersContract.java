package vn.com.toandv98.unitconverter.ui.converters;

import androidx.annotation.StringRes;

import java.util.List;

import vn.com.toandv98.unitconverter.data.entities.Unit;
import vn.com.toandv98.unitconverter.ui.base.IBasePresenter;
import vn.com.toandv98.unitconverter.ui.base.IBaseView;

public interface ConvertersContract {
    interface View extends IBaseView {
        void setTitle(@StringRes int resId);

        void focusInput();

        void swapConversion();

        void updateInputUnit(@StringRes int labelRes);

        void updateResultUnit(@StringRes int labelRes);

        void updateRadioInput(int position);

        void updateRadioResult(int position);

        void loadUnit(List<Unit> units);

        void clearInputValue();

        void updateResultValue(String result);

        void navigateToUnitSearch(int typeUnit);
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

        void onFragmentSearchFinished();
    }
}
