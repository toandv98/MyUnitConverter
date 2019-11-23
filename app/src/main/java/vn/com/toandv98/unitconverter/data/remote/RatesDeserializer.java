package vn.com.toandv98.unitconverter.data.remote;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.Map;
import java.util.TreeMap;

import vn.com.toandv98.unitconverter.data.entities.LastRates;

public class RatesDeserializer implements JsonDeserializer<LastRates> {

    private static final String KEY_DATE = "date";
    private static final String KEY_SUCCESS = "success";
    private static final String KEY_TIMESTAMP = "timestamp";
    private static final String KEY_BASE = "base";
    private static final String KEY_RATES = "rates";

    @Override
    public LastRates deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        final JsonObject jsonObject = json.getAsJsonObject();

        final String date = jsonObject.get(KEY_DATE).getAsString();
        final boolean success = jsonObject.get(KEY_SUCCESS).getAsBoolean();
        final int timestamp = jsonObject.get(KEY_TIMESTAMP).getAsInt();
        final String base = jsonObject.get(KEY_BASE).getAsString();

        final Map<String, Double> rates = readParametersMap(jsonObject);

        LastRates result = new LastRates();
        result.setBase(base);
        result.setDate(date);
        result.setSuccess(success);
        result.setTimestamp(timestamp);
        result.setRates(rates);
        return result;
    }

    @Nullable
    private Map<String, Double> readParametersMap(@NonNull final JsonObject jsonObject) {
        final JsonElement paramsElement = jsonObject.get(KEY_RATES);
        if (paramsElement == null) {
            return null;
        }

        final JsonObject parametersObject = paramsElement.getAsJsonObject();
        final Map<String, Double> rates = new TreeMap<>();
        for (Map.Entry<String, JsonElement> entry : parametersObject.entrySet()) {
            String key = entry.getKey();
            Double value = entry.getValue().getAsDouble();
            rates.put(key, value);
        }
        return rates;
    }
}
