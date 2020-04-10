package com.example.administrator.practice.charter3;

import android.animation.TypeEvaluator;

public class ColorEvaluator implements TypeEvaluator {
    private int mCurrentRed = -1;
    private int mCurrentGreen = -1;
    private int mCurrentBlue = -1;

    @Override
    public Object evaluate(float fraction, Object startValue, Object endValue) {
        String startColor = (String) startValue;
        String endColor = (String) endValue;
        int startRed = Integer.parseInt(startColor.substring(1,3),16);
        int startGreen = Integer.parseInt(startColor.substring(3,5),16);
        int startBlue = Integer.parseInt(startColor.substring(5,7),16);
        int endRed = Integer.parseInt(endColor.substring(1,3),16);
        int endGreen = Integer.parseInt(endColor.substring(3,5),16);
        int endBlue = Integer.parseInt(endColor.substring(5,7),16);
        if(mCurrentRed == -1){
            mCurrentRed = startRed;
        }
        if (mCurrentBlue == -1){
            mCurrentBlue = startBlue;
        }
        if (mCurrentGreen == -1){
            mCurrentGreen = startGreen;
        }
        //计算初始颜色和结束颜色之间的差值
        int redDiff = Math.abs(startRed - endRed);
        int blueDiff = Math.abs(startBlue - endBlue);
        int greenDiff = Math.abs(startGreen - endGreen);
        int colorDiff = redDiff + greenDiff + blueDiff;
        if(mCurrentRed != endRed){
            mCurrentRed = getmCurrentColor(startRed,endRed,colorDiff,0,fraction);
        }else if(mCurrentGreen != endGreen){
            mCurrentGreen = getmCurrentColor(startGreen,endGreen,colorDiff,redDiff,fraction);
        }else if(mCurrentBlue != endBlue){
            mCurrentBlue = getmCurrentColor(startBlue,endBlue,colorDiff,redDiff+greenDiff,fraction);
        }
        String currentColor = "#"+ getHexString(mCurrentRed)+getHexString(mCurrentGreen)+getHexString(mCurrentBlue);
        return currentColor;
    }

    private int getmCurrentColor(int startColor,int endColor,int colorDiff,int offest,float fraction){
        int currentColor;
        if(startColor > endColor){
            currentColor = (int)(startColor - (fraction*colorDiff-offest));
            if(currentColor < endColor){
                currentColor = endColor;
            }
        }else{
            currentColor= (int)(startColor + (fraction*colorDiff-offest));
            if(currentColor > endColor){
                currentColor = endColor;
            }
        }
        return currentColor;
    }

    private String getHexString(int value){
        String hexString = Integer.toHexString(value);
        if(hexString.length() == 1){
            hexString = "0"+hexString;
        }
        return hexString;
    }
}
