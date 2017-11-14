package com.example.yhao.fixedfloatwindow;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.fixedfloatwindow.FixedFloatWindow;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final FixedFloatWindow fixedFloatWindow = new FixedFloatWindow(getApplicationContext());
        Button button = new Button(getApplicationContext());
        button.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "onClick", Toast.LENGTH_SHORT).show();
                fixedFloatWindow.hide();
            }
        });

        fixedFloatWindow.setView(button);
        fixedFloatWindow.setGravity(Gravity.RIGHT | Gravity.TOP, 100, 150);
        fixedFloatWindow.show();
    }
}
