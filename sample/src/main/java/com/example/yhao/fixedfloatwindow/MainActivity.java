package com.example.yhao.fixedfloatwindow;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.widget.Button;

import com.example.fixedfloatwindow.FixedFloatWindow;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = new Button(getApplicationContext());
        button.setText("悬浮按钮");
        button.setBackgroundColor(getResources().getColor(R.color.colorAccent));


        FixedFloatWindow fixedFloatWindow = new FixedFloatWindow(getApplicationContext());
        fixedFloatWindow.setView(button);
        fixedFloatWindow.setGravity(Gravity.RIGHT | Gravity.TOP, 100, 150);
        fixedFloatWindow.show();
//        fixedFloatWindow.hide();
    }



}
