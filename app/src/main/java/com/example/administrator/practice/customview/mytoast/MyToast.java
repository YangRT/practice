package com.example.administrator.practice.customview.mytoast;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.practice.R;

//自定义Toast
public class MyToast {

    private static final String TAG = "MyToast";

    private Toast mToast;
    private View mView = null;

    //自定义布局
    public MyToast(Context context, View view,int duration){
        mToast = new Toast(context);
        mToast.setView(view);
        mView = view;
        mToast.setDuration(duration);
    }

    //默认布局
    public  MyToast(Context context,int duration){
        mToast = new Toast(context);
        mToast.setDuration(duration);
    }

    public MyToast setText(CharSequence text,int textColor,int background){
        if(mView != null){
            Log.e(TAG,"set the text or attribute in your layout");
            return this;
        }
        if(mToast != null){
            View view = mToast.getView();
            TextView message = (TextView) view.findViewById(R.id.message);
            message.setBackgroundResource(background);
            message.setTextColor(textColor);
            mToast.setText(text);
        }
        return this;
    }

    public MyToast setText(CharSequence text){
        if(mView != null){
            Log.e(TAG,"set the text in your layout");
            return this;
        }
        if(mToast != null){
            mToast.setText(text);
        }
        return this;
    }


    public MyToast setText(CharSequence text,int textColor){
        if(mView != null){
            Log.e(TAG,"set the text or attribute in your layout");
            return this;
        }
        if(mToast != null){
            View view = mToast.getView();
            TextView message = (TextView) view.findViewById(R.id.message);
            message.setTextColor(textColor);
            mToast.setText(text);
        }
        return this;
    }

    public MyToast setText(int background,CharSequence text) {
        if(mView != null){
            Log.e(TAG,"set the text or attribute in your layout");
            return this;
        }
        if (mToast != null) {
            View view = mToast.getView();
            TextView message = (TextView) view.findViewById(R.id.message);
            message.setBackgroundResource(background);
            mToast.setText(text);
        }
        return this;
    }

    public MyToast setLocation(int gravity,int xOffset,int yOffset){
        if(mToast != null){
            mToast.setGravity(gravity,xOffset,yOffset);
        }
        return this;
    }

    public MyToast addView(View view,int position){
        if(mView != null){
            Log.e(TAG,"add the view to your layout");
            return this;
        }
        if (mToast != null && view != null){
            LinearLayout linearLayout = (LinearLayout)mToast.getView();
            linearLayout.addView(view,position);
        }else {
            Log.e(TAG,"the view is null ！");
        }
        return this;
    }
    public void show(){
        if(mToast != null){
            mToast.show();
        }
    }

    public Toast getToast(){
        return mToast;
    }

    public View getView(){
        if (mView != null){
            return mView;
        }else {
            Log.e(TAG,"your layout is null");
            return null;
        }
    }
}
