package vn.com.toandv98.unitconverter.ui.conversion;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import vn.com.toandv98.unitconverter.R;
import vn.com.toandv98.unitconverter.data.DataManager;
import vn.com.toandv98.unitconverter.ui.base.BaseFragment;
import vn.com.toandv98.unitconverter.ui.converters.ConvertersActivity;
import vn.com.toandv98.unitconverter.utils.ConversionUtils;

import static vn.com.toandv98.unitconverter.utils.Constrants.EXTRA_NAME_CONVERSION_ID;

public class ConversionFragment extends BaseFragment<ConversionContract.Presenter>
        implements ConversionContract.View, SearchView.OnQueryTextListener {

    private SearchView svConversion;
    private RecyclerView rvConversion;
    private ConversionAdapter adapter;

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
        rvConversion = view.findViewById(R.id.rv_conversion);
        svConversion = view.findViewById(R.id.sv_conversion);
    }

    @Override
    protected void setupView(Bundle savedInstanceState) {
        adapter = new ConversionAdapter(getBaseActivity(), presenter,
                ConversionUtils.getInstance().getConversionItems());
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getBaseActivity(), 2);
        rvConversion.setLayoutManager(layoutManager);
        rvConversion.setAdapter(adapter);
    }

    @Override
    protected void initListener() {
        svConversion.setOnQueryTextListener(this);
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
    public void navigateToConverters(int id) {
        Intent intent = new Intent(getBaseActivity(), ConvertersActivity.class);
        intent.putExtra(EXTRA_NAME_CONVERSION_ID, id);
        startActivity(intent);
    }
}
