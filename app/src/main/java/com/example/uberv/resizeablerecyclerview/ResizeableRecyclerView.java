package com.example.uberv.resizeablerecyclerview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class ResizeableRecyclerView extends RecyclerView {

    private int mTotalItemsInView;
    private int mFeaturedItemHeight = 1000;
    private int mDefaultItemHeight = 300;
    private int mMaxDistance = mFeaturedItemHeight;

    public ResizeableRecyclerView(Context context) {
        super(context);
        setup();
    }

    public ResizeableRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setup();
    }

    public ResizeableRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setup();
    }

    private void setup() {
        addOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (getLayoutManager() instanceof LinearLayoutManager) {
                    LinearLayoutManager layoutManager = (LinearLayoutManager) getLayoutManager();
                    if (layoutManager.getOrientation() == LinearLayoutManager.VERTICAL) {
                        mTotalItemsInView = layoutManager.getItemCount();
                        changeHeightAccordingToScroll();
                    }
                }
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }
        });
    }

    private void changeHeightAccordingToScroll() {
        for (int i = 0; i < mTotalItemsInView; i++) {
            View viewToBeResized = getChildAt(i);
            if (viewToBeResized != null) {
                float distance = getTopOfView(viewToBeResized);
                if (distance > mMaxDistance) {
                    viewToBeResized.getLayoutParams().height = mDefaultItemHeight;
                    viewToBeResized.requestLayout();
                } else {
                    viewToBeResized.getLayoutParams().height = (int) height(distance);
                    viewToBeResized.requestLayout();
                }
            }
        }
    }

    private float getTopOfView(View view) {
        return Math.abs(view.getTop());
    }

    /**
     * diffHeight = featuredItemHeight - defaultItemHeight;
     */
    private float height(float distance) {
        int diffHeight = mFeaturedItemHeight - mDefaultItemHeight;
        return mFeaturedItemHeight - ((distance * (diffHeight)) / mMaxDistance);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }
}
