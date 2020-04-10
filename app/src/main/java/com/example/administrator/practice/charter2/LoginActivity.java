package com.example.administrator.practice.charter2;


import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.administrator.practice.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {
    TextInputLayout tl_username;
    TextInputLayout tl_password;
    Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        tl_username = findViewById(R.id.tl_name);
        tl_password = findViewById(R.id.tl_password);
        login = findViewById(R.id.bt_login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    private boolean validatePaaword(String password){
        return password.length() > 6;
    }

    private static final String EMAL_PATTERN = "^[a-zA-Z0-9#_~!$&'()*+,;=:.<>@\\[\\]\\\\]+@[a-zA-Z0-9]+(\\.[a-zA-z0-9-]+)*$";
    private Pattern pattern = Pattern.compile(EMAL_PATTERN);

    private boolean validateUserName(String name){
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }
    private void login(){
        String name = tl_username.getEditText().getText().toString();
        String password = tl_password.getEditText().getText().toString();
        if(!validateUserName(name)){
            tl_username.setEnabled(true);
            tl_username.setError("请输入正确邮箱地址");
        }else if(!validatePaaword(password)){
            tl_password.setEnabled(true);
            tl_password.setError("密码字数过少");
        }else{
            tl_username.setEnabled(false);
            tl_password.setEnabled(false);
            Toast.makeText(this,"登陆成功！",Toast.LENGTH_SHORT).show();
        }
    }
}
