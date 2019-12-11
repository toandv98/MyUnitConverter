package vn.com.toandv98.unitconverter.data;

import android.content.Context;
import android.content.Intent;

import androidx.annotation.DrawableRes;
import androidx.annotation.StringRes;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import vn.com.toandv98.unitconverter.data.entities.ConversionItem;
import vn.com.toandv98.unitconverter.data.entities.ConversionItemRoom;
import vn.com.toandv98.unitconverter.data.entities.ConversionWithUnit;
import vn.com.toandv98.unitconverter.data.entities.Unit;
import vn.com.toandv98.unitconverter.data.entities.UnitRoom;
import vn.com.toandv98.unitconverter.data.local.ConversionDao;
import vn.com.toandv98.unitconverter.data.local.ConversionDataBase;
import vn.com.toandv98.unitconverter.data.local.PreferencesHelper;
import vn.com.toandv98.unitconverter.services.UpdateCurrencyService;

public class DataManager implements IDataManager {

    private Context context;
    private static List<ConversionItem> CONVERSIONS = null;

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
        int unitId = 1400;  // Unit id >= 1400
        for (Map.Entry<String, Double> entry : getLastRates().entrySet()) {
            Double value = entry.getValue();
            String key = entry.getKey().toLowerCase();
            Unit unit = new Unit(unitId++, toStringResId(key + "_"),
                    toDrawableResId("ic_" + key), 1 / value, value);
            results.add(unit);
        }
        return results;
    }

    @Override
    public List<ConversionItem> getConversions() {
        if (CONVERSIONS == null) {
            CONVERSIONS = getDataFromRoomDB();
        }
        return CONVERSIONS;
    }

    @Override
    public ConversionItem getConversion(int id) {
        if (CONVERSIONS == null) {
            CONVERSIONS = getDataFromRoomDB();
        }
        for (ConversionItem item : CONVERSIONS) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }

    private List<ConversionItem> getDataFromRoomDB() {
        ConversionDao conversionDao = ConversionDataBase.getINSTANCE(context).conversionDao();
        List<ConversionItem> results = new ArrayList<>();
        List<ConversionWithUnit> conversionWithUnits = conversionDao.getConversionWithUnits();
        ConversionItemRoom temp;

        for (ConversionWithUnit item : conversionWithUnits) {
            temp = item.itemLocal;
            results.add(new ConversionItem(temp.getId(), toDrawableResId(temp.getImageRes()),
                    toStringResId(temp.getTitleRes()), toUnit(item.unitRooms)));
        }

        return results;
    }

    private List<Unit> toUnit(List<UnitRoom> unitRooms) {
        List<Unit> result = new ArrayList<>();
        for (UnitRoom unitR : unitRooms) {
            result.add(new Unit(unitR.getId(), toStringResId(unitR.getLabelRes()),
                    toDrawableResId(unitR.getDrawableRes()), unitR.getToBase(), unitR.getFromBase()));
        }
        return result;
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
