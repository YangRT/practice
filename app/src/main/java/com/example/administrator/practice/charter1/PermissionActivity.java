package com.example.administrator.practice.charter1;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.administrator.practice.R;

public class PermissionActivity extends AppCompatActivity {
    private Button call;
    private static final int PERMISSION_REQUEST_CALL_PHONE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);
        call = findViewById(R.id.charter1_call);
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                call();
            }
        });
    }

    private void call(){
        if(ActivityCompat.checkSelfPermission(this,Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CALL_PHONE},PERMISSION_REQUEST_CALL_PHONE);
        }else{
            callPhone();
        }
    }

    public void callPhone(){
        Intent intent = new Intent(Intent.ACTION_CALL);
        Uri data = Uri.parse("tel:"+"10086");
        intent.setData(data);
        try{
            startActivity(intent);
        }catch (SecurityException e){
            e.printStackTrace();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == PERMISSION_REQUEST_CALL_PHONE){
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                callPhone();
            }else {
                if(!ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.CALL_PHONE)){
                    AlertDialog dialog = new AlertDialog.Builder(this)
                                                         .setMessage("不开启无法正常工作！")
                                                         .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                                             @Override
                                                             public void onClick(DialogInterface dialog, int which) {

                                                             }
                                                         }).create();
                    dialog.show();
                }
                return;
            }
            super.onRequestPermissionsResult(requestCode,permissions,grantResults);
        }
    }
}
