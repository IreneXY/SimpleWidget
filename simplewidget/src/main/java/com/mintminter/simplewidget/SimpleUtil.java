package com.mintminter.simplewidget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;

public class SimpleUtil {
    public static Drawable newRectangleDrawable(Context context, int color, int corner){
        GradientDrawable gradientDrawable = new GradientDrawable(
                GradientDrawable.Orientation.TOP_BOTTOM, new int[]{color, color});
        gradientDrawable.setCornerRadius(corner);
        return gradientDrawable;
    }
}
