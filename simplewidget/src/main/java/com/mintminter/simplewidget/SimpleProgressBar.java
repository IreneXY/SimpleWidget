package com.mintminter.simplewidget;

/*******************************************************************************
 * Copyright (c) 2016 Irene Yu
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 *******************************************************************************/

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class SimpleProgressBar extends LinearLayout {
    private static final String TAG = "SimpleProgressBar";
    private static final int SCALE = 100;

    private LinearLayout mProgressView;
    private LinearLayout.LayoutParams mProgressLP;
    private float fProgress = 0;
    private int nForegroundColor = Color.BLUE;
    private int nBackgroundColor = Color.GRAY;
    private int nShadowColor = Color.TRANSPARENT;
    private int nShadowBorderWidth = 0;
    private int nShadowCorner = 0;
    private int nCorner = 0;

    public SimpleProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs){
        TypedArray styledAttrs = context.obtainStyledAttributes(attrs,
                R.styleable.SimpleProgressBar);
        fProgress = styledAttrs.getFloat(R.styleable.SimpleProgressBar_spb_progress, fProgress);
        nForegroundColor = styledAttrs.getColor(R.styleable.SimpleProgressBar_spb_foreground_color, nForegroundColor);
        nBackgroundColor = styledAttrs.getColor(R.styleable.SimpleProgressBar_spb_background_color, nBackgroundColor);
        nShadowColor = styledAttrs.getColor(R.styleable.SimpleProgressBar_spb_shadow_color, nShadowColor);
        nShadowBorderWidth = (int) styledAttrs.getDimension(R.styleable.SimpleProgressBar_spb_shadow_border_width, nShadowBorderWidth);
        nShadowCorner = (int) styledAttrs.getDimension(R.styleable.SimpleProgressBar_spb_shadow_corner, nShadowCorner);
        nCorner = (int) styledAttrs.getDimension(R.styleable.SimpleProgressBar_spb_corner, nCorner);
        styledAttrs.recycle();


        LinearLayout mProgressBarContainer = new LinearLayout(context);
        mProgressBarContainer.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        mProgressBarContainer.setOrientation(LinearLayout.HORIZONTAL);
        mProgressBarContainer.setWeightSum(SCALE);
        mProgressBarContainer.setGravity(Gravity.LEFT|Gravity.CENTER_VERTICAL);
        mProgressBarContainer.setBackgroundDrawable(SimpleUtil.newRectangleDrawable(getContext(), nBackgroundColor, nCorner));

        mProgressView = new LinearLayout(context);
        mProgressLP = new LinearLayout.LayoutParams(0, LayoutParams.MATCH_PARENT);
        mProgressView.setLayoutParams(mProgressLP);
        mProgressView.setOrientation(LinearLayout.HORIZONTAL);
        mProgressView.setBackgroundDrawable(SimpleUtil.newRectangleDrawable(getContext(), nForegroundColor, nCorner));
        mProgressBarContainer.addView(mProgressView);

        RelativeLayout parentView = new RelativeLayout(context);
        RelativeLayout.LayoutParams parentLayoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        parentLayoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
        parentView.addView(mProgressBarContainer, parentLayoutParams);
        parentView.setPadding(nShadowBorderWidth, nShadowBorderWidth, nShadowBorderWidth, nShadowBorderWidth);
        parentView.setBackgroundDrawable(SimpleUtil.newRectangleDrawable(getContext(), nShadowColor, nShadowCorner));

        addView(parentView);
        setProgress(fProgress);
    }

    public void setProgress(float progress){
        fProgress = progress;
        mProgressLP.weight = (int) (fProgress*SCALE);
        mProgressView.setLayoutParams(mProgressLP);
        requestLayout();
    }

    public float getProgress(){
        return fProgress;
    }

}
