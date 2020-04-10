package com.example.administrator.practice.customview.multiformrecyclerview;

public interface IGridItem {
    //是否使用分割线
    boolean isShow();

    // 类型
    String getTag();

    //所占权重
    int getSpanSize();
}
