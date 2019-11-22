package vn.com.toandv98.unitconverter.data.local;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;

public class PreferencesHelper {

    private static final String SP_NAME_LOCAL = "local_data";
    private static final String SP_KEY_HASH_MAP = "HashMap";

    public static void insertToSP(Context context, Map<String, Double> jsonMap) {
        String jsonString = new Gson().toJson(jsonMap);
        SharedPreferences sharedPreferences = context.getSharedPreferences(SP_NAME_LOCAL, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(SP_KEY_HASH_MAP, jsonString);
        editor.apply();
    }

    public static Map<String, Double> readFromSP(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SP_NAME_LOCAL, MODE_PRIVATE);
        String defValue = new Gson().toJson(new HashMap<String, Double>());
        String json = sharedPreferences.getString(SP_KEY_HASH_MAP, defValue);
        TypeToken<Map<String, Double>> token = new TypeToken<Map<String, Double>>() {
        };
        return new Gson().fromJson(json, token.getType());
    }
}
