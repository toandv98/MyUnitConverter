package vn.com.toandv98.unitconverter.ui.customs.editconversion;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import vn.com.toandv98.unitconverter.R;
import vn.com.toandv98.unitconverter.data.DataManager;
import vn.com.toandv98.unitconverter.data.entities.CustomUnit;
import vn.com.toandv98.unitconverter.ui.base.BaseActivity;

import static vn.com.toandv98.unitconverter.utils.Constrants.EXTRA_NAME_CONVERSION_ID;
import static vn.com.toandv98.unitconverter.utils.Constrants.UNIT_DIALOG_TAG;

public class EditConversionActivity extends BaseActivity<EditConversionContract.Presenter>
        implements EditConversionContract.View, AddUnitDialog.OnSaveUnitListener {
    private EditText mEdtConversionName;
    private RecyclerView mRvUnit;
    private FloatingActionButton mFabAddUnit;
    private AddedUnitsAdapter mAdapter;
    private TextView mTvNoUnit;
    private ConstraintLayout mContainerView;

    @Override
    protected int getLayout() {
        return R.layout.activity_add_convension;
    }

    @Override
    protected EditConversionContract.Presenter initPresenter() {
        return new EditConversionPresenter(this, new DataManager(this));
    }

    @Override
    protected void initView() {
        mEdtConversionName = findViewById(R.id.edt_add_conversion_name);
        mRvUnit = findViewById(R.id.rv_add_unit);
        mFabAddUnit = findViewById(R.id.fab_add_unit);
        mTvNoUnit = findViewById(R.id.tv_no_unit_found);
        mContainerView = findViewById(R.id.container_add_unit);
    }

    @Override
    protected void setupView(Bundle savedInstanceState) {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(getString(R.string.title_edit_conversion));
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        mEdtConversionName.requestFocus();
        presenter.onReceivedId(getIntent().getParcelableExtra(EXTRA_NAME_CONVERSION_ID));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_conversion, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                setResult(Activity.RESULT_CANCELED);
                finish();
                return true;
            case R.id.item_save_conversion:
                presenter.onMenuItemSaveClick(mEdtConversionName.getText().toString());
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void initListener() {
        mFabAddUnit.setOnClickListener(v -> presenter.onFabAddUnitClick());
    }

    @Override
    public void setConversionName(String label) {
        mEdtConversionName.setText(label);
    }

    @Override
    public void loadRecyclerView(List<CustomUnit> customUnits) {
        if (customUnits.isEmpty()) {
            mTvNoUnit.setVisibility(View.VISIBLE);
        }
        mAdapter = new AddedUnitsAdapter(this, customUnits, presenter);

        RecyclerView.LayoutManager layoutManager =
                new LinearLayoutManager(this, RecyclerView.VERTICAL, false);

        mRvUnit.setLayoutManager(layoutManager);
        mRvUnit.setAdapter(mAdapter);
    }

    @Override
    public void updateUnitItem(int position) {
        mAdapter.notifyItemChanged(position);
    }

    @Override
    public void addUnitItem(int position) {
        mTvNoUnit.setVisibility(View.GONE);
        mAdapter.notifyItemInserted(position);
    }

    @Override
    public void showSnackBar() {
        Snackbar snackbar = Snackbar.make(mContainerView, getString(R.string.msg_deleted_item), Snackbar.LENGTH_LONG);
        snackbar.setAction(getString(R.string.action_undo), v -> presenter.onUndoClick());
        snackbar.show();
    }

    @Override
    public void showError() {
        showMessage(R.string.msg_input_conversion_name);
        mEdtConversionName.requestFocus();
    }

    @Override
    public void finishOk(boolean isAdd) {
        Intent result = new Intent();
        result.putExtra("is_add", isAdd);
        setResult(RESULT_OK, result);
        finish();
    }

    @Override
    public void deleteUnitItem(int position) {
        mAdapter.notifyItemRemoved(position);
    }

    @Override
    public void showUnitDialog(CustomUnit unit, boolean isAdd) {
        AddUnitDialog unitDialog = AddUnitDialog.newInstance(unit, isAdd);
        unitDialog.show(getSupportFragmentManager(), UNIT_DIALOG_TAG);
        unitDialog.setOnSaveUnitListener(this);
    }

    @Override
    public void onAddUnit(CustomUnit unit) {
        presenter.onAddUnit(unit);
    }

    @Override
    public void onUpdateUnit(CustomUnit unit) {
        presenter.onUpdateUnit(unit);
    }

    @Override
    public void onInvalidInput() {
        showMessage(R.string.msg_fill_input);
    }
}
