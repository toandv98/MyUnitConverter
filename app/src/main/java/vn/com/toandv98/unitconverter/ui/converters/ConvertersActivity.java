package vn.com.toandv98.unitconverter.ui.converters;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;
import java.util.Objects;

import vn.com.toandv98.unitconverter.R;
import vn.com.toandv98.unitconverter.data.DataManager;
import vn.com.toandv98.unitconverter.data.IDataManager;
import vn.com.toandv98.unitconverter.data.entities.Unit;
import vn.com.toandv98.unitconverter.ui.base.BaseActivity;
import vn.com.toandv98.unitconverter.ui.converters.unitsearch.UnitSearchContract;
import vn.com.toandv98.unitconverter.ui.converters.unitsearch.UnitSearchFragment;

import static vn.com.toandv98.unitconverter.utils.Constrants.ACTION_UPDATE_RATES;
import static vn.com.toandv98.unitconverter.utils.Constrants.EXTRA_NAME_CONVERSION_ID;
import static vn.com.toandv98.unitconverter.utils.Constrants.EXTRA_NAME_RESULT_MSG;
import static vn.com.toandv98.unitconverter.utils.Constrants.FRAG_UNIT_SEARCH_NAME;
import static vn.com.toandv98.unitconverter.utils.Constrants.INPUT_UNIT;
import static vn.com.toandv98.unitconverter.utils.Constrants.RESULT_UNIT;
import static vn.com.toandv98.unitconverter.utils.Constrants.THEME_BLUE;
import static vn.com.toandv98.unitconverter.utils.Constrants.THEME_DARK;
import static vn.com.toandv98.unitconverter.utils.Constrants.THEME_GREEN;

public class ConvertersActivity extends BaseActivity<ConvertersContract.Presenter>
        implements ConvertersContract.View, UnitSearchContract.OnFinishListener {

    private Toolbar mToolbar;
    private ActionBar mActionBar;
    private FloatingActionButton mFabSwap, mFabInputUnit, mFabResultUnit;
    private EditText mEdtInputValue, mEdtResultValue;
    private TextView mTvInputUnit, mTvResultUnit;
    private RecyclerView mRvFromUnit, mRvToUnit;
    private UnitsAdapter mAdapterFrom, mAdapterTo;
    private IDataManager mDataManager;

    //region onCreate()
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
        return R.layout.activity_converters;
    }

    @Override
    protected ConvertersContract.Presenter initPresenter() {
        return new ConvertersPresenter(this, mDataManager);
    }

    @Override
    protected void initView() {
        mToolbar = findViewById(R.id.toolbar_converters);
        mEdtInputValue = findViewById(R.id.edt_converters_input);
        mEdtResultValue = findViewById(R.id.edt_converters_result);
        mTvInputUnit = findViewById(R.id.tv_input_unit);
        mTvResultUnit = findViewById(R.id.tv_result_unit);
        mRvFromUnit = findViewById(R.id.rv_unit_from);
        mRvToUnit = findViewById(R.id.rv_unit_to);
        mFabSwap = findViewById(R.id.fab_converters_swap);
        mFabInputUnit = findViewById(R.id.fab_converters_input_unit);
        mFabResultUnit = findViewById(R.id.fab_converters_result_unit);
    }

    @Override
    protected void setupView(Bundle savedInstanceState) {
        mEdtResultValue.setSaveEnabled(false);
        setSupportActionBar(mToolbar);
        mActionBar = getSupportActionBar();
        if (mActionBar != null) {
            mActionBar.setDisplayHomeAsUpEnabled(true);
        }
        presenter.onReceivedConversionId(getIntent().getIntExtra(EXTRA_NAME_CONVERSION_ID, 0));
    }

    @Override
    protected void initListener() {

        mFabSwap.setOnClickListener(v -> presenter.onSwapButtonClick());
        mFabInputUnit.setOnClickListener(v -> presenter.onFabInputUnitClick());
        mFabResultUnit.setOnClickListener(v -> presenter.onFabResultUnitClick());
        mEdtInputValue.addTextChangedListener(watcher);
    }
    //endregion

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_converters, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.item_clear:
                presenter.onClearItemClick();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onAttachFragment(@NonNull Fragment fragment) {
        super.onAttachFragment(fragment);
        if (fragment instanceof UnitSearchFragment) {
            ((UnitSearchFragment) fragment).setOnFinishListener(this);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(this)
                .registerReceiver(localReceiver, new IntentFilter(ACTION_UPDATE_RATES));
    }

    @Override
    protected void onPause() {
        LocalBroadcastManager.getInstance(this)
                .unregisterReceiver(localReceiver);
        super.onPause();
    }

    @Override
    public void setTitle(int resId, String title) {
        if (mActionBar != null) {
            if (resId == 0) {
                mActionBar.setTitle(title);
            } else {
                mActionBar.setTitle(getString(resId) + title);
            }
        }
    }

    @Override
    public void focusInput() {
        mEdtInputValue.requestFocus();
    }

    @Override
    public void swapConversion(int inputLabelRes, int resultLabelRes, String inputLabel, String resultLabel) {
        int lastPos = mAdapterFrom.getLastCheckedPosition();
        mAdapterFrom.updateRadio(mAdapterTo.getLastCheckedPosition());
        mAdapterTo.updateRadio(lastPos);
        mTvInputUnit.setText(inputLabelRes);
        mTvInputUnit.append(inputLabel);
        mTvResultUnit.setText(resultLabelRes);
        mTvResultUnit.append(resultLabel);
    }

    @Override
    public void updateInputUnit(int labelRes, String label) {
        mTvInputUnit.setText(labelRes);
        mTvInputUnit.append(label);
    }

    @Override
    public void updateResultUnit(int labelRes, String label) {
        mTvResultUnit.setText(labelRes);
        mTvResultUnit.append(label);
    }

    @Override
    public void updateRadioInput(int position) {
        mAdapterFrom.updateRadio(position);
    }

    @Override
    public void updateRadioResult(int position) {
        mAdapterTo.updateRadio(position);
    }

    @Override
    public void loadUnit(List<Unit> units) {
        mAdapterFrom = new UnitsAdapter(this, presenter, units, INPUT_UNIT);
        mAdapterTo = new UnitsAdapter(this, presenter, units, RESULT_UNIT);

        RecyclerView.LayoutManager layoutInputUnit, layoutResultUnit;
        layoutInputUnit = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        layoutResultUnit = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);

        mRvFromUnit.setLayoutManager(layoutInputUnit);
        mRvFromUnit.setAdapter(mAdapterFrom);

        mRvToUnit.setLayoutManager(layoutResultUnit);
        mRvToUnit.setAdapter(mAdapterTo);
    }

    @Override
    public void clearInputValue() {
        mEdtInputValue.getText().clear();
    }

    @Override
    public void updateResultValue(String result) {
        mEdtResultValue.setText(result);
    }

    @Override
    public void navigateToUnitSearch(int resultCode, int conversionId) {
        Fragment fragment = UnitSearchFragment.newInstance(resultCode, conversionId);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fl_search_unit, fragment)
                .addToBackStack(FRAG_UNIT_SEARCH_NAME)
                .commit();
    }

    private BroadcastReceiver localReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (Objects.equals(intent.getAction(), ACTION_UPDATE_RATES)) {
                presenter.onReceiveUpdateRates(intent.getStringExtra(EXTRA_NAME_RESULT_MSG));
            }
        }
    };

    private TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            presenter.onInputValueChanged(s);
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    @Override
    public void onFinished(int resultCode, int position) {
        presenter.onFragmentSearchFinished(resultCode, position);
    }
}
