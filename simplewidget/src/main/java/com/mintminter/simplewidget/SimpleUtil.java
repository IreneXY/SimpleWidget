package com.mintminter.simplewidget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;

/*******************************************************************************
 * Copyright (c) 2016-2017 Irene Yu
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 *******************************************************************************/

public class SimpleUtil {
    public static Drawable newRectangleDrawable(Context context, int color, int corner){
        GradientDrawable gradientDrawable = new GradientDrawable(
                GradientDrawable.Orientation.TOP_BOTTOM, new int[]{color, color});
        gradientDrawable.setCornerRadius(corner);
        return gradientDrawable;
    }
}
