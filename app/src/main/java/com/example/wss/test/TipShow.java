package com.example.wss.test;

import android.app.Activity;
import android.graphics.Point;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * Created by wss on 7/14/16.
 */
public class TipShow {
    private int[] location;
    private RelativeLayout container;
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

    public void addTip(Activity activity, View localView, View showView) {
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
        container = new RelativeLayout(activity);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        container.setLayoutParams(params);
        rootView.addView(container);
        container.removeAllViews();
        container.addView(showView);
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
//        ViewTreeObserver vto2 = showView.getViewTreeObserver();
//        vto2.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//            @Override
//            public void onGlobalLayout() {
//                showView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
//                showViewMeasure[0] = showView.getWidth();
//                showViewMeasure[1] = showView.getHeight();
//                Log.d("wang_show", ("\n\n" + showView.getHeight() + "," + showView.getWidth()));
//            }
//        });
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
//        ViewTreeObserver vto2 = localView.getViewTreeObserver();
//        vto2.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
//            @Override
//            public void onGlobalLayout() {
//                localView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
//                localViewMeasure[0] = localView.getWidth();
//                localViewMeasure[1] = localView.getHeight();
//                Log.d("wang_local", ("\n\n" + localView.getHeight() + "," + localView.getWidth()));
//            }
//        });
    }

    /**
     * 展示图片的位置
     *
     * @param activity
     * @param showView
     */
    private void showViewLocation(Activity activity, View showView) {
        RelativeLayout.LayoutParams viewParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
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
    }

//    public void setShowViewLocationLeft() {
//        setShowViewLocation(true, false, false, false, false);
//    }
//
//    public void setShowViewLocationTop() {
//        setShowViewLocation(false, true, false, false, false);
//    }
//
//    public void setShowViewLocationRight() {
//        setShowViewLocation(false, false, true, false, false);
//    }
//
//    public void setShowViewLocationBottom() {
//        setShowViewLocation(false, false, false, true, false);
//    }
//
//    public void setShowViewLocationLeftTop() {
//        setShowViewLocation(true, true, false, false, false);
//    }
//
//    public void setShowViewLocationTopMiddle() {
//        setShowViewLocation(false, true, false, false, true);
//    }
//
//    public void setShowViewLocationTopRight(){
//        setShowViewLocation(false, true, true, false, false);
//    }
//
//    public void setShowViewLocationRightMiddle(){
//        setShowViewLocation(false, false, true, false, true);
//    }
//
//    public void setShowViewLocationRightBottom(){
//        setShowViewLocation(false, false, true, true, false);
//    }
//
//    public void setShowViewLocationBottomMiddle(){
//        setShowViewLocation(false, false, false, true, true);
//    }
//
//    public void setShowViewLocationBottomLeft(){
//        setShowViewLocation(true, false, false, true, false);
//    }

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
