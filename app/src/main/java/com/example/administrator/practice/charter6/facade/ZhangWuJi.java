package com.example.administrator.practice.charter6.facade;
//外观类：张无忌
public class ZhangWuJi {
    private JingMai jingMai;
    private ZhaoShi zhaoShi;
    private NeiGong neiGong;

    public ZhangWuJi(){
        jingMai = new JingMai();
        zhaoShi = new ZhaoShi();
        neiGong = new NeiGong();
    }

    public void Qiankun(){
        jingMai.jingmai();
        neiGong.QianKun();
    }

    public void QiShang(){
        jingMai.jingmai();
        neiGong.JiuYang();
        zhaoShi.QiShangQuan();
    }
}
