package vn.com.toandv98.unitconverter.ui.main;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import vn.com.toandv98.unitconverter.R;
import vn.com.toandv98.unitconverter.data.DataManager;
import vn.com.toandv98.unitconverter.data.IDataManager;
import vn.com.toandv98.unitconverter.ui.base.BaseActivity;
import vn.com.toandv98.unitconverter.ui.conversion.ConversionFragment;
import vn.com.toandv98.unitconverter.ui.customs.CustomFragment;
import vn.com.toandv98.unitconverter.ui.setting.SettingFragment;
import vn.com.toandv98.unitconverter.ui.tool.ToolsFragment;

import static vn.com.toandv98.unitconverter.utils.Constrants.THEME_BLUE;
import static vn.com.toandv98.unitconverter.utils.Constrants.THEME_DARK;
import static vn.com.toandv98.unitconverter.utils.Constrants.THEME_GREEN;

public class MainActivity extends BaseActivity<MainContract.Presenter>
        implements MainContract.View, BottomNavigationView.OnNavigationItemSelectedListener {

    private FragmentManager mFragmentManager;
    private BottomNavigationView mNavigationView;
    private IDataManager mDataManager;

    @Override
    protected int getStyleRes() {
        mDataManager = new DataManager(this);
        switch (mDataManager.getThemePreference()) {
            case THEME_BLUE:
                return R.style.AppThemeBlue;
            case THEME_GREEN:
                return R.style.AppThemeGreen;
            case THEME_DARK:
                return R.style.AppThemeDark;
            default:
                return 0;
        }
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected MainContract.Presenter initPresenter() {
        return new MainPresenter(this, mDataManager);
    }

    @Override
    protected void initView() {
        mNavigationView = findViewById(R.id.bottom_navigation);
    }

    @Override
    protected void setupView(Bundle savedInstanceState) {
        mFragmentManager = getSupportFragmentManager();
        if (savedInstanceState == null) {
            mFragmentManager.beginTransaction().add(R.id.fl_home, new ConversionFragment()).commit();
        }
    }

    @Override
    protected void initListener() {
        mNavigationView.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment fragment = null;
        switch (menuItem.getItemId()) {
            case R.id.item_converter:
                fragment = new ConversionFragment();
                break;
            case R.id.item_tools:
                fragment = new ToolsFragment();
                break;
            case R.id.item_custom:
                fragment = new CustomFragment();
                break;
            case R.id.item_settings:
                fragment = new SettingFragment();
                break;
        }
        if (fragment != null) {
            clearBackStack();
            mFragmentManager.beginTransaction().replace(R.id.fl_home, fragment).commit();
            return true;
        }
        return false;
    }

    public void clearBackStack() {
        for (int i = 0; i < mFragmentManager.getBackStackEntryCount(); ++i) {
            mFragmentManager.popBackStack();
        }
    }
}
