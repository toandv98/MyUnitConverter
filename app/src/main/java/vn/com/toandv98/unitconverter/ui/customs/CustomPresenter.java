package vn.com.toandv98.unitconverter.ui.customs;

import java.util.ArrayList;
import java.util.List;

import vn.com.toandv98.unitconverter.data.IDataManager;
import vn.com.toandv98.unitconverter.data.entities.CustomConversion;
import vn.com.toandv98.unitconverter.ui.base.BasePresenter;

public class CustomPresenter extends BasePresenter<CustomContract.View, IDataManager> implements CustomContract.Presenter {

    private List<CustomConversion> mConversions;
    private List<CustomConversion> mUndoConversions;
    private int mUndoPosition, mUpdatePosition;

    public CustomPresenter(CustomContract.View view, IDataManager dataManager) {
        super(view, dataManager);
        mUndoConversions = new ArrayList<>(2);
    }

    @Override
    public void onSwipeLeft(int position) {
        mUndoPosition = position;
        mUndoConversions.add(mConversions.get(position));
        mConversions.remove(position);
        view.removeConversionFromList(position);
        view.showSnackBar();
    }

    @Override
    public void onSwipeRight(int position) {
        mUpdatePosition = position;
        view.navigateToAddConversion(mConversions.get(mUpdatePosition));
        view.updateConversionOnList(mUpdatePosition);
    }

    @Override
    public void onSnackBarDismissed() {

        if (!mUndoConversions.isEmpty() && dataManager != null) {
            dataManager.deleteConversions(mUndoConversions.get(0));
            mUndoConversions.remove(0);
        }
    }

    @Override
    public void onUndoClick() {
        mConversions.add(mUndoPosition, mUndoConversions.get(0));
        mUndoConversions.remove(0);
        view.addConversionToList(mUndoPosition);
    }

    @Override
    public void onSetupView() {
        mConversions = dataManager.getCustomConversions();
        view.loadRecyclerView(mConversions);
    }

    @Override
    public void onFabAddClick() {
        view.navigateToAddConversion(new CustomConversion("", 0));
    }

    @Override
    public void onItemClick(int pos) {
        int id = mConversions.get(pos).getId();
        dataManager.updateHistory(id);
        view.navigateToConverters(id);
    }

    @Override
    public void onEditConversionsResult(boolean isAdd) {
        getLastData();
        if (isAdd) {
            view.addConversionToList(mConversions.size() - 1);
        } else {
            view.updateConversionOnList(mUpdatePosition);
        }
    }

    private void getLastData() {
        mConversions.clear();
        mConversions.addAll(dataManager.getCustomConversions());
    }
}
