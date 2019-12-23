package vn.com.toandv98.unitconverter.ui.conversion;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import vn.com.toandv98.unitconverter.R;
import vn.com.toandv98.unitconverter.data.DataManager;
import vn.com.toandv98.unitconverter.data.entities.Conversion;
import vn.com.toandv98.unitconverter.ui.base.BaseFragment;
import vn.com.toandv98.unitconverter.ui.converters.ConvertersActivity;

import static vn.com.toandv98.unitconverter.utils.Constrants.EXTRA_NAME_CONVERSION_ID;

public class ConversionFragment extends BaseFragment<ConversionContract.Presenter>
        implements ConversionContract.View, SearchView.OnQueryTextListener {

    private SearchView mSvConversion;
    private RecyclerView mRvConversion;
    private ConversionAdapter mAdapter;

    @Override
    protected int getLayout() {
        return R.layout.fragment_conversion;
    }

    @Override
    protected ConversionContract.Presenter initPresenter() {
        return new ConversionPresenter(this, new DataManager(getBaseActivity()));
    }

    @Override
    protected void initView(View view) {
        mRvConversion = view.findViewById(R.id.rv_conversion);
        mSvConversion = view.findViewById(R.id.sv_conversion);
    }

    @Override
    protected void setupView(Bundle savedInstanceState) {
        presenter.onSetupView();
    }

    @Override
    protected void initListener() {
        mSvConversion.setOnQueryTextListener(this);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        mAdapter.getFilter().filter(query);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        mAdapter.getFilter().filter(newText);
        return false;
    }

    @Override
    public void navigateToConverters(int id) {
        Intent intent = new Intent(getBaseActivity(), ConvertersActivity.class);
        intent.putExtra(EXTRA_NAME_CONVERSION_ID, id);
        startActivity(intent);
    }

    @Override
    public void loadRecyclerView(List<Conversion> items) {
        int spanCount = 2;
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            spanCount = 3;
        }
        mAdapter = new ConversionAdapter(getBaseActivity(), presenter, items);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getBaseActivity(), spanCount);
        mRvConversion.setLayoutManager(layoutManager);
        mRvConversion.setAdapter(mAdapter);
    }
}
