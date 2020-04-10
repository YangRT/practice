package com.example.administrator.practice.charter3;

import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;

import com.example.administrator.practice.R;

public class Main3Activity extends AppCompatActivity implements View.OnClickListener {
    private PopupWindow myWindow;
    private View mView;
    private View p;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Button  button = findViewById(R.id.pic_select);
        p = LayoutInflater.from(this).inflate(R.layout.activity_main3,null,false);
        button.setOnClickListener(this);
        mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void showPicSelect(){
        mView = LayoutInflater.from(this).inflate(R.layout.pop_up_window_demo1,null,false);
        myWindow = new PopupWindow(mView,ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
        myWindow.setBackgroundDrawable(new ColorDrawable());
        myWindow.showAtLocation(p,Gravity.BOTTOM,0,0);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.pic_select:
                showPicSelect();
                break;

        }
    }

    @Override
    public void onBackPressed() {
        if(myWindow!=null){
            if(myWindow.isShowing()){
                myWindow.dismiss();
            }else {
                super.onBackPressed();
            }
        }else{
        super.onBackPressed();
        }
    }
}
