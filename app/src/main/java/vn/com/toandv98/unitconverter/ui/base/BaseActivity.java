package vn.com.toandv98.unitconverter.ui.base;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.annotation.StyleRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import vn.com.toandv98.unitconverter.R;

public abstract class BaseActivity<P extends IBasePresenter> extends AppCompatActivity implements IBaseView {

    protected P presenter;

    @Override
    protected final void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(getStyleRes());
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        setContentView(getLayout());
        presenter = initPresenter();
        initView();
        setupView(savedInstanceState);
        initListener();
    }

    @Override
    protected final void onDestroy() {
        presenter.onViewDetached();
        super.onDestroy();
    }

    @StyleRes
    protected abstract int getStyleRes();

    @LayoutRes
    protected abstract int getLayout();

    protected abstract P initPresenter();

    protected abstract void initView();

    protected abstract void setupView(Bundle savedInstanceState);

    protected abstract void initListener();

    @Override
    public void showMessage(String msg) {
        if (msg != null) {
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, R.string.msg_null_message, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void showMessage(int resId) {
        showMessage(getString(resId));
    }
}
