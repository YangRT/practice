package com.example.administrator.practice.charter3;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.administrator.practice.R;

public class summitButton extends View implements Animator.AnimatorListener {
    private final int  mStrokeColor;
    private final int mTextColor;
    private final int mProgressWidth;
    private OnClickListener mListener;
    private Paint mPaint;

    private int mDefaultWidth;
    private int mDefaultRadius;
    private int rectWidth;
    private TextPaint mTextPaint;
    private int mDefaultTextSize;
    private int mTopBottomPadding;
    private int mLeftRightPadding;
    private String mText;
    private int mTextWidth;
    private int mTextSize;
    private int mRadius;

    private Path mPath;

    private RectF leftRect;
    private RectF rightRect;
    private RectF contentRect;
    private RectF progressRect;

    private int left;
    private int right;
    private int bottom;
    private int top;
    private boolean isUnfold;
    private int mBackgroundColor;
    private State mCurrentState;
    private float circleSweep;
    private ObjectAnimator loadAnimator;
    private ObjectAnimator shrinkAnim;

    private boolean progressReverse;
    private int mProgressSecondColor;
    private int mProgressColor;
    private int mProgressStartAngel;
    LoadListener mLoadListener;

    public LoadListener getListener(){
        return mLoadListener;
    }

    public void setmLoadListener(LoadListener mLoadListener) {
        this.mLoadListener = mLoadListener;
    }

    @Override
    public void onAnimationStart(Animator animation) {

    }

    @Override
    public void onAnimationEnd(Animator animation) {
        isUnfold = false;
        mCurrentState = State.FODDING;
        load();
    }

    @Override
    public void onAnimationCancel(Animator animation) {

    }

    @Override
    public void onAnimationRepeat(Animator animation) {

    }

    enum State{
        INITIAL,
        FODDING,
        LOADING,
        COMPLETED_ERROR,
        COMPLETED_SUCCESSED,
        LOADDING_PAUSE
    }
    public summitButton(Context context){
        this(context,null);
    }

    public summitButton(Context context,AttributeSet attributeSet){
        this(context,attributeSet,0);
    }

    public summitButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mDefaultRadius = 40;
        TypedArray array = context.obtainStyledAttributes(attrs,R.styleable.summitButton);
        mDefaultTextSize = 24;
        mTextSize = array.getDimensionPixelSize(R.styleable.summitButton_android_textSize,mDefaultTextSize);
        mStrokeColor = array.getColor(R.styleable.summitButton_strokeColor,Color.RED);
        mTextColor = array.getColor(R.styleable.summitButton_content_color,Color.WHITE);
        mText = array.getString(R.styleable.summitButton_android_text);
        mRadius = array.getDimensionPixelOffset(R.styleable.summitButton_radius,mDefaultRadius);
        mTopBottomPadding = array.getDimensionPixelOffset(R.styleable.summitButton_contentPaddingTB,10);
        mLeftRightPadding = array.getDimensionPixelOffset(R.styleable.summitButton_contentPaddingLR,10);
        mBackgroundColor = array.getColor(R.styleable.summitButton_backColor,Color.WHITE);
        mProgressColor = array.getColor(R.styleable.summitButton_progressColor,Color.WHITE);
        mProgressSecondColor = array.getColor(R.styleable.summitButton_progressSecondColor,Color.parseColor("#c3c3c3"));
        mProgressWidth = array.getDimensionPixelOffset(R.styleable.summitButton_progressWidth,2);
        array.recycle();

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(mStrokeColor);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(mProgressWidth);

        mDefaultWidth = 200;

        mTextPaint = new TextPaint();
        mTextPaint.setColor(mTextColor);
        mTextPaint.setTextAlign(Paint.Align.CENTER);
        mTextPaint.setTextSize(mTextSize);

