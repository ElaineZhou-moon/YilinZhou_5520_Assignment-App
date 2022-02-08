package com.example.numad22sp_yilinzhou;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Clicky extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clicky);

        Button A = (Button)findViewById(R.id.buttonA);
        A.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                TextView disp = (TextView)findViewById(R.id.textView2);
                disp.setText("Pressed: A");
            }
        });

        Button B = (Button)findViewById(R.id.buttonB);
        B.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                TextView disp = (TextView)findViewById(R.id.textView2);
                disp.setText("Pressed: B");
            }
        });

        Button C = (Button)findViewById(R.id.buttonC);
        C.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                TextView disp = (TextView)findViewById(R.id.textView2);
                disp.setText("Pressed: C");
            }
        });

        Button D = (Button)findViewById(R.id.buttonD);
        D.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                TextView disp = (TextView)findViewById(R.id.textView2);
                disp.setText("Pressed: D");
            }
        });

        Button E = (Button)findViewById(R.id.buttonE);
        E.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                TextView disp = (TextView)findViewById(R.id.textView2);
                disp.setText("Pressed: E");
            }
        });

        Button F = (Button)findViewById(R.id.buttonF);
        F.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                TextView disp = (TextView)findViewById(R.id.textView2);
                disp.setText("Pressed: F");
            }
        });
    }






}