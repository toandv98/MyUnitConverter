package vn.com.toandv98.unitconverter.ui.main;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import vn.com.toandv98.unitconverter.R;
import vn.com.toandv98.unitconverter.data.DataManager;
import vn.com.toandv98.unitconverter.ui.base.BaseActivity;
import vn.com.toandv98.unitconverter.ui.conversion.ConversionFragment;
import vn.com.toandv98.unitconverter.ui.custom.CustomFragment;
import vn.com.toandv98.unitconverter.ui.setting.SettingFragment;
import vn.com.toandv98.unitconverter.ui.tool.ToolsFragment;

public class MainActivity extends BaseActivity<MainContract.Presenter>
        implements MainContract.View, BottomNavigationView.OnNavigationItemSelectedListener {

    private FragmentManager fragmentManager;
    private BottomNavigationView navigationView;

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected MainContract.Presenter initPresenter() {
        return new MainPresenter(this, new DataManager(this));
    }

    @Override
    protected void initView() {
        navigationView = findViewById(R.id.bottom_navigation);
    }

    @Override
    protected void setupView(Bundle savedInstanceState) {
        fragmentManager = getSupportFragmentManager();
        if (savedInstanceState == null) {
            fragmentManager.beginTransaction().add(R.id.fl_home, new ConversionFragment()).commit();
        }
    }

    @Override
    protected void initListener() {
        navigationView.setOnNavigationItemSelectedListener(this);
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
            fragmentManager.beginTransaction().replace(R.id.fl_home, fragment).commit();
            return true;
        }
        return false;
    }
}
