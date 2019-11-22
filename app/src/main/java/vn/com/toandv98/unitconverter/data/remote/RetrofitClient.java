package vn.com.toandv98.unitconverter.data.remote;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import vn.com.toandv98.unitconverter.data.entities.LastRates;

import static vn.com.toandv98.unitconverter.utils.Constrants.API_URL;

public class RetrofitClient {
    private static Retrofit retrofit = null;

    public static ApiService getApiService() {
        return RetrofitClient.getClient().create(ApiService.class);
    }

    private static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl(API_URL)
                    .addConverterFactory(createGsonConverter())
                    .build();
        }
        return retrofit;
    }

    private static Converter.Factory createGsonConverter() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LastRates.class, new RatesDeserializer());
        Gson gson = gsonBuilder.create();
        return GsonConverterFactory.create(gson);
    }
}
