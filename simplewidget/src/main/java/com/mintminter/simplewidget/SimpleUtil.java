package com.mintminter.simplewidget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.support.v4.content.ContextCompat;

/**
 * Created by yuxin on 9/11/16.
 */
public class SimpleUtil {
    public static Drawable setRectangleDrawable(Context context, int color, int corner){
        Drawable drawable = ContextCompat.getDrawable(context,R.drawable.round_rectangle).getConstantState().newDrawable();
        LayerDrawable layerDrawable = (LayerDrawable) drawable;
        GradientDrawable gradientDrawable = (GradientDrawable) layerDrawable.findDrawableByLayerId(R.id.round_rectangle);
        gradientDrawable.setColor(color);
        gradientDrawable.setCornerRadius(corner);
        return drawable;
    }
}
