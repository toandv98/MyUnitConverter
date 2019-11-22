package vn.com.toandv98.unitconverter.ui.converters;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import vn.com.toandv98.unitconverter.R;
import vn.com.toandv98.unitconverter.data.DataManager;
import vn.com.toandv98.unitconverter.data.entities.Unit;
import vn.com.toandv98.unitconverter.ui.base.BaseActivity;
import vn.com.toandv98.unitconverter.utils.StateUtils;

import static vn.com.toandv98.unitconverter.utils.Constrants.EXTRA_NAME_ID;
import static vn.com.toandv98.unitconverter.utils.Constrants.INPUT_UNIT;
import static vn.com.toandv98.unitconverter.utils.Constrants.RESULT_UNIT;

public class ConvertersActivity extends BaseActivity<ConvertersContract.Presenter>
        implements ConvertersContract.View, TextWatcher, View.OnClickListener {

    private Toolbar toolbar;
    private ActionBar actionBar;
    private FloatingActionButton fabSwap;
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
    }

    @Override
    protected void setupView(Bundle savedInstanceState) {
        edtResultValue.setSaveEnabled(false);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        presenter.onReceiveId(getIntent().getIntExtra(EXTRA_NAME_ID, 0));
    }

    @Override
    protected void initListener() {
        edtInputValue.addTextChangedListener(this);
        fabSwap.setOnClickListener(this);
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

    //region Handle listener events
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

    @Override
    public void onClick(View v) {
        presenter.onSwapButtonClick();
    }
    //endregion
}
