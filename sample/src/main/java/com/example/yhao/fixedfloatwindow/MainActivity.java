package com.example.yhao.fixedfloatwindow;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {
    MyFloatWindow myFloatWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myFloatWindow = ((BaseApplication) getApplication()).getMyFloatWindow();
    }


    public void change(View view) {
        startActivity(new Intent(this, Main2Activity.class));
    }

    public void show(View view) {
        myFloatWindow.show();
    }

    public void hide(View view) {
        myFloatWindow.hide();

    }

    public void dismiss(View view) {
        myFloatWindow.dismiss();
    }
}
