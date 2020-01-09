package vn.com.toandv98.unitconverter.data.local;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class PreferencesHelper implements IPreferencesHelper {

    private static final String KEY_DECIMAL_PLACES = "decimal_places";
    private static final String KEY_DECIMAL_SEPARATOR = "decimal_separator";
    private static final String KEY_GROUP_SEPARATOR = "group_separator";
    private static final String KEY_PREFERENCE_THEME = "preference_theme";
    private SharedPreferences mPreferences;

    public PreferencesHelper(Context context) {
        mPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Override
    public int getDecimalPlaces() {
        return Integer.parseInt(mPreferences.getString(KEY_DECIMAL_PLACES, "3"));
    }

    @Override
    public char getDecimalSeparator() {
        return mPreferences.getString(KEY_DECIMAL_SEPARATOR, ".").charAt(0);
    }

    @Override
    public char getGroupingSeparator() {
        String s = mPreferences.getString(KEY_GROUP_SEPARATOR, ".");
        return s.equals("space") ? ' ' : s.charAt(0);
    }

    @Override
    public String getThemePreference() {
        return mPreferences.getString(KEY_PREFERENCE_THEME, "blue");
    }
}
