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
import android.view.Gravity;
import android.widget.LinearLayout;

public class SimpleProgressBar extends LinearLayout {
    private static final String TAG = "SimpleProgressBar";
    private static final int SCALE = 100;

    private Context mContext;
    private LinearLayout mView;
    private LinearLayout mProgressView;
    private LinearLayout.LayoutParams mProgressLP;
    private float fProgress = 0;
    private int nForegroundColor = Color.BLUE;
    private int nBackgroundColor = Color.GRAY;
    private int nCorner = 0;

    public SimpleProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs){
        mContext = context;
        TypedArray styledAttrs = context.obtainStyledAttributes(attrs,
                R.styleable.SimpleProgressBar);
        fProgress = styledAttrs.getFloat(R.styleable.SimpleProgressBar_spb_progress, fProgress);
        nForegroundColor = styledAttrs.getColor(R.styleable.SimpleProgressBar_spb_foreground_color, nForegroundColor);
        nBackgroundColor = styledAttrs.getColor(R.styleable.SimpleProgressBar_spb_background_color, nBackgroundColor);
        nCorner = (int) styledAttrs.getDimension(R.styleable.SimpleProgressBar_spb_corner, nCorner);
        styledAttrs.recycle();

        mView = new LinearLayout(context);
        mView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        mView.setOrientation(LinearLayout.HORIZONTAL);
        mView.setWeightSum(SCALE);
        mView.setGravity(Gravity.LEFT|Gravity.CENTER_VERTICAL);
        mView.setBackgroundColor(nBackgroundColor);

        mProgressView = new LinearLayout(context);
        mProgressLP = new LinearLayout.LayoutParams(0, LayoutParams.MATCH_PARENT);
        mProgressView.setLayoutParams(mProgressLP);
        mProgressView.setOrientation(LinearLayout.HORIZONTAL);
        mProgressView.setBackgroundColor(nForegroundColor);
        mView.addView(mProgressView);
        addView(mView);

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