        rectWidth = mDefaultWidth - mDefaultRadius * 2;
        leftRect = new RectF();
        rightRect = new RectF();
        contentRect = new RectF();
        isUnfold = true;
        mListener = new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mCurrentState == State.FODDING){
                    return;
                }
                if(mCurrentState == State.INITIAL){
                    if(isUnfold){
                        shringk();
                    }
                }else if(mCurrentState == State.COMPLETED_ERROR){
                    if(mLoadListener != null){
                        mLoadListener.onClick(false);
                    }
                }else if(mCurrentState == State.COMPLETED_SUCCESSED){
                    if(mLoadListener != null){
                        mLoadListener.onClick(true);
                    }
                }else if(mCurrentState == State.LOADDING_PAUSE){
                    if(mLoadListener != null){
                        mLoadListener.needing();
                        load();
                    }
                }else if (mCurrentState == State.LOADING){
                    mCurrentState = State.LOADDING_PAUSE;
                    cancelAnimation();
                    invaidateSelft();
                }
            }
        };
        setOnClickListener(mListener);
        mCurrentState = State.INITIAL;

    }

    public void reset(){
        mCurrentState = State.INITIAL;
        rectWidth = getWidth() - mRadius * 2;
        isUnfold = true;
        cancelAnimation();
        invaidateSelft();

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int withSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);
        int resultW = withSpecSize;
        int resultH = heightSpecSize;
        int contentW = 0;
        int contentH = 0;
        if(widthSpecMode == MeasureSpec.AT_MOST){
            mTextWidth = (int)mTextPaint.measureText(mText);
            contentW += mTextWidth + mLeftRightPadding * 2 + mRadius * 2;
            resultW = contentW < withSpecSize?contentW:withSpecSize;
        }
        if(heightSpecMode == MeasureSpec.AT_MOST){
            contentH += mTopBottomPadding * 2 + mTextSize;
            resultH = contentH < heightSpecSize?contentH:heightSpecSize;
        }
        resultW = resultW < 2 * mRadius?2 * mRadius:resultW;
        resultH = resultH < 2 * mRadius?2 * mRadius:resultH;
        mRadius = resultH / 2;
        rectWidth = resultW - 2 * mRadius;
        setMeasuredDimension(resultW,resultH);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int cx = getWidth() / 2;
        int cy = getHeight() / 2;
        drawPath(canvas,cx,cy);
        int textDescent = (int) mTextPaint.getFontMetrics().descent;
        int textAscent = (int) mTextPaint.getFontMetrics().ascent;
        int delta = Math.abs(textAscent) - Math.abs(textDescent);
        int circleR = mRadius / 2;
        if(mCurrentState == State.INITIAL){
            canvas.drawText(mText,cx,cy+delta/2,mTextPaint);
        }else if(mCurrentState == State.LOADING){
            if (progressRect == null){
                progressRect = new RectF();
            }
            progressRect.set(cx-circleR,cy-circleR,cx+circleR,cy+circleR);
            mPaint.setColor(mProgressSecondColor);
            canvas.drawCircle(cx,cy,circleR,mPaint);
            mPaint.setColor(mProgressColor);
            if(circleSweep != 360){
                mProgressStartAngel = progressReverse?270:(int)(270+circleSweep);
                canvas.drawArc(progressRect,mProgressStartAngel,progressReverse?circleSweep:(int)(360-circleSweep),false,mPaint);
            }
            mPaint.setColor(mBackgroundColor);
        }else if(mCurrentState == State.COMPLETED_ERROR){

        }  else if(mCurrentState == State.COMPLETED_SUCCESSED){

        }else if(mCurrentState == State.LOADDING_PAUSE){

        }

    }

    public void setRectWidth(int width){
        rectWidth = width;

    }

    private void invaidateSelft(){
        if(Looper.myLooper() == Looper.getMainLooper()){
            invalidate();
        }else {
            postInvalidate();
        }
    }

    private void shringk(){
        if(shrinkAnim == null){
            shrinkAnim = ObjectAnimator.ofInt(this,"rectWidth",rectWidth,0);
        }
        shrinkAnim.addListener(this);
        shrinkAnim.setDuration(4000);
        shrinkAnim.start();
    }

    public void load(){
        if(loadAnimator == null){
            loadAnimator = ObjectAnimator.ofFloat(this,"circleSweep",0,360);
        }
        loadAnimator.setDuration(1000);
        loadAnimator.setRepeatMode(ValueAnimator.RESTART);
        loadAnimator.setRepeatCount(ValueAnimator.INFINITE);
        loadAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                progressReverse = !progressReverse;
            }
        });
        loadAnimator.start();
        mCurrentState = State.LOADING;
    }
    public void loadSuccessed(){
        mCurrentState = State.COMPLETED_SUCCESSED;
        cancelAnimation();
        invaidateSelft();
    }
    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        cancelAnimation();
    }

    private void drawPath(Canvas c,int x,int y){
        if(mPath == null){
            mPath = new Path();
        }
        mPath.reset();
        left = x - rectWidth / 2 - mRadius;
        top = 0;
        right = x + rectWidth / 2 + mRadius;
        bottom = getHeight();

        leftRect.set(left,top,left+mRadius*2,bottom);
        rightRect.set(right-mRadius*2,top,right,bottom);
        contentRect.set(x-rectWidth/2,top,x+rectWidth/2,bottom);
        mPath.moveTo(x - rectWidth / 2,bottom);
        mPath.arcTo(leftRect,90.0f,180f);
        mPath.lineTo(x + rectWidth / 2,top);
        mPath.addArc(rightRect,270.0f,180f);
        mPath.lineTo(x-rectWidth/2,bottom);
        mPath.close();

        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(mBackgroundColor);
        c.drawPath(mPath,mPaint);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(mStrokeColor);
    }

    private void cancelAnimation(){
        if(shrinkAnim != null && shrinkAnim.isRunning()){
            shrinkAnim.removeAllListeners();
            shrinkAnim.cancel();
            shrinkAnim = null;
        }
        if(loadAnimator != null && loadAnimator.isRunning()){
            loadAnimator.removeAllListeners();
            loadAnimator.cancel();
            loadAnimator = null;
        }
    }



    public void loadFailed(){
        mCurrentState = State.COMPLETED_ERROR;
        cancelAnimation();
        invaidateSelft();
    }

    public void setCircleSweep(float circleSweep){
        this.circleSweep = circleSweep;
    }
    public interface LoadListener{
        void onClick(boolean isSuccessed);
        void needing();
    }

}
