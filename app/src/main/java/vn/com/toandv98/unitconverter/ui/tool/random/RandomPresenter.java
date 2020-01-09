package vn.com.toandv98.unitconverter.ui.tool.random;

import java.util.Random;

import vn.com.toandv98.unitconverter.data.IDataManager;
import vn.com.toandv98.unitconverter.ui.base.BasePresenter;

import static vn.com.toandv98.unitconverter.ui.tool.random.RandomContract.Presenter;
import static vn.com.toandv98.unitconverter.ui.tool.random.RandomContract.View;

public class RandomPresenter extends BasePresenter<View, IDataManager> implements Presenter {
    public RandomPresenter(View view, IDataManager dataManager) {
        super(view, dataManager);
    }

    @Override
    public void onFabBackClick() {
        view.navigateBack();
    }

    @Override
    public void onFabRandomClick(String from, String to) {
        if (from.trim().length() == 0) {
            view.showErrorFrom();
        } else if (to.trim().length() == 0) {
            view.showErrorTo();
        } else {
            view.showResult(randomNumber(from, to));
        }
    }

    @Override
    public void onFabClearClick() {
        view.clearAll();
    }

    private String randomNumber(String from, String to) {
        int fromNum = Integer.parseInt(from);
        int toNum = Integer.parseInt(to);
        String result = "??";
        if (toNum > fromNum) {
            Random r = new Random();
            result = String.valueOf(r.nextInt(toNum - fromNum) + fromNum);
        }
        return result;
    }
}
