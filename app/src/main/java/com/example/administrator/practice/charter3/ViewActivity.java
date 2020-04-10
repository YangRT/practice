package com.example.administrator.practice.charter3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.administrator.practice.R;

public class ViewActivity extends AppCompatActivity {
    private ListView one;
    private ListView two;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        one = findViewById(R.id.charter3_list_view_one);
        two = findViewById(R.id.charter3_list_view_two);
        String[] str1 = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15"};
        String[] str2 = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O"};
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,str1);
        one.setAdapter(adapter1);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,str2);
        two.setAdapter(adapter2);
    }
}
