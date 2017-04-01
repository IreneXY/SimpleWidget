package com.mintminter.simplewidget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/*******************************************************************************
 * Copyright (c) 2016-2017 Irene Yu
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 *******************************************************************************/

public class SimpleButton extends LinearLayout {
    private Context mContext;

    private int nIconId = -1;
    private int nTextAppearanceId = -1;
    private String sText;
    private int nIconTintColor = Color.BLACK;
    private int nIconSize = 0;

    private LinearLayout mContainer;
    private ImageView mIcon;
    private TextView mText;

    public SimpleButton(Context context, AttributeSet attrs){
        super(context, attrs);

        mContext = context;
        TypedArray styledAttrs = context.obtainStyledAttributes(attrs,
                R.styleable.SimpleButton);
        nIconId = styledAttrs.getResourceId(R.styleable.SimpleButton_sb_icon, nIconId);
        nIconTintColor = styledAttrs.getColor(R.styleable.SimpleButton_sb_icon_tint, nIconTintColor);
        nIconSize = (int) styledAttrs.getDimension(R.styleable.SimpleButton_sb_icon_size, nIconSize);
        nTextAppearanceId = styledAttrs.getResourceId(R.styleable.SimpleButton_sb_text_appearance, nTextAppearanceId);
        sText = styledAttrs.getString(R.styleable.SimpleButton_sb_text);
        styledAttrs.recycle();

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.layout_simplebutton, this, true);

        mContainer = (LinearLayout) findViewById(R.id.simplebutton_container);
        mIcon = (ImageView) findViewById(R.id.simplebutton_icon);
        mText = (TextView) findViewById(R.id.simplebutton_text);

        if(nIconSize > 0){
            ViewGroup.LayoutParams iconLP = mIcon.getLayoutParams();
            iconLP.height = nIconSize;
            iconLP.width = nIconSize;
            mIcon.setLayoutParams(iconLP);

        }

        if(nIconId > 0){
            mIcon.setVisibility(VISIBLE);
            mIcon.setImageResource(nIconId);
        }else{
            mIcon.setVisibility(GONE);
        }

        mIcon.setColorFilter(nIconTintColor);

        if(!TextUtils.isEmpty(sText)){
            mText.setVisibility(VISIBLE);
            mText.setText(sText);
        }else{
            mText.setVisibility(GONE);
        }

        if(nTextAppearanceId > 0){
            mText.setTextAppearance(mContext, nTextAppearanceId);
        }

    }

    public void setText(String text){
        if(!TextUtils.isEmpty(text)){
            mText.setText(text);
            mText.setVisibility(VISIBLE);
        }else{
            mText.setVisibility(GONE);
        }

    }

    public void setText(Spanned text){
        if(text != null && !TextUtils.isEmpty(text.toString())) {
            mText.setText(text);
            mText.setVisibility(VISIBLE);
        }else{
            mText.setVisibility(GONE);
        }
    }
}