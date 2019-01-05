package com.lightricks.efraim.toolbar_back_navigation;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

/**
 * Displays dynamic number of arrows,configured by {@link ToolbarNavigationBackView#setNumOfArrows(int)} changes in the number of arrows are animated.
 *
 */
public class ToolbarNavigationBackView extends FrameLayout {
    private int numOfArrows = 0;
    private FrameLayout arrowsLayout;

    public ToolbarNavigationBackView(@NonNull Context context) {
        super(context);
        initView(null, context);
    }

    public ToolbarNavigationBackView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(attrs, context);
    }

    public ToolbarNavigationBackView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(attrs, context);
    }

    private void initView(@Nullable AttributeSet attrs, @NonNull Context context) {
        readAttrs(attrs, context);
        getLayoutInflater().inflate(R.layout.toolbar_navigation_back_layout, this, true);
    }

    private void readAttrs(@Nullable AttributeSet attrs, @NonNull Context context) {
        if (attrs == null) {
            return;
        }
        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.ToolbarNavigationBackView, 0, 0);
        numOfArrows = a.getInt(R.styleable.ToolbarNavigationBackView_numOfArrows, 0);
        a.recycle();
    }

    private LayoutInflater getLayoutInflater() {
        return (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getNumOfArrows() {
        return numOfArrows;
    }

    public void setNumOfArrows(int numOfArrows) {
        this.numOfArrows = numOfArrows;
        setArrows();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        arrowsLayout = findViewById(R.id.toolbar_navigation_back_arrow_container);
        arrowsLayout.getViewTreeObserver().
                addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                    @Override
                    public void onGlobalLayout() {
                        arrowsLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        setArrows();
                    }
                });

    }


    private void setArrows() {
        if (arrowsLayout == null || arrowsLayout.getHeight() <= 0) {
            return;
        }
        int arrowsToAdd = numOfArrows - arrowsLayout.getChildCount();
        if (arrowsToAdd == 0) {
            return;
        }
        if (arrowsToAdd < 0) {
            removeArrows(arrowsLayout, -arrowsToAdd);
        } else {
            addArrows(arrowsToAdd);
        }
        positionArrows();
    }

    private void removeArrows(FrameLayout arrowsLayout, int arrowsToRemove) {
        int startIndexForRemoval = arrowsLayout.getChildCount() - arrowsToRemove;
        if (startIndexForRemoval >= arrowsLayout.getChildCount()) {
            return;
        }
        if (startIndexForRemoval <= 0) {
            arrowsLayout.removeAllViews();
            return;
        }
        arrowsLayout.removeViews(startIndexForRemoval, arrowsToRemove);
    }

    private void addArrows(int arrowsToAdd) {
        int arrowViewSize = getResources().getDimensionPixelSize(R.dimen.toolbar_navigate_back_arrow_height_and_width);
        LayoutParams layoutParams = new LayoutParams(arrowViewSize, arrowViewSize);
        for (int i = 0; i < arrowsToAdd; i++) {
            View arrowView = new View(getContext());
            arrowView.setLayoutParams(layoutParams);
            arrowView.setBackground(getContext().getDrawable(R.drawable.ic_back));
            arrowsLayout.addView(arrowView);
        }
    }

    private void positionArrows() {
        int arrowViewSize = getResources().getDimensionPixelSize(R.dimen.toolbar_navigate_back_arrow_height_and_width);
        int topOffset = (arrowsLayout.getHeight() - arrowViewSize) / 2;
        int spaceBetweenArrows = getResources().getDimensionPixelSize(R.dimen.toolbar_navigate_back_arrow_apace_between_arrows);
        int totalArrowsWidth = arrowViewSize + (numOfArrows - 1) * spaceBetweenArrows;
        int offsetLeft = (arrowsLayout.getWidth() - totalArrowsWidth) / 2;
        for (int i = 0; i < arrowsLayout.getChildCount(); i++) {
            View view = arrowsLayout.getChildAt(i);
            view.animate().x(offsetLeft + i * spaceBetweenArrows);
            view.setY(topOffset);
        }
    }
}
