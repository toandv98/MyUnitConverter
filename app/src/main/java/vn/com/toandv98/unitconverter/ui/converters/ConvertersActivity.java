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
import vn.com.toandv98.unitconverter.data.entities.Unit;
import vn.com.toandv98.unitconverter.ui.base.BaseActivity;
import vn.com.toandv98.unitconverter.ui.unitsearch.UnitSearchFragment;
import vn.com.toandv98.unitconverter.utils.StateUtils;

import static vn.com.toandv98.unitconverter.utils.Constrants.ACTION_UPDATE_RATES;
import static vn.com.toandv98.unitconverter.utils.Constrants.EXTRA_NAME_CONVERSION_ID;
import static vn.com.toandv98.unitconverter.utils.Constrants.EXTRA_NAME_RESULT_MSG;
import static vn.com.toandv98.unitconverter.utils.Constrants.FRAG_UNIT_SEARCH_NAME;
import static vn.com.toandv98.unitconverter.utils.Constrants.INPUT_UNIT;
import static vn.com.toandv98.unitconverter.utils.Constrants.RESULT_UNIT;

public class ConvertersActivity extends BaseActivity<ConvertersContract.Presenter>
        implements ConvertersContract.View {

    private Toolbar toolbar;
    private ActionBar actionBar;
    private FloatingActionButton fabSwap, fabInputUnit, fabResultUnit;
    private EditText edtInputValue, edtResultValue;
    private TextView tvInputUnit, tvResultUnit;
    private RecyclerView rvFromUnit, rvToUnit;
    private UnitsAdapter adapterInputUnit, adapterResultUnit;

    //region onCreate()
    @Override
    protected int getLayout() {
        return R.layout.activity_converters;
    }

    @Override
    protected ConvertersContract.Presenter initPresenter() {
        return new ConvertersPresenter(this, new DataManager(this));
    }

    @Override
    protected void initView() {
        toolbar = findViewById(R.id.toolbar_converters);
        edtInputValue = findViewById(R.id.edt_converters_input);
        edtResultValue = findViewById(R.id.edt_converters_result);
        tvInputUnit = findViewById(R.id.tv_input_unit);
        tvResultUnit = findViewById(R.id.tv_result_unit);
        rvFromUnit = findViewById(R.id.rv_unit_from);
        rvToUnit = findViewById(R.id.rv_unit_to);
        fabSwap = findViewById(R.id.fab_converters_swap);
        fabInputUnit = findViewById(R.id.fab_converters_input_unit);
        fabResultUnit = findViewById(R.id.fab_converters_result_unit);
    }

    @Override
    protected void setupView(Bundle savedInstanceState) {
        edtResultValue.setSaveEnabled(false);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        presenter.onReceivedConversionId(getIntent().getIntExtra(EXTRA_NAME_CONVERSION_ID, 0));
    }

    @Override
    protected void initListener() {

        fabSwap.setOnClickListener(v -> presenter.onSwapButtonClick());
        fabInputUnit.setOnClickListener(v -> presenter.onFabInputUnitClick());
        fabResultUnit.setOnClickListener(v -> presenter.onFabResultUnitClick());

        edtInputValue.addTextChangedListener(new TextWatcher() {
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
        });

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
    public void setTitle(int resId) {
        if (actionBar != null) {
            actionBar.setTitle(resId);
        }
    }

    @Override
    public void focusInput() {
        edtInputValue.requestFocus();
    }

    @Override
    public void swapConversion() {
        int lastPos = adapterInputUnit.getLastCheckedPosition();
        adapterInputUnit.updateRadio(adapterResultUnit.getLastCheckedPosition());
        adapterResultUnit.updateRadio(lastPos);
        tvInputUnit.setText(StateUtils.getInputUnit().getLabelRes());
        tvResultUnit.setText(StateUtils.getResultUnit().getLabelRes());
    }

    @Override
    public void updateInputUnit(int labelRes) {
        tvInputUnit.setText(labelRes);
    }

    @Override
    public void updateResultUnit(int labelRes) {
        tvResultUnit.setText(labelRes);
    }

    @Override
    public void loadUnit(List<Unit> units) {
        adapterInputUnit = new UnitsAdapter(this, presenter, units, INPUT_UNIT);
        adapterResultUnit = new UnitsAdapter(this, presenter, units, RESULT_UNIT);

        RecyclerView.LayoutManager layoutInputUnit, layoutResultUnit;
        layoutInputUnit = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        layoutResultUnit = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);

        rvFromUnit.setLayoutManager(layoutInputUnit);
        rvFromUnit.setAdapter(adapterInputUnit);

        rvToUnit.setLayoutManager(layoutResultUnit);
        rvToUnit.setAdapter(adapterResultUnit);
    }

    @Override
    public void clearInputValue() {
        edtInputValue.getText().clear();
    }

    @Override
    public void updateResultValue(String result) {
        edtResultValue.setText(result);
    }

    @Override
    public void navigateToUnitSearch(int typeUnit) {
        Fragment fragment = UnitSearchFragment.newInstance(typeUnit);

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
}
