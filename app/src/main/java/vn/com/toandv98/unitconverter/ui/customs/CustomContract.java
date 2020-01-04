package vn.com.toandv98.unitconverter.ui.customs;

import java.util.List;

import vn.com.toandv98.unitconverter.data.entities.CustomConversion;
import vn.com.toandv98.unitconverter.ui.base.IBasePresenter;
import vn.com.toandv98.unitconverter.ui.base.IBaseView;

public interface CustomContract {

    interface OnSwipeListener {

        void onSwipeLeft(int position);

        void onSwipeRight(int position);
    }

    interface View extends IBaseView {
        void loadRecyclerView(List<CustomConversion> data);

        void addConversionToList(int position);

        void updateConversionOnList(int position);

        void removeConversionFromList(int position);

        void navigateToAddConversion();

        void showSnackBar();
    }

    interface Presenter extends IBasePresenter {

        void onSwipeLeft(int position);

        void onSwipeRight(int position);

        void onSnackBarDismissed();

        void onUndoClick();

        void onSetupView();

        void onFabAddClick();

        void onItemClick(int pos);

        void onAddedConversions();
    }
}
