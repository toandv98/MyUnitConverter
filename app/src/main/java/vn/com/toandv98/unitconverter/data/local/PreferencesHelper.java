package vn.com.toandv98.unitconverter.data.local;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.Map;
import java.util.TreeMap;

import vn.com.toandv98.unitconverter.R;

import static android.content.Context.MODE_PRIVATE;

public class PreferencesHelper {

    private static final String SP_NAME_LOCAL = "local_data";
    private static final String SP_KEY_HASH_MAP = "TreeMap";

    public static void insertToSP(Context context, Map<String, Double> jsonMap) {
        String jsonString = new Gson().toJson(jsonMap);
        context.getSharedPreferences(SP_NAME_LOCAL, MODE_PRIVATE)
                .edit()
                .putString(SP_KEY_HASH_MAP, jsonString)
                .apply();
    }

    public static Map<String, Double> readFromSP(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SP_NAME_LOCAL, MODE_PRIVATE);
        String defValue = new Gson().toJson(new TreeMap<String, Double>());
        if (!sharedPreferences.contains(SP_KEY_HASH_MAP)) {
            sharedPreferences.edit()
                    .putString(SP_KEY_HASH_MAP, context.getString(R.string.default_rates))
                    .apply();
        }
        String json = sharedPreferences.getString(SP_KEY_HASH_MAP, defValue);
        TypeToken<Map<String, Double>> token = new TypeToken<Map<String, Double>>() {
        };
        return new Gson().fromJson(json, token.getType());
    }
}
