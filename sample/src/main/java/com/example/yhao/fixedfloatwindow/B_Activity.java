package com.example.yhao.fixedfloatwindow;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.yhao.floatwindow.FloatActivity;
import com.yhao.floatwindow.FloatWindow;

public class B_Activity extends BaseActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b);
        setTitle("B");
    }

    public void change(View view) {
        startActivity(new Intent(this, C_Activity.class));
    }

    public void back(View view) {
        finish();
    }
}
