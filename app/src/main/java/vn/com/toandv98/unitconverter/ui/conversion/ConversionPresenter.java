package vn.com.toandv98.unitconverter.ui.conversion;

import vn.com.toandv98.unitconverter.data.IDataManager;
import vn.com.toandv98.unitconverter.ui.base.BasePresenter;

public class ConversionPresenter extends BasePresenter<ConversionContract.View, IDataManager>
        implements ConversionContract.Presenter {


    public ConversionPresenter(ConversionContract.View view, IDataManager dataManager) {
        super(view, dataManager);
    }

    @Override
    public void onRecyclerViewItemClick(int id) {
        view.navigateToConverters(id);
    }
}
