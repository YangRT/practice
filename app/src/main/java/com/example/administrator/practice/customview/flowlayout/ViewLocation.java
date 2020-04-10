package com.example.administrator.practice.customview.flowlayout;
//存储FlowLayout子view位置信息
public class ViewLocation {
    private int left;
    private int right;
    private int top;
    private int myLine;

    public int getMyLine() {
        return myLine;
    }

    public void setMyLine(int myLine) {
        this.myLine = myLine;
    }



    public int getLeft() {
        return left;
    }

    public void setLeft(int left) {
        this.left = left;
    }

    public int getRight() {
        return right;
    }

    public void setRight(int right) {
        this.right = right;
    }

    public int getTop() {
        return top;
    }

    public void setTop(int top) {
        this.top = top;
    }

    public int getBottom() {
        return bottom;
    }

    public void setBottom(int bottom) {
        this.bottom = bottom;
    }

    private int bottom;
}
