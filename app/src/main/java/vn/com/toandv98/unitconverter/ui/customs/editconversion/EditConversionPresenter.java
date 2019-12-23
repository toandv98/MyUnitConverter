package vn.com.toandv98.unitconverter.ui.customs.editconversion;

import java.util.ArrayList;
import java.util.List;

import vn.com.toandv98.unitconverter.data.IDataManager;
import vn.com.toandv98.unitconverter.data.entities.CustomConversion;
import vn.com.toandv98.unitconverter.data.entities.CustomUnit;
import vn.com.toandv98.unitconverter.ui.base.BasePresenter;

public class EditConversionPresenter extends BasePresenter<EditConversionContract.View, IDataManager>
        implements EditConversionContract.Presenter {
    public EditConversionPresenter(EditConversionContract.View view, IDataManager dataManager) {
        super(view, dataManager);
    }

    private List<CustomUnit> mUnits;
    private int mPosition;
    private CustomUnit mUndoUnit;

    @Override
    public void onReceivedId(int id) {
        mUnits = new ArrayList<>();
        view.loadRecyclerView(mUnits);
    }

    @Override
    public void onFabAddUnitClick() {
        view.showUnitDialog(new CustomUnit("", 0, 0, 0), true);
    }

    @Override
    public void onMenuItemSaveClick(String label) {
        if (label.trim().length() == 0 || mUnits.size() == 0) {
            view.showError();
        } else {
            CustomConversion conversion = new CustomConversion(label, 0);
            dataManager.insertConversionWithUnits(conversion, mUnits);
            view.finishOk();
        }
    }

    @Override
    public void onItemUnitClick(int position) {
        mPosition = position;
        view.showUnitDialog(mUnits.get(mPosition), false);
    }

    @Override
    public void onItemDelClick(int position) {
        mPosition = position;
        mUndoUnit = mUnits.get(mPosition);
        mUnits.remove(mPosition);
        view.deleteUnitItem(mPosition);
        view.showSnackBar();
    }

    @Override
    public void onUpdateUnit(CustomUnit unit) {
        mUnits.set(mPosition, unit);
        view.updateUnitItem(mPosition);
    }

    @Override
    public void onAddUnit(CustomUnit unit) {
        mUnits.add(unit);
        view.addUnitItem(mUnits.size() - 1);
    }

    @Override
    public void onUndoDelete() {
        mUnits.add(mPosition, mUndoUnit);
        view.addUnitItem(mPosition);
    }
}
