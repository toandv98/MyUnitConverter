package vn.com.toandv98.unitconverter.ui.conversion;

import java.util.List;

import vn.com.toandv98.unitconverter.data.entities.ConversionItem;
import vn.com.toandv98.unitconverter.ui.base.IBasePresenter;
import vn.com.toandv98.unitconverter.ui.base.IBaseView;

public interface ConversionContract {
    interface View extends IBaseView {
        void navigateToConverters(int id);

        void loadRecyclerView(List<ConversionItem> items);
    }

    interface Presenter extends IBasePresenter {
        void onSetupView();
        void onRecyclerViewItemClick(int id);
    }
}
