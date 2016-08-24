package com.mintminter.simplewidget;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.LinearLayout;

public class SimpleProgressBar extends LinearLayout {
    private Context mContext;
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
        mContext = context;
        TypedArray styledAttrs = context.obtainStyledAttributes(attrs,
                R.styleable.SimpleProgressBar);
        nScale = styledAttrs.getInt(R.styleable.SimpleProgressBar_spb_scale, nScale);
        nGapInDP = (int) styledAttrs.getDimension(R.styleable.SimpleProgressBar_spb_gap, nGapInDP);
        fProgress = styledAttrs.getFloat(R.styleable.SimpleProgressBar_spb_progress, fProgress);
        nForegroundColor = styledAttrs.getColor(R.styleable.SimpleProgressBar_spb_foreground_color, nForegroundColor);
        nBackgroundColor = styledAttrs.getColor(R.styleable.SimpleProgressBar_spb_background_color, nBackgroundColor);
        styledAttrs.recycle();

        mView = new LinearLayout(context);
        mView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        mView.setOrientation(LinearLayout.HORIZONTAL);
        mView.setWeightSum(nScale);
        mView.setGravity(Gravity.CENTER);
        addView(mView);

        generate();
    }

    private void generate(){

        if(mView.getChildCount() > 0){
            mView.removeAllViews();
        }

        for(int i = 0; i< nScale; i++){
            LinearLayout block = new LinearLayout(mContext);
            LinearLayout.LayoutParams blocklp = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT);
            blocklp.setMargins(0,0,dpToPixal(nGapInDP),0);
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
    }

    public void setScale(int scale){
        nScale = scale;
        mView.setWeightSum(nScale);
        generate();
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

    private int dpToPixal(int dp){
        float scale = mContext.getResources().getDisplayMetrics().density;
        return (int) (dp*scale + 0.5f);//conversiton from dp to pixels
    }
}
