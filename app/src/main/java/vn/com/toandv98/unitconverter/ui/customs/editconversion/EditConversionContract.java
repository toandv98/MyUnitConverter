package vn.com.toandv98.unitconverter.ui.customs.editconversion;

import java.util.List;

import vn.com.toandv98.unitconverter.data.entities.CustomConversion;
import vn.com.toandv98.unitconverter.data.entities.CustomUnit;
import vn.com.toandv98.unitconverter.ui.base.IBasePresenter;
import vn.com.toandv98.unitconverter.ui.base.IBaseView;

public interface EditConversionContract {
    interface View extends IBaseView {

        void setConversionName(String label);

        void loadRecyclerView(List<CustomUnit> customUnits);

        void updateUnitItem(int position);

        void addUnitItem(int position);

        void showSnackBar();

        void showError();

        void finishOk(boolean isAdd);

        void deleteUnitItem(int position);

        void showUnitDialog(CustomUnit unit, boolean isAdd);
    }

    interface Presenter extends IBasePresenter {

        void onReceivedId(CustomConversion conversion);

        void onFabAddUnitClick();

        void onMenuItemSaveClick(String label);

        void onItemUnitClick(int position);

        void onItemDelClick(int position);

        void onUpdateUnit(CustomUnit unit);

        void onAddUnit(CustomUnit unit);

        void onUndoClick();
    }
}
