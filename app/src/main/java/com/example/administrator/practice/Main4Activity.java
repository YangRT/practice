package com.example.administrator.practice;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.administrator.practice.plugin.LoadPluginUtil;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main4Activity extends AppCompatActivity {

    private Button plugin;
    private Button addPlugin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        plugin = findViewById(R.id.plugin);
        addPlugin = findViewById(R.id.add_plugin);
        addPlugin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoadPluginUtil.loadClass(getApplicationContext());
            }
        });
        plugin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                printClassLoader();
                testPlugin();
            }
        });
    }

    private void testPlugin() {
        try {
//            Class<?> clazz = Class.forName("com.enjoy.plugin.Test");
            Class<?> clazz = Class.forName("com.example.wan.PluginTest");
            Method method = clazz.getMethod("print");
            method.invoke(null);
        } catch (ClassNotFoundException e) {
            //Class<?> clazz = Class.forName(" com.rlw.plugin.PluginTest");
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            //Method method = clazz.getMethod("print");
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            //method.invoke(null);
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            //method.invoke(null);
            e.printStackTrace();
        }
    }

    private void printClassLoader(){
        ClassLoader classLoader = getClassLoader(); //pathclassLoader

        while (classLoader != null){
            Log.e("PluginTest","printClassLoader:"+classLoader);
            classLoader = classLoader.getParent(); //bootclassLoader
        }
        //bootclassLoader
        Log.e("PluginTest","printClassLoader:"+ Activity.class.getClassLoader());
        //pathclassLoader
        Log.e("PluginTest","printClassLoader:"+ AppCompatActivity.class.getClassLoader());

    }
}
