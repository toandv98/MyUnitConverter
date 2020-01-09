package vn.com.toandv98.unitconverter.ui.tool.random;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Random;

import vn.com.toandv98.unitconverter.R;
import vn.com.toandv98.unitconverter.data.DataManager;
import vn.com.toandv98.unitconverter.ui.base.BaseFragment;

public class RandomFragment extends BaseFragment<RandomContract.Presenter> implements RandomContract.View {

    private FloatingActionButton mFabBack, mFabRandom, mFabClear;
    private EditText mEdtFrom, mEdtTo;
    private TextView mTvResult;
    private CardView mCvResult;

    @Override
    protected int getLayout() {
        return R.layout.fragment_random_number;
    }

    @Override
    protected RandomContract.Presenter initPresenter() {
        return new RandomPresenter(this, new DataManager(getBaseActivity()));
    }

    @Override
    protected void initView(View view) {
        mEdtFrom = view.findViewById(R.id.edt_random_from);
        mEdtTo = view.findViewById(R.id.edt_random_to);
        mTvResult = view.findViewById(R.id.tv_random_result);
        mFabBack = view.findViewById(R.id.fab_random_back);
        mFabClear = view.findViewById(R.id.fab_random_clear);
        mFabRandom = view.findViewById(R.id.fab_start_random);
        mCvResult = view.findViewById(R.id.cv_random_result);
    }

    @Override
    protected void setupView(Bundle savedInstanceState) {

    }

    @Override
    protected void initListener() {
        mFabRandom.setOnClickListener(v -> presenter.onFabRandomClick(mEdtFrom.getText().toString(), mEdtTo.getText().toString()));
        mFabBack.setOnClickListener(v -> presenter.onFabBackClick());
        mFabClear.setOnClickListener(v -> presenter.onFabClearClick());
    }

    @Override
    public void navigateBack() {
        getBaseActivity().onBackPressed();
    }

    @Override
    public void showResult(String result) {
        int[] androidColors = getResources().getIntArray(R.array.android_colors);
        int randomAndroidColor = androidColors[new Random().nextInt(androidColors.length)];
        mCvResult.setCardBackgroundColor(randomAndroidColor);
        mTvResult.setText(result);
    }

    @Override
    public void showErrorFrom() {
        mEdtFrom.requestFocus();
        showMessage(R.string.msg_cannot_empty);
    }

    @Override
    public void showErrorTo() {
        mEdtTo.requestFocus();
        showMessage(R.string.msg_cannot_empty);
    }

    @Override
    public void clearAll() {
        mEdtFrom.setText(R.string.defaut_random_from);
        mEdtTo.setText(R.string.default_ramdom_to);
        mTvResult.setText(R.string.default_random_result);
    }
}
