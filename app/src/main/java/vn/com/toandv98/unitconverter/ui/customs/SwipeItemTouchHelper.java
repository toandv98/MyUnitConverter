package vn.com.toandv98.unitconverter.ui.customs;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import static androidx.recyclerview.widget.ItemTouchHelper.ACTION_STATE_SWIPE;

public class SwipeItemTouchHelper extends ItemTouchHelper.SimpleCallback {

    private CustomContract.OnSwipeListener mListener;
    private boolean mSwipeBack = false;
    private boolean mSwipeLeft = false;

    public SwipeItemTouchHelper(CustomContract.OnSwipeListener mListener) {
        super(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
        this.mListener = mListener;
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        int position = viewHolder.getAdapterPosition();

        if (direction == ItemTouchHelper.LEFT) {
            mListener.onSwipeLeft(position);
        } else if (direction == ItemTouchHelper.RIGHT) {
            mListener.onSwipeRight(position);
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {

        mSwipeLeft = dX > 0;

        if (actionState == ACTION_STATE_SWIPE) {
            recyclerView.setOnTouchListener((v, event) -> {
                mSwipeBack = event.getAction() == MotionEvent.ACTION_UP || event.getAction() == MotionEvent.ACTION_CANCEL;
                if (mSwipeBack && mSwipeLeft) {
                    mListener.onSwipeRight(viewHolder.getAdapterPosition());
                }
                return false;
            });
        }

        super.onChildDraw(c, recyclerView, viewHolder, mSwipeBack ? dX : dX / 2, dY, actionState, isCurrentlyActive);
    }

    @Override
    public int convertToAbsoluteDirection(int flags, int layoutDirection) {
        if (mSwipeBack && mSwipeLeft) {
            mSwipeBack = false;
            return 0;
        } else {
            return super.convertToAbsoluteDirection(flags, layoutDirection);
        }
    }
}