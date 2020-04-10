package com.example.administrator.practice.charter3;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.RelativeLayout;

import com.example.administrator.practice.R;
//水平展开菜单
public class HorizontalExpandMenu extends RelativeLayout {
    private Context mContext;
    private AttributeSet mAttrs;

    private int defaultWidth;
    private int defaultHeight;
    private int viewWidth;
    private int viewHeight;

    private int menuBackColor;
    private float menuStrokeSize;
    private int menuStrokeColor;
    private float menuCornerRadius;

    private float buttonIconDegrees; //菜单按钮旋转角度
    private float buttonIconSize;
    private float buttonIconStrokeWidth;
    private int buttonIconColor;

    private int buttonStyle; //左or右
    private int buttonRadius;
    private float buttonTop;
    private float buttonBottom;
    private Paint buttonIconPaint;
    private Path path;

    private Point rightButtonCenter;
    private float rightButtonLeft;
    private float rightButtonRight;

    private Point leftButtonCenter; //点
    private float leftButtonLeft;
    private float leftButtonRight;

    private boolean isExpand; //是否展开
    private float downX = -1;
    private float downY = -1;
    private int expandAnimtime;
    ExpandMenuAnim anim; //展开动画

    private float backPathWidth; //当前菜单宽度
    private float maxBackPathWidth; //最大菜单宽度
    private int menuLeft;
    private int menuRight;
    private boolean isFirstLayout;

    private boolean isAnimEnd; //动画是否结束
    private View childView;

    public HorizontalExpandMenu(Context context){
        this(context,null);
    }

    public HorizontalExpandMenu(Context context,AttributeSet set){
        this(context,set,0);
    }
    public HorizontalExpandMenu(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        mAttrs = attrs;
        init();
    }

    private void init(){
        TypedArray array = mContext.obtainStyledAttributes(mAttrs,R.styleable.HorizontalExpandMenu);
        menuBackColor = array.getColor(R.styleable.HorizontalExpandMenu_back_color,Color.WHITE);
        menuStrokeSize = array.getDimension(R.styleable.HorizontalExpandMenu_stroke_size,1);
        menuStrokeColor = array.getColor(R.styleable.HorizontalExpandMenu_stroke_color,Color.GRAY);
        menuCornerRadius = array.getDimension(R.styleable.HorizontalExpandMenu_corner_radius,sizeUtils.dp2px(mContext,20));
        buttonStyle = array.getInteger(R.styleable.HorizontalExpandMenu_button_style,ButtonStyle.Right);
        expandAnimtime = array.getInteger(R.styleable.HorizontalExpandMenu_expand_time,400);
        buttonIconSize = array.getDimension(R.styleable.HorizontalExpandMenu_button_icon_size,sizeUtils.dp2px(mContext,8));
        buttonIconStrokeWidth = array.getDimension(R.styleable.HorizontalExpandMenu_button_icon_stroke_width,8);
        buttonIconColor = array.getColor(R.styleable.HorizontalExpandMenu_button_icon_color,Color.GRAY);
        array.recycle();

        buttonIconDegrees = 90;
        isExpand = true;
        isAnimEnd = false;
        anim = new ExpandMenuAnim();
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                isAnimEnd = true;
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        isFirstLayout = true;
        defaultHeight = sizeUtils.dp2px(mContext,40);
        defaultWidth = sizeUtils.dp2px(mContext,200);

        buttonIconPaint = new Paint();
        buttonIconPaint.setColor(buttonIconColor);
        buttonIconPaint.setStrokeWidth(buttonIconStrokeWidth);
        buttonIconPaint.setStyle(Paint.Style.STROKE);
        buttonIconPaint.setAntiAlias(true);
        path = new Path();
    }

    //按钮位置
    public class ButtonStyle{
        public static final int Left = 1;
        public static final int Right = 0;
    }

    //根据动画进度改变宽度
    private class ExpandMenuAnim extends Animation{
        public ExpandMenuAnim(){}

        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            super.applyTransformation(interpolatedTime, t);
            float left = menuRight - buttonRadius * 2;
            float right = menuLeft + buttonRadius * 2;
            if(childView != null){
                childView.setVisibility(GONE);
            }
            if(isExpand){
                backPathWidth = maxBackPathWidth * interpolatedTime;
                buttonIconDegrees = 90 * interpolatedTime;

                if(backPathWidth == maxBackPathWidth&& childView!=null){
                    childView.setVisibility(VISIBLE);
                }
            }else{
                backPathWidth = maxBackPathWidth - maxBackPathWidth * interpolatedTime;
                buttonIconDegrees = 90 - 90 * interpolatedTime;
            }
            if(buttonStyle == ButtonStyle.Right){
                layout((int)(left-backPathWidth),getTop(),menuRight,getBottom());
            }else{
                layout(menuLeft,getTop(),(int)(right+backPathWidth),getBottom());
            }
            postInvalidate();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int height = measureSize(defaultHeight,heightMeasureSpec);
        int width = measureSize(defaultWidth,widthMeasureSpec);
        viewHeight = height;
        viewWidth = width;
        buttonRadius = viewHeight / 2;
        maxBackPathWidth = viewWidth -buttonRadius * 2;
        backPathWidth = maxBackPathWidth;
        setMeasuredDimension(viewWidth,viewHeight);

        layoutButton();

