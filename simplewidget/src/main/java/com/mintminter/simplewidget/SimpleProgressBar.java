package com.mintminter.simplewidget;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.LinearLayout;

public class SimpleProgressBar extends LinearLayout {
    private static final int NATURAL_SCALE = 100;
    private Context mContext;
    private LinearLayout mView;
    //private int nScale = -1;
    private int nGap = 5;
    private float fProgress = 0;
    private int nForegroundColor = Color.BLUE;
    private int nBackgroundColor = Color.GRAY;

    private LinearLayout.LayoutParams mProgressBlockLP;
    private LinearLayout.LayoutParams mRemainBlockLP;

    public SimpleProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        mContext = context;
        TypedArray styledAttrs = context.obtainStyledAttributes(attrs,
                R.styleable.SimpleProgressBar);
        //nScale = styledAttrs.getInt(R.styleable.SimpleProgressBar_spb_scale, nScale);
        nGap = (int) styledAttrs.getDimension(R.styleable.SimpleProgressBar_spb_gap, nGap);
        fProgress = styledAttrs.getFloat(R.styleable.SimpleProgressBar_spb_progress, fProgress);
        nForegroundColor = styledAttrs.getColor(R.styleable.SimpleProgressBar_spb_foreground_color, nForegroundColor);
        nBackgroundColor = styledAttrs.getColor(R.styleable.SimpleProgressBar_spb_background_color, nBackgroundColor);
        styledAttrs.recycle();

        mView = new LinearLayout(context);
        mView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
        mView.setOrientation(LinearLayout.HORIZONTAL);
        mView.setWeightSum(NATURAL_SCALE);
        mView.setGravity(Gravity.CENTER);
        addView(mView);

        generate();
    }

    private void generate(){

        if(mView.getChildCount() > 0){
            mView.removeAllViews();
        }

        LinearLayout progressBlock = new LinearLayout(mContext);
        int progressBlockWeight =(int) (fProgress*NATURAL_SCALE);
        mProgressBlockLP = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT);
        mProgressBlockLP.weight = progressBlockWeight;
        progressBlock.setLayoutParams(mProgressBlockLP);
        progressBlock.setBackgroundColor(nForegroundColor);
        progressBlock.setGravity(Gravity.CENTER);
        mView.addView(progressBlock);

        LinearLayout remainBlock = new LinearLayout(mContext);
        mRemainBlockLP = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT);
        mRemainBlockLP.weight = NATURAL_SCALE - progressBlockWeight;
        remainBlock.setLayoutParams(mRemainBlockLP);
        remainBlock.setBackgroundColor(nBackgroundColor);
        remainBlock.setGravity(Gravity.CENTER);
        mView.addView(remainBlock);

//        for(int i = 0; i< nScale; i++){
//            LinearLayout block = new LinearLayout(mContext);
//            LinearLayout.LayoutParams blocklp = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT);
//            blocklp.setMargins(0,0, nGap,0);
//            blocklp.weight = 1;
//            block.setLayoutParams(blocklp);
//            block.setGravity(Gravity.CENTER);
//
//            int nProgress = (int) Math.floor(fProgress*nScale);
//            if(i < nProgress) {
//                block.setBackgroundColor(nForegroundColor);
//                block.setAlpha(1f);
//            }else if (i == nProgress && nProgress < (int) Math.ceil(fProgress*nScale)){
//                block.setBackgroundColor(nForegroundColor);
//                block.setAlpha(0.5f);
//            }else{
//                block.setBackgroundColor(nBackgroundColor);
//                block.setAlpha(1f);
//            }
//
//            mView.addView(block);
//        }
    }

//    public void setScale(int scale){
//        nScale = scale;
//        mView.setWeightSum(nScale);
//        generate();
//    }

    public void setProgress(float processingValue, float maxValue){
        setProgress(processingValue/maxValue);
    }

//    public void setProgress(float progress){
//        fProgress = progress;
//        int ceil = (int) Math.ceil(progress*nScale);
//        int nProgress = (int) Math.floor(progress*nScale);
//        for(int i = 0; i<nProgress; i++){
//            LinearLayout child = (LinearLayout) mView.getChildAt(i);
//            child.setBackgroundColor(nForegroundColor);
//            child.setAlpha(1f);
//        }
//
//        for(int i=nProgress; i<nScale; i++){
//            LinearLayout child = (LinearLayout) mView.getChildAt(i);
//            child.setBackgroundColor(nBackgroundColor);
//            child.setAlpha(1f);
//        }
//
//        if(ceil > nProgress){
//            LinearLayout child = (LinearLayout) mView.getChildAt(nProgress);
//            child.setBackgroundColor(nForegroundColor);
//            child.setAlpha(0.5f);
//        }
//    }

    public synchronized void setProgress(float progress){
        fProgress = progress;
        mProgressBlockLP.weight = fProgress * NATURAL_SCALE;
        mRemainBlockLP.weight = NATURAL_SCALE - mProgressBlockLP.weight;
        //requestLayout();
    }

    public float getProgress(){
        return fProgress;
    }
}
