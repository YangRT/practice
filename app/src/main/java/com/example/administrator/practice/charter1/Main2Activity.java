package com.example.administrator.practice.charter1;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.administrator.practice.R;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private HomeAdapter mHomeAdapter;
    private DividerItemDecoration mItemDecoration;
    private List<String> mList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        init();
    }

    private void init(){
        initData();
        mRecyclerView = findViewById(R.id.charter1_recyclerview);
        mHomeAdapter = new HomeAdapter(mList,this);
        mHomeAdapter.setItemClickListener(new HomeAdapter.onItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(Main2Activity.this,mList.get(position),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, final int position) {
                new AlertDialog.Builder(Main2Activity.this)
                               .setTitle("确认删除吗？")
                               .setNegativeButton("取消",null)
                               .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                                   @Override
                                   public void onClick(DialogInterface dialog, int which) {
                                       mHomeAdapter.removeData(position);
                                       mList.remove(position);
                                   }
                               }).show();
            }
        });
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mHomeAdapter);
        mItemDecoration = new DividerItemDecoration(this,LinearLayoutManager.VERTICAL);
        mRecyclerView.addItemDecoration(mItemDecoration);

    }

    private void initData(){
        for(int i = 0;i < 20;i++){
            mList.add("Hello");
            mList.add("Bye!");
        }
    }
}
