package vn.com.toandv98.unitconverter.ui.unitsearch;

import java.util.List;

import vn.com.toandv98.unitconverter.data.entities.Unit;
import vn.com.toandv98.unitconverter.ui.base.IBasePresenter;
import vn.com.toandv98.unitconverter.ui.base.IBaseView;

public interface UnitSearchContract {
    interface View extends IBaseView {
        void focusInput();

        void loadUnit(List<Unit> units);
    }

    interface Presenter extends IBasePresenter {
        void onReceivedBundle(int typeUnit);
    }
}
