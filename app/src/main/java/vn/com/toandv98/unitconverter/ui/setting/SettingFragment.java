package vn.com.toandv98.unitconverter.ui.setting;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import vn.com.toandv98.unitconverter.R;
import vn.com.toandv98.unitconverter.ui.main.MainActivity;

public class SettingFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.preferences);
        Activity activity = getActivity();
        Preference prefsAbout = getPreferenceScreen().findPreference("about_dialog");
        Preference prefsTheme = getPreferenceScreen().findPreference("preference_theme");
        if (activity instanceof MainActivity) {
            if (prefsAbout != null)
                prefsAbout.setOnPreferenceClickListener(preference -> {
                    (new AboutDialog()).show(((MainActivity) activity).getSupportFragmentManager(), "about_dialog");
                    return true;
                });
            if (prefsTheme != null)
                prefsTheme.setOnPreferenceChangeListener((preference, newValue) -> {
                    Intent intent = activity.getIntent();
                    activity.finish();
                    startActivity(intent);
                    return true;
                });
        }


    }
}
