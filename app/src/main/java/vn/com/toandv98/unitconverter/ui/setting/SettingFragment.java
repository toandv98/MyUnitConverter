package vn.com.toandv98.unitconverter.ui.setting;

import android.os.Bundle;

import androidx.preference.PreferenceFragmentCompat;

import vn.com.toandv98.unitconverter.R;

public class SettingFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.preferences);
    }
}
