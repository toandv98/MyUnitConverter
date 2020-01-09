package vn.com.toandv98.unitconverter.data;

import android.content.Context;
import android.content.Intent;

import androidx.annotation.DrawableRes;
import androidx.annotation.StringRes;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.com.toandv98.unitconverter.data.entities.Conversion;
import vn.com.toandv98.unitconverter.data.entities.ConversionRoom;
import vn.com.toandv98.unitconverter.data.entities.CustomConversion;
import vn.com.toandv98.unitconverter.data.entities.CustomUnit;
import vn.com.toandv98.unitconverter.data.entities.LastRates;
import vn.com.toandv98.unitconverter.data.entities.Unit;
import vn.com.toandv98.unitconverter.data.entities.UnitRoom;
import vn.com.toandv98.unitconverter.data.local.ConversionDao;
import vn.com.toandv98.unitconverter.data.local.ConversionDataBase;
import vn.com.toandv98.unitconverter.data.local.IPreferencesHelper;
import vn.com.toandv98.unitconverter.data.local.PreferencesHelper;
import vn.com.toandv98.unitconverter.data.remote.RetrofitClient;
import vn.com.toandv98.unitconverter.services.UpdateCurrencyService;

import static vn.com.toandv98.unitconverter.utils.Constrants.CURRENCY;
import static vn.com.toandv98.unitconverter.utils.Constrants.FIXED_IO_API_KEY;
import static vn.com.toandv98.unitconverter.utils.Constrants.MAP_QUERY_KEY;

public class DataManager implements IDataManager {

    private Context mContext;
    private ConversionDao mConversionDao;
    private IPreferencesHelper mPreferencesHelper;

    public DataManager(Context mContext) {
        this.mContext = mContext;
        mConversionDao = ConversionDataBase.getINSTANCE(mContext).conversionDao();
        mPreferencesHelper = new PreferencesHelper(mContext);
    }

    @Override
    public void updateFromRemote() {
        UpdateCurrencyService.enqueueWork(mContext, new Intent());
    }

    @Override
    public void fetchLastRates(RemoteCallBack callBack) {
        Map<String, String> mapQuery = new TreeMap<>();
        mapQuery.put(MAP_QUERY_KEY, FIXED_IO_API_KEY);

        RetrofitClient.getApiService().getLastRates(mapQuery).enqueue(new Callback<LastRates>() {

            @SuppressWarnings("NullableProblems")
            @Override
            public void onResponse(Call<LastRates> call, Response<LastRates> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<UnitRoom> lastRates = new ArrayList<>();
                    int unitId = 1400;
                    for (Map.Entry<String, Double> entry : response.body().getRates().entrySet()) {
                        Double value = entry.getValue();
                        String key = entry.getKey().toLowerCase();
                        UnitRoom unitR = new UnitRoom(unitId++, key + "_", "ic_" + key, 1 / value, value, CURRENCY);
                        lastRates.add(unitR);
                    }
                    mConversionDao.updateLocalRates(lastRates);
                    callBack.onSuccess(lastRates);
                }
            }

            @SuppressWarnings("NullableProblems")
            @Override
            public void onFailure(Call<LastRates> call, Throwable t) {
                callBack.onFailure(t.getMessage());
            }
        });
    }

    @Override
    public List<Conversion> getConversions() {
        List<Conversion> results = new ArrayList<>();

        for (ConversionRoom item : mConversionDao.getConversions()) {
            results.add(new Conversion(item.getId(),
                    toDrawableResId(item.getImageRes()), toStringResId(item.getTitleRes()), ""));
        }

        return results;
    }

    @Override
    public List<CustomConversion> getCustomConversions() {
        return mConversionDao.getConversionCustoms();
    }

    @Override
    public List<Unit> getUnitsByConversionId(int id) {
        List<Unit> results = new ArrayList<>();
        for (UnitRoom unitR : mConversionDao.getUnitsByConversionId(id)) {
            results.add(new Unit(unitR.getId(), toStringResId(unitR.getLabelRes()),
                    toDrawableResId(unitR.getDrawableRes()), "", unitR.getToBase(), unitR.getFromBase()));
        }

        if (results.isEmpty()) {
            for (CustomUnit customUnit : mConversionDao.getUnitCustomsByConversionId(id)) {
                results.add(new Unit(customUnit.getId(), toStringResId("blank"),
                        toDrawableResId("ic_unit_edit"), customUnit.getLabel(), customUnit.getToBase(), customUnit.getFromBase()));
            }
        }
        return results;
    }

    @Override
    public List<CustomUnit> getUnitCustomsByConversionId(int id) {
        return mConversionDao.getUnitCustomsByConversionId(id);
    }

    @Override
    public Conversion getConversionById(int id) {

        Conversion result;
        ConversionRoom conversionR = mConversionDao.getConversionById(id);
        if (conversionR != null) {
            result = new Conversion(conversionR.getId(),
                    toDrawableResId(conversionR.getImageRes()), toStringResId(conversionR.getTitleRes()), "");
        } else {
            CustomConversion customConversion = mConversionDao.getConversionCustomById(id);
            result = new Conversion(customConversion.getId(),
                    toDrawableResId("ic_unit_edit"), toStringResId("blank"), customConversion.getLabel());
        }
        return result;
    }

    @Override
    public void insertConversionWithUnits(CustomConversion custom, List<CustomUnit> customUnits) {
        mConversionDao.insertConversionWithUnits(custom, customUnits);
    }

    @Override
    public void updateConversionWithUnits(CustomConversion custom, List<CustomUnit> newUnits) {
        mConversionDao.updateConversionWithUnits(custom, newUnits);
    }

    @Override
    public void updateHistory(int id) {
        mConversionDao.updateHistory(id);
    }

    @Override
    public void deleteConversions(CustomConversion custom) {
        mConversionDao.deleteConversions(custom);
    }

    @Override
    public int getDecimalPlaces() {
        return mPreferencesHelper.getDecimalPlaces();
    }

    @Override
    public char getDecimalSeparator() {
        return mPreferencesHelper.getDecimalSeparator();
    }

    @Override
    public char getGroupingSeparator() {
        return mPreferencesHelper.getGroupingSeparator();
    }

    @Override
    public String getThemePreference() {
        return mPreferencesHelper.getThemePreference();
    }


    @StringRes
    private int toStringResId(String name) {
        return mContext.getResources().getIdentifier(name, "string", mContext.getPackageName());
    }

    @DrawableRes
    private int toDrawableResId(String name) {
        return mContext.getResources().getIdentifier(name, "drawable", mContext.getPackageName());
    }
}
