package vn.com.toandv98.unitconverter.ui.unitsearch;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import vn.com.toandv98.unitconverter.R;
import vn.com.toandv98.unitconverter.data.DataManager;
import vn.com.toandv98.unitconverter.ui.base.BaseFragment;

import static vn.com.toandv98.unitconverter.utils.Constrants.EXTRA_NAME_CONVERSION_ID;
import static vn.com.toandv98.unitconverter.utils.Constrants.EXTRA_NAME_TYPE_UNIT;

public class UnitSearchFragment extends BaseFragment<UnitSearchContract.Presenter> implements UnitSearchContract.View, SearchView.OnQueryTextListener {

    private Toolbar toolbar;
    private SearchView svUnit;
    private RecyclerView rvUnit;

    public static UnitSearchFragment newInstance(int conversionId, int typeUnit) {
        UnitSearchFragment unitSearchFragment = new UnitSearchFragment();
        Bundle args = new Bundle();
        args.putInt(EXTRA_NAME_CONVERSION_ID, conversionId);
        args.putInt(EXTRA_NAME_TYPE_UNIT, typeUnit);
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

        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {

        return false;
    }
}
