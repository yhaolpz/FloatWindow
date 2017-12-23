package com.example.yhao.floatwindow;

import android.os.Bundle;
import android.view.View;

import com.example.yhao.fixedfloatwindow.R;

public class C_Activity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c);
        setTitle("C");

    }

    public void back(View view) {
        finish();
    }
}
