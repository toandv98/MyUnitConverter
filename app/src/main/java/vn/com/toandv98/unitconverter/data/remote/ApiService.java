package vn.com.toandv98.unitconverter.data.remote;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import vn.com.toandv98.unitconverter.data.entities.LastRates;

public interface ApiService {
    @GET("/latest")
    Call<LastRates> getLastRates(@QueryMap Map<String, String> params);
}
