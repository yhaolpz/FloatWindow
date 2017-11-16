package com.example.yhao.fixedfloatwindow;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.fixedfloatwindow.FixedFloatWindow;

public class MainActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((BaseApplication) getApplication()).getMyFloatWindow().show();



    }


    public void change(View view) {
        startActivity(new Intent(this, Main2Activity.class));
    }
}
