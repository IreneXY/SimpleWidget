package com.mintminter.simplewidget;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.LinearLayout;

public class SimpleProgressBar extends LinearLayout {
    private LinearLayout mView;
    private int nScale = 10;
    private int nGapInDP = 5;
    private float fProgress = 0;
    private int nForegroundColor = Color.BLUE;
    private int nBackgroundColor = Color.GRAY;

    public SimpleProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray styledAttrs = context.obtainStyledAttributes(attrs,
                R.styleable.SimpleProgressBar);
        nScale = styledAttrs.getInt(R.styleable.SimpleProgressBar_scale, nScale);
        nGapInDP = (int) styledAttrs.getDimension(R.styleable.SimpleProgressBar_gap, nGapInDP);
        fProgress = styledAttrs.getFloat(R.styleable.SimpleProgressBar_progress, fProgress);
        nForegroundColor = styledAttrs.getColor(R.styleable.SimpleProgressBar_foreground_color, nForegroundColor);
        nBackgroundColor = styledAttrs.getColor(R.styleable.SimpleProgressBar_background_color, nBackgroundColor);
        styledAttrs.recycle();

        mView = new LinearLayout(context);
        mView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        mView.setOrientation(LinearLayout.HORIZONTAL);
        mView.setWeightSum(nScale);
        mView.setGravity(Gravity.CENTER);
        generate(context);
    }

    private void generate(Context context){
        if(mView.getChildCount() > 0){
            mView.removeAllViews();
        }

        for(int i = 0; i< nScale; i++){
            LinearLayout block = new LinearLayout(context);
            LinearLayout.LayoutParams blocklp = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT);
            blocklp.setMargins(0,0,dpToPixal(context, nGapInDP),0);
            blocklp.weight = 1;
            block.setLayoutParams(blocklp);
            block.setGravity(Gravity.CENTER);

            int nProgress = (int) Math.floor(fProgress*nScale);
            if(i < nProgress) {
                block.setBackgroundColor(nForegroundColor);
                block.setAlpha(1f);
            }else if (i == nProgress && nProgress < (int) Math.ceil(fProgress*nScale)){
                block.setBackgroundColor(nForegroundColor);
                block.setAlpha(0.5f);
            }else{
                block.setBackgroundColor(nBackgroundColor);
                block.setAlpha(1f);
            }

            mView.addView(block);
        }

        addView(mView);
    }

    public void setProgress(float processingValue, float maxValue){
        setProgress(processingValue/maxValue);
    }

    public void setProgress(float progress){
        fProgress = progress;
        int ceil = (int) Math.ceil(progress*nScale);
        int nProgress = (int) Math.floor(progress*nScale);
        for(int i = 0; i<nProgress; i++){
            LinearLayout child = (LinearLayout) mView.getChildAt(i);
            child.setBackgroundColor(nForegroundColor);
            child.setAlpha(1f);
        }

        for(int i=nProgress; i<nScale; i++){
            LinearLayout child = (LinearLayout) mView.getChildAt(i);
            child.setBackgroundColor(nBackgroundColor);
            child.setAlpha(1f);
        }

        if(ceil > nProgress){
            LinearLayout child = (LinearLayout) mView.getChildAt(nProgress);
            child.setBackgroundColor(nForegroundColor);
            child.setAlpha(0.5f);
        }
    }

    public static int dpToPixal(Context context,int dp){
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp*scale + 0.5f);//conversiton from dp to pixels
    }
}
