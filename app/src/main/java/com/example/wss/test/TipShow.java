package com.example.wss.test;

import android.app.Activity;
import android.graphics.Point;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

/**
 * Created by wss on 7/14/16.
 */
public class TipShow {
    private int[] location;
    private FrameLayout container;
    private int[] showViewMeasure;
    private int[] localViewMeasure;
    public boolean mainLeft = false;
    public boolean mainTop = false;
    public boolean mainRight = false;
    public boolean mainBottom = false;
    private boolean left = false;
    private boolean top = false;
    private boolean right = false;
    private boolean bottom = false;
    private boolean middle = false;
    private boolean isShow = false;
    private View showView = null;

    public void addTip(Activity activity, View localView, View showView) {
        if (isShow && null != this.showView && this.showView == showView)
            return;
        this.showView = showView;
        initLocalView(activity, showView);
        showViewMeasure(showView);
        localViewMeasure(localView);
        getLocalViewLocation(localView);
        showViewLocation(activity, showView);
    }

    /**
     * 初始化倚靠的图片,并把需要展示的图片画上去（没有确定好位置）
     *
     * @param activity
     */
    private void initLocalView(Activity activity, View showView) {
        ViewGroup rootView = (ViewGroup) activity.getWindow().getDecorView();
        container = new FrameLayout(activity);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        container.setLayoutParams(params);
        container.removeAllViews();
        container.addView(showView);
        rootView.addView(container);
    }

    /**
     * 获取展示图片的宽高
     *
     * @param showView
     */
    private void showViewMeasure(final View showView) {
        showViewMeasure = new int[2];
        showView.measure(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        showViewMeasure[0] = showView.getMeasuredWidth();
        showViewMeasure[1] = showView.getMeasuredHeight();
        Log.d("wang_show", ("\n\n" + showView.getMeasuredWidth() + "," + showView.getMeasuredHeight()));
    }

    /**
     * 倚靠图片的宽高
     *
     * @param localView
     */
    private void localViewMeasure(final View localView) {
        localViewMeasure = new int[2];
        localView.measure(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        localViewMeasure[0] = localView.getMeasuredWidth();
        localViewMeasure[1] = localView.getMeasuredHeight();
        Log.d("wang_local", ("\n\n" + localView.getMeasuredWidth() + "," + localView.getMeasuredHeight()));
    }

    /**
     * 展示图片的位置
     *
     * @param activity
     * @param showView
     */
    private void showViewLocation(Activity activity, View showView) {
        FrameLayout.LayoutParams viewParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        Display display = activity.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        viewParams.leftMargin = location[0];
        viewParams.topMargin = location[1];

        if (mainLeft)
            viewParams.leftMargin = location[0] - showViewMeasure[0];
        if (mainRight)
            viewParams.leftMargin = location[0] + localViewMeasure[0];
        if (mainTop)
            viewParams.topMargin = location[1] - showViewMeasure[1];
        if (mainBottom)
            viewParams.topMargin = location[1] + localViewMeasure[1];
        if (top && (mainLeft | mainRight)) {
            viewParams.topMargin = location[1] - showViewMeasure[1];
            if (middle)
                viewParams.topMargin = location[1] + localViewMeasure[1] / 2 - showViewMeasure[1];
        }
        if (bottom && (mainLeft | mainRight)) {
            viewParams.topMargin = location[1] + localViewMeasure[1];
            if (middle)
                viewParams.topMargin = location[0] + localViewMeasure[1] / 2;
        }
        if (left && (mainTop | mainBottom)) {
            viewParams.leftMargin = location[0] - showViewMeasure[0];
            if (middle)
                viewParams.leftMargin = location[0] + localViewMeasure[0] / 2 - showViewMeasure[0];
        }
        if (right && (mainTop | mainBottom)) {
            viewParams.leftMargin = location[0] + localViewMeasure[0];
            if (middle)
                viewParams.leftMargin = location[0] + localViewMeasure[0] / 2;
        }

        showView.setLayoutParams(viewParams);
        isShow = true;
    }

    public void setShowViewLocation(boolean left, boolean top, boolean right, boolean bottom, boolean middle) {
        this.left = left;
        this.top = top;
        this.right = right;
        this.bottom = bottom;
        this.middle = middle;
    }

    public void removeTip(View view) {
        container.removeView(view);
    }

    public void removeAllTips() {
        container.removeAllViews();
    }

    private void getLocalViewLocation(View view) {
        location = new int[2];
        view.getLocationInWindow(location);
    }
}
