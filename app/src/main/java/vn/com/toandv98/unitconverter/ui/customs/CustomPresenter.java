package vn.com.toandv98.unitconverter.ui.customs;

import java.util.ArrayList;
import java.util.List;

import vn.com.toandv98.unitconverter.data.IDataManager;
import vn.com.toandv98.unitconverter.data.entities.CustomConversion;
import vn.com.toandv98.unitconverter.ui.base.BasePresenter;

public class CustomPresenter extends BasePresenter<CustomContract.View, IDataManager> implements CustomContract.Presenter {

    private List<CustomConversion> mConversions;
    private List<CustomConversion> mUndoConversions;
    private int mPosition;

    public CustomPresenter(CustomContract.View view, IDataManager dataManager) {
        super(view, dataManager);
        mUndoConversions = new ArrayList<>(2);
    }

    @Override
    public void onSwipeLeft(int position) {
        mPosition = position;
        mUndoConversions.add(mConversions.get(position));
        mConversions.remove(position);
        view.removeConversionFromList(position);
        view.showSnackBar();
    }

    @Override
    public void onSwipeRight(int position) {
        view.navigateToAddConversion();
        view.updateConversionOnList(position);
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
        mConversions.add(mPosition, mUndoConversions.get(0));
        mUndoConversions.remove(0);
        view.addConversionToList(mPosition);
    }

    @Override
    public void onSetupView() {
        mConversions = dataManager.getCustomConversions();
        view.loadRecyclerView(mConversions);
    }

    @Override
    public void onFabAddClick() {
        view.navigateToAddConversion();
    }

    @Override
    public void onItemClick(int pos) {
        dataManager.updateHistory(mConversions.get(pos).getId());
        view.showMessage(String.valueOf(pos));
    }

    @Override
    public void onAddedConversions() {
        getLastData();
        view.addConversionToList(mConversions.size() - 1);
    }

    private void getLastData() {
        mConversions.clear();
        mConversions.addAll(dataManager.getCustomConversions());
    }
}
