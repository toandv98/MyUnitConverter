package vn.com.toandv98.unitconverter.ui.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;

public abstract class BaseFragment<P extends IBasePresenter> extends Fragment implements IBaseView {

    protected P presenter;
    private BaseActivity mActivity;

    @Override
    public final void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof Activity) {
            mActivity = (BaseActivity) context;
        }
    }

    protected BaseActivity getBaseActivity() {
        return mActivity;
    }

    @Nullable
    @Override
    public final View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(getLayout(), container, false);

        presenter = initPresenter();
        initView(view);
        setupView(savedInstanceState);
        initListener();

        return view;
    }

    @LayoutRes
    protected abstract int getLayout();

    protected abstract P initPresenter();

    protected abstract void initView(View view);

    protected abstract void setupView(Bundle savedInstanceState);

    protected abstract void initListener();

    @Override
    public void showMessage(String msg) {
        getBaseActivity().showMessage(msg);
    }

    @Override
    public void showMessage(@StringRes int resId) {
        getBaseActivity().showMessage(resId);
    }

    @Override
    public void onDestroy() {
        presenter.onViewDetached();
        super.onDestroy();
    }

    @Override
    public final void onDetach() {
        mActivity = null;
        super.onDetach();
    }

}
