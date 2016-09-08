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
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class SimpleSeekBar extends RelativeLayout {

    private static final int SCALE = 100;

    private int nBackgroundHeight = 6;
    private int nForegroundHeight = 10;
    private int nBackgroundColor = Color.GRAY;
    private int nForegroundColor = Color.BLUE;
    private int nThumbColor = nForegroundColor;
    private float fProgress = 0.5f;

    private Context mContext;
    private LinearLayout mProgressView;
    private LinearLayout mThumbView;
    private LinearLayout.LayoutParams mProgressBlockLP;
    private LinearLayout.LayoutParams mRemainBlockLP;
    private LinearLayout.LayoutParams mThumbLP;

    private boolean mIsDragging = false;

    public SimpleSeekBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;

        TypedArray styledAttrs = context.obtainStyledAttributes(attrs,
                R.styleable.SimpleSeekBar);
        nForegroundHeight = (int) styledAttrs.getDimension(R.styleable.SimpleSeekBar_ssb_foreground_height,
                nForegroundHeight);
        nBackgroundHeight = (int) styledAttrs.getDimension(R.styleable.SimpleSeekBar_ssb_background_height, nBackgroundHeight);
        if(nForegroundHeight < nBackgroundHeight){
            nBackgroundHeight = nForegroundHeight-4>1?nForegroundHeight-4:1;
        }

        nBackgroundColor = styledAttrs.getColor(R.styleable.SimpleSeekBar_ssb_background_color, nBackgroundColor);
        nForegroundColor = styledAttrs.getColor(R.styleable.SimpleSeekBar_ssb_foreground_color, nForegroundColor);
        nThumbColor = styledAttrs.getColor(R.styleable.SimpleSeekBar_ssb_thumb_color, nThumbColor);
        fProgress = styledAttrs.getFloat(R.styleable.SimpleSeekBar_ssb_progress, fProgress);
        styledAttrs.recycle();

        mProgressView = new LinearLayout(context);
        mProgressView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        mProgressView.setOrientation(LinearLayout.HORIZONTAL);
        mProgressView.setWeightSum(SCALE);
        mProgressView.setGravity(Gravity.CENTER);
        LayoutParams relativeParamsForProgressView = new LayoutParams(
                LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        relativeParamsForProgressView.addRule(RelativeLayout.CENTER_IN_PARENT);
        addView(mProgressView, relativeParamsForProgressView);

        mThumbView = new LinearLayout(context);
        mThumbView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        mThumbView.setOrientation(LinearLayout.HORIZONTAL);
        mThumbView.setGravity(Gravity.CENTER);
        LayoutParams relativeParamsForThumbView = new LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        relativeParamsForThumbView.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        relativeParamsForThumbView.addRule(RelativeLayout.CENTER_VERTICAL);
        addView(mThumbView, relativeParamsForThumbView);

        setGravity(Gravity.CENTER);
        generate();
    }

    private void generate(){

        if(mProgressView.getChildCount() > 0){
            mProgressView.removeAllViews();
        }


        LinearLayout progressBlock = new LinearLayout(mContext);
        int progressBlockWeight =(int) (fProgress*SCALE);
        mProgressBlockLP = new LinearLayout.LayoutParams(0, nForegroundHeight);
        mProgressBlockLP.weight = progressBlockWeight;
        progressBlock.setLayoutParams(mProgressBlockLP);
        progressBlock.setBackgroundColor(nForegroundColor);
        progressBlock.setGravity(Gravity.CENTER);
        mProgressView.addView(progressBlock);

        LinearLayout remainBlock = new LinearLayout(mContext);
        mRemainBlockLP = new LinearLayout.LayoutParams(0, nBackgroundHeight);
        mRemainBlockLP.weight = SCALE - progressBlockWeight;
        remainBlock.setLayoutParams(mRemainBlockLP);
        remainBlock.setBackgroundColor(nBackgroundColor);
        remainBlock.setGravity(Gravity.CENTER);
        mProgressView.addView(remainBlock);

        if(mThumbView.getChildCount() > 0){
            mThumbView.removeAllViews();
        }
        LinearLayout thumb = new LinearLayout(mContext);
        mThumbLP = new LinearLayout.LayoutParams(nForegroundHeight*4, nForegroundHeight*4);
        thumb.setLayoutParams(mThumbLP);
        Drawable thumbDrawable = getResources().getDrawable(R.drawable.circle);
        LayerDrawable bubble = (LayerDrawable) thumbDrawable;
        GradientDrawable solidColor = (GradientDrawable) bubble.findDrawableByLayerId(R.id.circle_item);
        solidColor.setColor(nThumbColor);
        thumb.setBackgroundDrawable(thumbDrawable);
        thumb.setGravity(Gravity.CENTER);
        //thumb.setAlpha(0.5f);
        mThumbView.addView(thumb);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int progressViewWidth = mProgressView.getMeasuredWidth();
        int thumbWidth = mThumbView.getChildAt(0).getMeasuredWidth();
        float progressWidth = progressViewWidth*fProgress;
        int paddingLeft = (int)(progressWidth-thumbWidth/2);
        if(paddingLeft < 0 ){
            paddingLeft = 0;
        }
        if(paddingLeft > progressViewWidth-thumbWidth){
            paddingLeft = progressViewWidth-thumbWidth;
        }
        mThumbView.setPadding(paddingLeft,0,0,0);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                onStartTrackingTouch();
                break;

            case MotionEvent.ACTION_MOVE:
                onStartTrackingTouch();
                tracking(event);
                break;

            case MotionEvent.ACTION_UP:
                onStopTrackingTouch();
                break;

            case MotionEvent.ACTION_CANCEL:
                if (mIsDragging) {
                    onStopTrackingTouch();
                }
                break;
        }
        return true;
    }

    private void onStartTrackingTouch() {
        mIsDragging = true;
        if (mOnSimpleSeekBarChangeListener != null) {
            mOnSimpleSeekBarChangeListener.onStartTrackingTouch(this);
        }
    }

    private void onStopTrackingTouch() {
        mIsDragging = false;
        if (mOnSimpleSeekBarChangeListener != null) {
            mOnSimpleSeekBarChangeListener.onStopTrackingTouch(this);
        }
    }

    private void tracking(MotionEvent event){
        int currentX = (int) event.getX();
        int viewWidth = mProgressView.getWidth();
        int progressWidth = currentX - (int)mProgressView.getX();
        fProgress = (float)progressWidth/(float)viewWidth;
        if(fProgress - 1 > 0.01){
            fProgress = 1;
        }
        if(fProgress - 0 < 0.01){
            fProgress = 0;
        }
        updateProgress();

        if (mOnSimpleSeekBarChangeListener != null) {
            mOnSimpleSeekBarChangeListener.onProgressChanged(this, fProgress);
        }
    }

    private synchronized void updateProgress(){
        mProgressBlockLP.weight = fProgress * SCALE;
        mRemainBlockLP.weight = SCALE - mProgressBlockLP.weight;
        requestLayout();
    }

    public void setProgress(float progress){
        fProgress = progress;
        updateProgress();
    }

    public float getProgress(){
        return fProgress;
    }

    public interface OnSimpleSeekBarChangeListener {

        void onProgressChanged(SimpleSeekBar seekBar, float progress);

        void onStartTrackingTouch(SimpleSeekBar seekBar);

        void onStopTrackingTouch(SimpleSeekBar seekBar);
    }

    private OnSimpleSeekBarChangeListener mOnSimpleSeekBarChangeListener;

    public void setOnSimpleSeekBarChangeListener(OnSimpleSeekBarChangeListener l) {
        mOnSimpleSeekBarChangeListener = l;
    }

}
