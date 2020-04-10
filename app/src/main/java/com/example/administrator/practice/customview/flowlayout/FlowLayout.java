package com.example.administrator.practice.customview.flowlayout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class FlowLayout extends ViewGroup {
    //可用宽度
    private int usefulWidth;
    //子view x轴初始位置
    private int left;
    //已用宽度
    private int usedWidth;
    //总行数
    private int totalLines=0;
    //总高度
    private int totalHight;
    //每行子View的最大高度
    private int lineMaxHeight = 0;
    //存储每个子view位置信息
    private List<ViewLocation> viewLocationList = new ArrayList<>();

    public FlowLayout(Context context) {
        super(context);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //每行子View的最大宽度
        int lineMaxWidth = getPaddingLeft();

        //设置初值
        totalHight = getPaddingTop();
        usedWidth = getPaddingLeft();
        left =getPaddingLeft();

        if(viewLocationList.size() != 0){
            //及时清空，避免布局混乱
            viewLocationList.clear();
        }

        //获取限制大小
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        usefulWidth = widthSize;

        int childCount = getChildCount();
        //遍历子View,计算子view位置以及布局大小
        if(childCount>0) {
            totalLines = 1;
            for (int i = 0; i < childCount; i++) {
                View v = getChildAt(i);
                measureChild(v, widthMeasureSpec, heightMeasureSpec);
                //获取子view属性
                MarginLayoutParams lp = (MarginLayoutParams) v.getLayoutParams();
                //判断是否换行
                if ((v.getMeasuredWidth() + lp.leftMargin) <= usefulWidth) {
                    setLocation(v, lp);
                } else {
                    //换行更新或重置各变量
                    totalLines++;
                    lineMaxWidth = lineMaxWidth >= usedWidth ? lineMaxWidth : usedWidth;
                    totalHight += lineMaxHeight;
                    lineMaxHeight = 0;
                    usefulWidth = widthSize;
                    usedWidth = getPaddingLeft();
                    left = getPaddingLeft();
                    //直接添加，即使新的一行也装不下
                    setLocation(v, lp);
                }

            }
        }

        //判断最后一行
        lineMaxWidth = lineMaxWidth >=usedWidth? lineMaxWidth :usedWidth;
        totalHight += lineMaxHeight+getPaddingBottom();

        System.out.println("heightSize:"+heightSize+",totalHeight:"+totalHight);
        //使wrap_content属性起作用
        if(getLayoutParams().width == LayoutParams.WRAP_CONTENT && getLayoutParams().height == LayoutParams.WRAP_CONTENT){
            setMeasuredDimension(lineMaxWidth,totalHight);
        }else if(getLayoutParams().width == LayoutParams.WRAP_CONTENT){
            setMeasuredDimension(lineMaxWidth,heightSize);
        }else if(getLayoutParams().height == LayoutParams.WRAP_CONTENT){
            setMeasuredDimension(widthSize,totalHight);
        }else {
            setMeasuredDimension(widthSize,heightSize);
        }
    }

    //保存各view的位置参数
    private void setLocation(View v,MarginLayoutParams lp){
        ViewLocation mLocation = new ViewLocation();
        mLocation.setLeft(left+lp.leftMargin);
        //getWidth() layout后才能获取到值，getMeasuredWidth()测量后就获取到值
        mLocation.setRight(mLocation.getLeft()+v.getMeasuredWidth());
        mLocation.setTop(totalHight+lp.topMargin);
        mLocation.setBottom(mLocation.getTop()+v.getMeasuredHeight());
        mLocation.setMyLine(totalLines);
        usefulWidth -= mLocation.getRight()+lp.rightMargin-mLocation.getLeft();
        usedWidth += mLocation.getRight()+lp.rightMargin-mLocation.getLeft()+lp.leftMargin;
        left += usedWidth;
        lineMaxHeight = (mLocation.getBottom()-mLocation.getTop()+lp.bottomMargin+lp.topMargin)>=lineMaxHeight?mLocation.getBottom()-mLocation.getTop()+lp.bottomMargin+lp.topMargin:lineMaxHeight;
        viewLocationList.add(mLocation);
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = getChildCount();
        for(int i = 0;i<childCount;i++){
            View v = getChildAt(i);
            v.layout(viewLocationList.get(i).getLeft(),viewLocationList.get(i).getTop(),viewLocationList.get(i).getRight(),viewLocationList.get(i).getBottom());
        }

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
