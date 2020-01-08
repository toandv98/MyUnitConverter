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
    private CustomUnit mUndoUnit;
    private CustomConversion mNewConversion;
    private int mPosition;
    private boolean mIsAdd;

    @Override
    public void onReceivedId(CustomConversion conversion) {
        mNewConversion = conversion;
        int conversionId = conversion.getId();
        if (conversionId == 0) {
            mIsAdd = true;
            mUnits = new ArrayList<>();
        } else {
            mIsAdd = false;
            mUnits = dataManager.getUnitCustomsByConversionId(conversionId);
        }
        view.setConversionName(conversion.getLabel());
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
            if (mIsAdd) {
                dataManager.insertConversionWithUnits(mNewConversion, mUnits);
            } else {
                dataManager.updateConversionWithUnits(mNewConversion, mUnits);
            }
            view.finishOk(mIsAdd);
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
    public void onUndoClick() {
        mUnits.add(mPosition, mUndoUnit);
        view.addUnitItem(mPosition);
    }
}
