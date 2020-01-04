package vn.com.toandv98.unitconverter.ui.customs;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;

import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import vn.com.toandv98.unitconverter.R;
import vn.com.toandv98.unitconverter.data.DataManager;
import vn.com.toandv98.unitconverter.data.entities.CustomConversion;
import vn.com.toandv98.unitconverter.ui.base.BaseFragment;
import vn.com.toandv98.unitconverter.ui.customs.editconversion.EditConversionActivity;

import static vn.com.toandv98.unitconverter.utils.Constrants.ADD_CONVERSION_REQUEST_CODE;

public class CustomFragment extends BaseFragment<CustomContract.Presenter>
        implements CustomContract.View, SearchView.OnQueryTextListener, CustomContract.OnSwipeListener {

    private SearchView mSvCustom;
    private RecyclerView mRvCustom;
    private FloatingActionButton mFabAdd;
    private CustomAdapter mAdapter;
    private CoordinatorLayout mCoordinatorLayout;

    @Override
    protected int getLayout() {
        return R.layout.fragment_custom;
    }

    @Override
    protected CustomContract.Presenter initPresenter() {
        return new CustomPresenter(this, new DataManager(getBaseActivity()));
    }

    @Override
    protected void initView(View view) {
        mSvCustom = view.findViewById(R.id.sv_custom);
        mRvCustom = view.findViewById(R.id.rv_custom);
        mFabAdd = view.findViewById(R.id.fab_custom_add);
        mCoordinatorLayout = view.findViewById(R.id.container_custom_tab);
    }

    @Override
    protected void setupView(Bundle savedInstanceState) {
        presenter.onSetupView();
    }

    @Override
    protected void initListener() {
        mSvCustom.setOnQueryTextListener(this);
        mFabAdd.setOnClickListener(v -> presenter.onFabAddClick());
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
    public void loadRecyclerView(List<CustomConversion> data) {
        mAdapter = new CustomAdapter(getBaseActivity(), presenter, data);
        RecyclerView.LayoutManager layoutManager =
                new LinearLayoutManager(getBaseActivity(), RecyclerView.VERTICAL, false);
        mRvCustom.setLayoutManager(layoutManager);
        mRvCustom.setAdapter(mAdapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new SwipeItemTouchHelper(getBaseActivity(), this));
        itemTouchHelper.attachToRecyclerView(mRvCustom);
    }

    @Override
    public void addConversionToList(int position) {
        mAdapter.notifyItemInserted(position);
    }

    @Override
    public void updateConversionOnList(int position) {
        mAdapter.notifyItemChanged(position);
    }

    @Override
    public void removeConversionFromList(int position) {
        mAdapter.notifyItemRemoved(position);
    }

    @Override
    public void navigateToAddConversion() {
        Intent intent = new Intent(getBaseActivity(), EditConversionActivity.class);
        startActivityForResult(intent, ADD_CONVERSION_REQUEST_CODE);
    }

    @Override
    public void showSnackBar() {
        Snackbar snackbar = Snackbar.make(mCoordinatorLayout, getString(R.string.msg_deleted_item), Snackbar.LENGTH_SHORT);
        snackbar.setAction(getString(R.string.action_undo), v -> presenter.onUndoClick());
        snackbar.addCallback(new Snackbar.Callback() {
            @Override
            public void onDismissed(Snackbar transientBottomBar, int event) {
                super.onDismissed(transientBottomBar, event);
                presenter.onSnackBarDismissed();
            }
        });
        snackbar.show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_CONVERSION_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            presenter.onAddedConversions();
        }
    }

    @Override
    public void onSwipeLeft(int position) {
        presenter.onSwipeLeft(position);
    }

    @Override
    public void onSwipeRight(int position) {
        presenter.onSwipeRight(position);
    }
}
