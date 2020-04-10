package com.example.administrator.practice;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;

import com.example.administrator.practice.customview.multiformrecyclerview.GridItemDecoration;
import com.example.administrator.practice.customview.multiformrecyclerview.IGridItem;

import java.util.List;

public class MainActivity extends AppCompatActivity  {

    RecyclerView recyclerView;
    private List<IGridItem> values;
    private GridItemDecoration itemDecoration;
    private Button plugin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e("ss","ss");


    }




}
