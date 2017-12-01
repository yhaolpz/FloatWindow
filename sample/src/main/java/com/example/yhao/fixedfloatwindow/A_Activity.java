package com.example.yhao.fixedfloatwindow;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;

import com.example.fixedfloatwindow.FFWindow;
import com.example.fixedfloatwindow.FilterType;

public class A_Activity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a);
        setTitle("A");


        Button button = new Button(getApplicationContext());
        button.setBackgroundColor(ContextCompat.getColor(this, R.color.colorAccent));
        button.setText(" 悬浮按钮 ");

        FFWindow fFWindow = new FFWindow(getApplicationContext());
        fFWindow.setView(button);
        fFWindow.setGravity(Gravity.END | Gravity.TOP, 100, 100);
        fFWindow.setFilter(FilterType.SHOW, A_Activity.class, D_Activity.class);
        fFWindow.show();

    }


    public void change(View view) {
        startActivity(new Intent(this, B_Activity.class));
    }

}
