package com.example.administrator.practice.charter3;

import android.content.Context;

public class sizeUtils {
    private sizeUtils() {
    }
    public static int dp2px(Context content, final float dpValue) {
        final float scale = content.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
    public static int sp2px(Context context,final float spValue){
        float fontScale=context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue*fontScale+0.5f);
    }
}