        if(getBackground() == null){
            setMenuBackground();
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if(isFirstLayout){
            menuLeft = getLeft();
            menuRight = getRight();
            isFirstLayout = false;
        }
        if(getChildCount() > 0){
            childView = getChildAt(0);
            if(isExpand){
                if(buttonStyle == ButtonStyle.Right){
                    childView.layout((int)leftButtonCenter.getX(),(int) buttonTop,(int)rightButtonLeft,(int)buttonBottom);
                }else{
                    childView.layout((int)leftButtonRight,(int) buttonTop,(int)rightButtonCenter.getX(),(int)buttonBottom);
                }

                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(viewWidth,viewHeight);
                params.setMargins(0,0,buttonRadius*3,0);
                childView.setLayoutParams(params);
            }else{
                childView.setVisibility(GONE);
            }
        }
        if(getChildCount() > 1){
            throw new IllegalArgumentException("HorizontalExpandMenu can host only one direct child");
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        viewWidth = w;
        if(isAnimEnd){
            if(buttonStyle == ButtonStyle.Right){
                if(!isExpand){
                    layout((int)(menuRight - buttonRadius * 2-backPathWidth),getTop(),menuRight,getBottom());
                }
            }else{
                if(!isExpand){
                    layout(menuLeft,getTop(),(int)(menuLeft+buttonRadius*2+backPathWidth),getBottom());
                }
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        layoutButton();
        if(buttonStyle == ButtonStyle.Right){
            drawRightIcon(canvas);
        }else{
            drawLeftIcon(canvas);
        }
        super.onDraw(canvas);
    }

    //处理点击事件
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);
        float x = event.getX();
        float y = event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                downX = event.getX();
                downY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                if(backPathWidth == maxBackPathWidth || backPathWidth == 0) {
                    switch (buttonStyle) {
                        case ButtonStyle.Right:
                            if (x == downX && y == downY && y >= buttonTop && y <= buttonBottom && x >= rightButtonLeft && x <= rightButtonRight) {
                                expandMenu(expandAnimtime);
                            }
                            break;
                        case ButtonStyle.Left:
                            if (x == downX && y == downY && y >= buttonTop && y <= buttonBottom && x >= leftButtonLeft && x <= leftButtonRight) {
                                expandMenu(expandAnimtime);
                            }
                            break;
                    }
                }
                break;
        }
        return true;
    }

    //根据条件设置大小
    private int measureSize(int defaultSize, int measureSpec){
        int result = defaultSize;
        int specMode = View.MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        if(specMode == MeasureSpec.EXACTLY){
            result = specSize;
        }else if(specMode == MeasureSpec.AT_MOST){
            result = Math.min(result,specSize);
        }
        return result;
    }

    //执行动画，改变布局
    private void expandMenu(int time){
        anim.setDuration(time);
        isExpand = isExpand?false:true; //状态转换
        this.startAnimation(anim);
        isAnimEnd = false;
    }

    //初始化按钮属性
    private void layoutButton(){
        buttonTop = 0;
        buttonBottom = viewHeight;

        rightButtonCenter = new Point(viewWidth - buttonRadius,viewHeight / 2);
        rightButtonLeft = rightButtonCenter.getX() - buttonRadius;
        rightButtonRight = rightButtonCenter.getX() + buttonRadius;

        leftButtonCenter = new Point(buttonRadius,viewHeight / 2);
        leftButtonLeft  = leftButtonCenter.getX() - buttonRadius;
        leftButtonRight = leftButtonCenter.getX() + buttonRadius;
    }

    //设置布局背景
    private void setMenuBackground(){
        GradientDrawable gd = new GradientDrawable();
        gd.setColor(menuBackColor);
        gd.setStroke((int)menuStrokeSize,menuStrokeColor);
        gd.setCornerRadius(menuCornerRadius);
        setBackground(gd);
    }

    //绘制图标
    private void drawRightIcon(Canvas canvas){
        path.reset();
        path.moveTo(rightButtonCenter.getX()-buttonIconSize,rightButtonCenter.getY());
        path.lineTo(rightButtonCenter.getX()+buttonIconSize,rightButtonCenter.getY());
        canvas.drawPath(path,buttonIconPaint);

        canvas.save();
        canvas.rotate(buttonIconDegrees,rightButtonCenter.getX(),rightButtonCenter.getY()); //旋转画布
        path.reset();
        path.moveTo(rightButtonCenter.getX(),rightButtonCenter.getY()-buttonIconSize);
        path.lineTo(rightButtonCenter.getX(),rightButtonCenter.getY()+buttonIconSize);
        canvas.drawPath(path,buttonIconPaint);
        canvas.restore();
    }

    private void drawLeftIcon(Canvas canvas){
        path.reset();
        path.moveTo(leftButtonCenter.getX()-buttonIconSize,leftButtonCenter.getY());
        path.lineTo(leftButtonCenter.getX()+buttonIconSize,leftButtonCenter.getY());
        canvas.drawPath(path,buttonIconPaint);

        canvas.save();
        canvas.rotate(-buttonIconDegrees,leftButtonCenter.getX(),leftButtonCenter.getY()); //旋转画布
        path.reset();
        path.moveTo(leftButtonCenter.getX(),leftButtonCenter.getY()-buttonIconSize);
        path.lineTo(leftButtonCenter.getX(),leftButtonCenter.getY()+buttonIconSize);
        canvas.drawPath(path,buttonIconPaint);
        canvas.restore();
    }
}
