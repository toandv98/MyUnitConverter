package vn.com.toandv98.unitconverter.data.local;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class PreferencesHelper implements IPreferencesHelper {

    private static final String KEY_DECIMAL_PLACES = "decimal_places";
    private static final String KEY_DECIMAL_SEPARATOR = "decimal_separator";
    private static final String KEY_GROUP_SEPARATOR = "group_separator";
    private SharedPreferences preferences;

    public PreferencesHelper(Context context) {
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Override
    public int getDecimalPlaces() {
        return Integer.parseInt(preferences.getString(KEY_DECIMAL_PLACES, "3"));
    }

    @Override
    public char getDecimalSeparator() {
        return preferences.getString(KEY_DECIMAL_SEPARATOR, ".").charAt(0);
    }

    @Override
    public char getGroupingSeparator() {
        String s = preferences.getString(KEY_GROUP_SEPARATOR, ".");
        return s.equals("space") ? ' ' : s.charAt(0);
    }
}
