package vn.com.toandv98.unitconverter.ui.customs;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import vn.com.toandv98.unitconverter.R;

public class SwipeItemTouchHelper extends ItemTouchHelper.SimpleCallback {

    private CustomContract.OnSwipeListener mListener;
    private Drawable mIconDelete, mIconEdit;
    private final ColorDrawable mBgEdit, mBgDelete;

    public SwipeItemTouchHelper(Context mContext, CustomContract.OnSwipeListener mListener) {
        super(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
        this.mListener = mListener;
        mIconDelete = mContext.getDrawable(R.drawable.ic_delete_white);
        mIconEdit = mContext.getDrawable(R.drawable.ic_edit_white);
        mBgEdit = new ColorDrawable(mContext.getResources().getColor(R.color.bg_edit_item));
        mBgDelete = new ColorDrawable(mContext.getResources().getColor(R.color.bg_delete_item));
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
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);

        View itemView = viewHolder.itemView;
        int backgroundCornerOffset = 20;

        int iconMargin = (itemView.getHeight() - mIconDelete.getIntrinsicHeight()) / 2;
        int iconTop = itemView.getTop() + (itemView.getHeight() - mIconDelete.getIntrinsicHeight()) / 2;
        int iconBottom = iconTop + mIconDelete.getIntrinsicHeight();

        if (dX > 0) {
            int iconRight = itemView.getLeft() + iconMargin + mIconEdit.getIntrinsicWidth();
            int iconLeft = itemView.getLeft() + iconMargin;
            mIconEdit.setBounds(iconLeft, iconTop, iconRight, iconBottom);

            mBgEdit.setBounds(itemView.getLeft(), itemView.getTop(),
                    itemView.getLeft() + ((int) dX) + backgroundCornerOffset,
                    itemView.getBottom());
            mBgEdit.draw(c);
            mIconEdit.draw(c);
        } else if (dX < 0) {
            int iconLeft = itemView.getRight() - iconMargin - mIconDelete.getIntrinsicWidth();
            int iconRight = itemView.getRight() - iconMargin;
            mIconDelete.setBounds(iconLeft, iconTop, iconRight, iconBottom);

            mBgDelete.setBounds(itemView.getRight() + ((int) dX) - backgroundCornerOffset,
                    itemView.getTop(), itemView.getRight(), itemView.getBottom());
            mBgDelete.draw(c);
            mIconDelete.draw(c);
        } else {
            mBgEdit.setBounds(0, 0, 0, 0);
            mBgDelete.setBounds(0, 0, 0, 0);
            mBgEdit.draw(c);
            mIconEdit.draw(c);
            mBgDelete.draw(c);
            mIconDelete.draw(c);
        }
    }
}