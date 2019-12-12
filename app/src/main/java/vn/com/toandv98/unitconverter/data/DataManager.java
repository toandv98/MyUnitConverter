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
import vn.com.toandv98.unitconverter.data.entities.LastRates;
import vn.com.toandv98.unitconverter.data.entities.Unit;
import vn.com.toandv98.unitconverter.data.entities.UnitRoom;
import vn.com.toandv98.unitconverter.data.local.ConversionDao;
import vn.com.toandv98.unitconverter.data.local.ConversionDataBase;
import vn.com.toandv98.unitconverter.data.remote.RetrofitClient;
import vn.com.toandv98.unitconverter.services.UpdateCurrencyService;

import static vn.com.toandv98.unitconverter.utils.Constrants.CURRENCY;
import static vn.com.toandv98.unitconverter.utils.Constrants.FIXED_IO_API_KEY;
import static vn.com.toandv98.unitconverter.utils.Constrants.MAP_QUERY_KEY;

public class DataManager implements IDataManager {

    private Context context;
    private ConversionDao conversionDao;

    public DataManager(Context context) {
        this.context = context;
        conversionDao = ConversionDataBase.getINSTANCE(context).conversionDao();
    }

    @Override
    public void updateFromRemote() {
        UpdateCurrencyService.enqueueWork(context, new Intent());
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
                    conversionDao.updateLocalRates(lastRates);
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

        for (ConversionRoom item : conversionDao.getConversions()) {
            results.add(new Conversion(item.getId(),
                    toDrawableResId(item.getImageRes()), toStringResId(item.getTitleRes())));
        }

        return results;
    }

    @Override
    public List<Unit> getUnitsByConversionId(int id) {
        List<Unit> results = new ArrayList<>();
        for (UnitRoom unitR : conversionDao.getUnitsByConversionId(id)) {
            results.add(new Unit(unitR.getId(), toStringResId(unitR.getLabelRes()),
                    toDrawableResId(unitR.getDrawableRes()), unitR.getToBase(), unitR.getFromBase()));
        }
        return results;
    }

    @Override
    public Conversion getConversionById(int id) {
        ConversionRoom conversionR = conversionDao.getConversionById(id);
        return new Conversion(conversionR.getId(),
                toDrawableResId(conversionR.getImageRes()), toStringResId(conversionR.getTitleRes()));
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
