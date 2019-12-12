package vn.com.toandv98.unitconverter.ui.unitsearch;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import vn.com.toandv98.unitconverter.R;
import vn.com.toandv98.unitconverter.data.DataManager;
import vn.com.toandv98.unitconverter.data.entities.Unit;
import vn.com.toandv98.unitconverter.ui.base.BaseFragment;

import static vn.com.toandv98.unitconverter.utils.Constrants.EXTRA_NAME_CONVERSION_ID;
import static vn.com.toandv98.unitconverter.utils.Constrants.EXTRA_NAME_TYPE_UNIT;

public class UnitSearchFragment extends BaseFragment<UnitSearchContract.Presenter>
        implements UnitSearchContract.View, SearchView.OnQueryTextListener {

    private Toolbar toolbar;
    private SearchView svUnit;
    private RecyclerView rvUnit;
    private UnitSearchAdapter adapter;
    private UnitSearchContract.OnFinishListener callback;

    public void setOnFinishListener(UnitSearchContract.OnFinishListener callback) {
        this.callback = callback;
    }

    public static UnitSearchFragment newInstance(int typeUnit, int conversionId) {
        UnitSearchFragment unitSearchFragment = new UnitSearchFragment();
        Bundle args = new Bundle();
        args.putInt(EXTRA_NAME_TYPE_UNIT, typeUnit);
        args.putInt(EXTRA_NAME_CONVERSION_ID, conversionId);
        unitSearchFragment.setArguments(args);
        return unitSearchFragment;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_unit_search;
    }

    @Override
    protected UnitSearchContract.Presenter initPresenter() {
        return new UnitSearchPresenter(this, new DataManager(getBaseActivity()));
    }

    @Override
    protected void initView(View view) {
        toolbar = view.findViewById(R.id.toolbar_unit_search);
        svUnit = view.findViewById(R.id.sv_unit_search);
        rvUnit = view.findViewById(R.id.rv_unit_search);
    }

    @Override
    protected void setupView(Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        getBaseActivity().getDelegate().setSupportActionBar(toolbar);
        ActionBar actionBar = getBaseActivity().getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        Bundle bundle = getArguments();
        if (bundle != null) {
            presenter.onReceivedBundle(bundle.getInt(EXTRA_NAME_TYPE_UNIT), bundle.getInt(EXTRA_NAME_CONVERSION_ID));
        }
    }

    @Override
    protected void initListener() {
        svUnit.setOnQueryTextListener(this);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        menu.clear();
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            getBaseActivity().onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        adapter.getFilter().filter(query);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        adapter.getFilter().filter(newText);
        return false;
    }

    @Override
    public void focusInput() {
        svUnit.requestFocus();
    }

    @Override
    public void finishFragment() {
        getBaseActivity().onBackPressed();
        callback.onFinished();
    }

    @Override
    public void loadUnit(List<Unit> units) {
        int spanCount = 2;
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            spanCount = 3;
        }
        adapter = new UnitSearchAdapter(getBaseActivity(), presenter, units);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getBaseActivity(), spanCount);
        rvUnit.setLayoutManager(layoutManager);
        rvUnit.setAdapter(adapter);
    }
}
