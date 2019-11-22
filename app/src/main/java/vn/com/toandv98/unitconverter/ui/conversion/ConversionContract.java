package vn.com.toandv98.unitconverter.ui.conversion;

import vn.com.toandv98.unitconverter.ui.base.IBasePresenter;
import vn.com.toandv98.unitconverter.ui.base.IBaseView;

public interface ConversionContract {
    interface View extends IBaseView {
        void navigateToConverters(int id);
    }

    interface Presenter extends IBasePresenter {
        void onRecyclerViewItemClick(int id);
    }
}
