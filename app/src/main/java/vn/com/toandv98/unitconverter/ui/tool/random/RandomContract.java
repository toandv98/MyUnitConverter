package vn.com.toandv98.unitconverter.ui.tool.random;

import vn.com.toandv98.unitconverter.ui.base.IBasePresenter;
import vn.com.toandv98.unitconverter.ui.base.IBaseView;

public interface RandomContract {
    interface View extends IBaseView {
        void navigateBack();

        void showResult(String result);

        void showErrorFrom();

        void showErrorTo();

        void clearAll();
    }

    interface Presenter extends IBasePresenter {
        void onFabBackClick();

        void onFabRandomClick(String from, String to);

        void onFabClearClick();
    }
}
