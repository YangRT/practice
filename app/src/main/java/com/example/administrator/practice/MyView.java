package com.example.administrator.practice;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MyView extends android.support.v7.widget.AppCompatTextView {


    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void to(int x, int y){
        this.scrollTo(x, y);
        postInvalidate();
    }

    public void log(){
        Log.e("MyView","x:"+getScrollX());
        Log.e("MyView","y:"+getScrollY());
    }

    public void by(int dx,int dy){
        this.scrollBy(dx, dy);
        postInvalidate();
    }


}
