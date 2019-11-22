package vn.com.toandv98.unitconverter.ui.base;

import androidx.annotation.StringRes;

public interface IBaseView {
    void showMessage(String msg);

    void showMessage(@StringRes int resId);
}
