package com.example.administrator.practice.charter3;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.administrator.practice.R;

public class AbilityView extends View {
    private Paint bgPaint;
    private Paint linePaint;
    private Paint mPaint;
    Path mPath;
    private Paint titlePaint;
    private int dataCount = 7;
    private float radian = (float) (Math.PI * 2 / dataCount);
    private int mColor;
    private float radius;
    private int width;
    private int height;
    private float centerX;
    private float centerY;
    private String[] bgColor = {"#1b604c","#1c9975","#19ca97","#08f7b2"};
    private String[] title = {"击杀","生存","助攻","物理","魔法","防御","金钱"};
    private float[] data = {0.45f,0.6f,0.5f,0.6f,0.5f,0.5f,0.65f};
    public AbilityView(Context context){
        this(context,null);
    }

    public AbilityView(Context context,AttributeSet attrs){
        this(context,attrs,0);
    }

    public AbilityView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    private void init(Context context,AttributeSet attributeSet){
        TypedArray array = context.obtainStyledAttributes(attributeSet,R.styleable.AbilityView);
        mColor = array.getColor(R.styleable.AbilityView_my_color,Color.RED);
        array.recycle();
        bgPaint = new Paint();
        mPath = new Path();
        bgPaint.setAntiAlias(true);
        bgPaint.setStyle(Paint.Style.FILL);
        linePaint = new Paint();
        linePaint.setColor(Color.WHITE);
        linePaint.setAntiAlias(true);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(8);
        mPaint.setColor(mColor);
        titlePaint = new Paint();
        titlePaint.setColor(Color.BLACK);
        titlePaint.setTextSize(25);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);
        height = heightSpecSize;
        width = widthSpecSize;
        if(widthSpecMode == MeasureSpec.AT_MOST && heightSpecMode == MeasureSpec.AT_MOST){
            height = 200;
            width = 200;
            setMeasuredDimension(200,200);
        }else if(widthSpecMode == MeasureSpec.AT_MOST){
            width = Math.min(widthSpecSize,200);
            setMeasuredDimension(width,heightSpecSize);
        }else if(heightSpecMode == MeasureSpec.AT_MOST){
            height = Math.min(heightSpecSize,200);
            setMeasuredDimension(widthSpecSize,height);
        }
        radius = Math.min(width,height) / 2 * 0.8f;
        centerX = width/2;
        centerY = height/2;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for(int i = 4;i > 0;i--){
            bgPaint.setColor(Color.parseColor(bgColor[i-1]));
            drawRect(canvas,radius*(float)(0.25*i));
        }
        drawLine(canvas);
        drawText(canvas);
        drawMy(canvas);
    }

    private void drawRect(Canvas canvas,float length){
        for(int i = 0;i < dataCount;i++){
            if(i == 0){
                mPath.reset();
                mPath.moveTo(centerX+(float) Math.sin(i * radian)*length,centerY-(float) Math.cos(i * radian)*length);
            }else{
                mPath.lineTo(centerX+(float) Math.sin(i * radian)*length,centerY-(float) Math.cos(i * radian)*length);
            }
        }
        mPath.close();
        canvas.drawPath(mPath,bgPaint);
    }

    private void drawLine(Canvas canvas){
        for(int i = 0;i < dataCount;i++){
            canvas.drawLine(centerX,centerY,centerX+(float)Math.sin(i * radian)*radius,centerY-(float)Math.cos(i * radian)*radius,linePaint);
        }
    }

    private void drawText(Canvas canvas){
        Paint.FontMetrics fontMetrics = titlePaint.getFontMetrics();
        float fontHeight = fontMetrics.descent - fontMetrics.ascent;
        for(int i = 0;i < dataCount;i++){
            float angle = radian * i;
            float x = (float)(centerX+(radius+fontHeight/2)*Math.sin(radian*i));
            float y = (float)(centerY-(radius+fontHeight/2)*Math.cos(radian*i));
            if(i == 0){
                float dis = titlePaint.measureText(title[i]);
                canvas.drawText(title[i],x-dis/2,y,titlePaint);
            }else if(i >= 4){
                float dis = titlePaint.measureText(title[i]);
                canvas.drawText(title[i],x-dis,y,titlePaint);
            }else{
                canvas.drawText(title[i],x,y,titlePaint);
            }
        }
    }

    private void drawMy(Canvas canvas){
        for(int i = 0;i < dataCount;i++){
            if(i == 0){
                mPath.reset();
                mPath.moveTo(centerX+(float) Math.sin(i * radian)*radius*data[i],centerY-(float) Math.cos(i * radian)*radius*data[i]);
            }else {
                mPath.lineTo(centerX+(float) Math.sin(i * radian)*radius*data[i],centerY-(float) Math.cos(i * radian)*radius*data[i]);
            }
        }
        mPath.close();
        canvas.drawPath(mPath,mPaint);
    }

}
