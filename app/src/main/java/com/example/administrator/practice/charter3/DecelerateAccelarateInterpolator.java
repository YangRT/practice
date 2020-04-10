package com.example.administrator.practice.charter3;

import android.animation.TimeInterpolator;

public class DecelerateAccelarateInterpolator implements TimeInterpolator {
    //返回值决定fraction
    @Override
    public float getInterpolation(float input) {
        float result;
        if(input <= 0.5){
            result = (float)(Math.sin(Math.PI * input)) / 2;
        }else{
            result = (float)(2-Math.sin(Math.PI * input)) / 2;
        }
        return result;
    }
}
