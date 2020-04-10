package com.example.administrator.practice.charter3;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class StarView extends View {
    private Paint mPaint = new Paint();
    private float radius;
    private float centerX;
    private float centerY;
    private float angle = (float) 360 / 7;
    public StarView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        //五角星
//        canvas.drawLine(100, 500, 700, 500, mPaint);//        canvas.translate(100,500);
//        canvas.rotate(36);
//        canvas.drawLine(0, 0, 600, 0, mPaint);
//        canvas.translate(600,0);
//        canvas.rotate(36);
//        canvas.drawLine(0, 0, -600, 0, mPaint);
//        canvas.translate(-600, 0);
//        canvas.rotate(36);
//        canvas.drawLine(0, 0, 600, 0, mPaint);
//        canvas.translate(600, 0);
//        canvas.rotate(36);
//        canvas.drawLine(0, 0, -600, 0, mPaint);

//        canvas.drawLine(300,300,500,300,mPaint);
//        canvas.translate(300,300);
//        canvas.rotate(angle);
//        canvas.drawLine(0,0,200,0,mPaint);
//        canvas.rotate(angle);
//        canvas.drawLine(0,0,200,0,mPaint);
//        canvas.rotate(angle);
//        canvas.drawLine(0,0,200,0,mPaint);
//        canvas.rotate(angle);
//        canvas.drawLine(0,0,200,0,mPaint);
//        canvas.rotate(angle);
//        canvas.drawLine(0,0,200,0,mPaint);
//        canvas.rotate(angle);
//        canvas.drawLine(0,0,200,0,mPaint);
//        //太极图
//        mPaint.setColor(Color.BLACK);
//        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
//        radius = Math.min(getWidth(),getHeight())/2;
//        centerX = getWidth()/2;
//        centerY = getHeight()/2;
//        canvas.drawCircle(centerX,centerY,radius,mPaint);
//        mPaint.setColor(Color.WHITE);
//        mPaint.setStyle(Paint.Style.FILL);
//        canvas.drawArc(centerX-radius,centerY-radius,centerX+radius,centerY+radius,-90,180,true,mPaint);
//        mPaint.setColor(Color.BLACK);
//        mPaint.setStyle(Paint.Style.STROKE);
//        canvas.drawCircle(centerX,centerY,radius,mPaint);
//        mPaint.setStyle(Paint.Style.FILL);
//        canvas.drawCircle(centerX,centerY-radius/2,radius/2,mPaint);
//        mPaint.setColor(Color.WHITE);
//        canvas.drawCircle(centerX,centerY+radius/2,radius/2,mPaint);
//        canvas.drawCircle(centerX,centerY-radius/2,radius/8,mPaint);
//        mPaint.setColor(Color.BLACK);
//        canvas.drawCircle(centerX,centerY+radius/2,radius/8,mPaint);
    }
}
