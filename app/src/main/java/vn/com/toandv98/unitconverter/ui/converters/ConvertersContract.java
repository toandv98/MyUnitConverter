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

        void loadUnit(List<Unit> units);

        void clearInputValue();

        void updateResultValue(String result);
    }

    interface Presenter extends IBasePresenter {
        void onReceiveId(int id);

        void onSwapButtonClick();

        void onClearItemClick();

        void onUnitItemClick(int typeUnit, Unit unit);

        void onInputValueChanged(CharSequence s);
    }
}
