package com.example.administrator.practice.customview.horizontalscrollview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

//支持横向滑动的自定义布局，处理了滑动冲突
public class HorizontalScrollview extends ViewGroup {
    int lastX,lastY;
    Scroller scroller;
    VelocityTracker tracker;
    int maxHeight,maxWidth;

    public HorizontalScrollview(Context context) {
        super(context);
        init();
    }

    public HorizontalScrollview(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public HorizontalScrollview(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){
        scroller = new Scroller(getContext());
        tracker = VelocityTracker.obtain();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        measureChildren(widthMeasureSpec,heightMeasureSpec);
        int childCount = getChildCount();
        maxHeight=0 ;
        maxWidth=0;
        for(int i = 0;i<childCount;i++){
            View v = getChildAt(i);
            maxHeight = maxHeight>=v.getMeasuredHeight()?maxHeight:v.getMeasuredHeight();
            maxWidth += v.getMeasuredWidth();
        }
        //使wrap_content属性起作用
        if(getLayoutParams().width == LayoutParams.WRAP_CONTENT && getLayoutParams().height == LayoutParams.WRAP_CONTENT){
            setMeasuredDimension(maxWidth,maxHeight);
        }else if(getLayoutParams().height == LayoutParams.WRAP_CONTENT){
            setMeasuredDimension(widthSize,maxHeight);
        }else if(getLayoutParams().width == LayoutParams.WRAP_CONTENT){
            setMeasuredDimension(maxWidth,heightSize);
        }else {
            setMeasuredDimension(widthSize,heightSize);
        }
    }

    //处理滑动冲突，判断是否拦截事件
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Boolean intercept = false;
        int x = (int)ev.getX();
        int y = (int)ev.getY();
        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                intercept = false;
                if(!scroller.isFinished()){
                    scroller.abortAnimation();
                    intercept =true;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                int deltaX = x-lastX;
                int deltaY = y-lastY;
                if(Math.abs(deltaX)>Math.abs(deltaY)){
                    intercept = true;
                }
                break;
            case MotionEvent.ACTION_UP:
                intercept = false;
                break;
            default:
                break;
        }
        lastX = x;
        lastY = y;
        return intercept;

    }

    //滑动事件
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        tracker.addMovement(event);
        int x = (int)event.getX();
        int y = (int)event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                if(!scroller.isFinished()){
                    scroller.abortAnimation();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                int deltaX = x-lastX;
                int deltaY = y-lastY;
                scrollBy(-scrollLimit(deltaX),0);
                break;
            case MotionEvent.ACTION_UP:
                int dx=0;
                tracker.computeCurrentVelocity(1000);
                float xVelocity = tracker.getXVelocity();
                if(Math.abs(xVelocity)>=50){
                       dx = 0-scrollLimit((int)xVelocity);
                }
                smoothScrollBy(dx,0);
                tracker.clear();
                break;
        }
        lastX = x;
        lastY = y;
        return true;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        //布局
        int left = getPaddingLeft();
        for(int i = 0;i < childCount;i++){
            View v = getChildAt(i);
            MarginLayoutParams lp = (MarginLayoutParams) v.getLayoutParams();
            v.layout(left+lp.leftMargin,lp.topMargin,left+lp.leftMargin+v.getMeasuredWidth(),v.getMeasuredHeight());
            left += v.getMeasuredWidth();
        }
    }

    private void smoothScrollBy(int dx,int dy){
        scroller.startScroll(getScrollX(),0,dx,0,500);
        invalidate();
    }

    //限制滑动距离
    private int scrollLimit(int delta){
        //长度小于屏幕，禁止滑动
        if(maxWidth-((View)getParent()).getWidth()<=0){
            return 0;
        }else {
            if (delta <= 0) {
                //左滑
                if (getScrollX() == maxWidth - ((View) getParent()).getWidth()) {
                    //滑到最右 ，禁止继续左滑
                    return 0;
                }else{
                    //左滑不超过view的最大长度
                    int dx = Math.min(maxWidth - ((View) getParent()).getWidth() - getScrollX(), Math.abs(delta));
                    return 0 - dx;
                }

            } else {
                //右滑
                if (getScrollX() == 0) {
                    //处于开始状态，禁止右滑
                    return 0;
                }else{
                    return Math.min(Math.abs(getScrollX()),delta);
                }
            }

        }
    }

    @Override
    public void computeScroll() {
        if(scroller.computeScrollOffset()){
            scrollTo(scroller.getCurrX(),scroller.getCurrY());
            postInvalidate();
        }
    }

    //资源回收
    @Override
    protected void onDetachedFromWindow() {
        tracker.recycle();
        super.onDetachedFromWindow();
    }

    //为获取子view 属性，重写方法
    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return new MarginLayoutParams(p);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(),attrs);
    }
}
