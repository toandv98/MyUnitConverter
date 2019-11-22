package vn.com.toandv98.unitconverter.data;

import android.content.Context;
import android.content.Intent;

import androidx.annotation.DrawableRes;
import androidx.annotation.StringRes;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import vn.com.toandv98.unitconverter.data.entities.Unit;
import vn.com.toandv98.unitconverter.data.local.PreferencesHelper;
import vn.com.toandv98.unitconverter.services.UpdateCurrencyService;

public class DataManager implements IDataManager {

    private Context context;

    public DataManager(Context context) {
        this.context = context;
    }

    @Override
    public void updateFromRemote() {
        UpdateCurrencyService.enqueueWork(context, new Intent());
    }

    @Override
    public Map<String, Double> getLastRates() {
        return PreferencesHelper.readFromSP(context);
    }

    @Override
    public List<Unit> getCurrencyUnits() {
        List<Unit> results = new ArrayList<>();
        int unitId = 1400;
        for (Map.Entry<String, Double> entry : getLastRates().entrySet()) {
            Double value = entry.getValue();
            String key = entry.getKey().toLowerCase();
            Unit unit = new Unit(unitId++, toStringResId(key + "_"), toDrawableResId("ic_" + key), 1 / value, value);
            results.add(unit);
        }
        return results;
    }

    @StringRes
    private int toStringResId(String name) {
        return context.getResources().getIdentifier(name, "string", context.getPackageName());
    }

    @DrawableRes
    private int toDrawableResId(String name) {
        return context.getResources().getIdentifier(name, "drawable", context.getPackageName());
    }
}
