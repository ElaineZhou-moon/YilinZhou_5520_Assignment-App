package com.example.numad22sp_yilinzhou;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
//    向下兼容的Activity 其中AppCompatActivity是Activity的子类 Activity是Android系统提供的一个活动基类

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Button button_Panel = (Button)findViewById(R.id.buttonPanel);

        button_Panel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Toast.makeText(MainActivity.this,"Yilin Zhou; zhou.yil@northeastern.edu",Toast.LENGTH_SHORT).show();
            }
        });

    }
}
