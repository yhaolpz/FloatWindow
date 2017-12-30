package com.example.yhao.floatwindow;

import android.app.Application;
import android.os.Build;
import android.view.animation.BounceInterpolator;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.yhao.fixedfloatwindow.R;
import com.yhao.floatwindow.FloatWindow;
import com.yhao.floatwindow.MoveType;
import com.yhao.floatwindow.Screen;

/**
 * Created by yhao on 2017/12/18.
 * https://github.com/yhaolpz
 */

public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        ImageView imageView = new ImageView(getApplicationContext());
        imageView.setImageResource(R.drawable.icon);


        //效果图1
        FloatWindow
                .with(getApplicationContext())
                .setView(imageView)
                .setWidth(Screen.width,0.2f)
                .setHeight(Screen.width,0.2f)
                .setX(Screen.width,0.8f)
                .setY(Screen.height,0.3f)
                .setMoveType(MoveType.slide)
                .setMoveStyle(500,new BounceInterpolator())
                .setDesktopShow(true)
                .build();

        ImageView imageView2 = new ImageView(getApplicationContext());
        imageView2.setImageResource(R.mipmap.ic_launcher_round);

//      效果图2
        FloatWindow
                .with(getApplicationContext())
                .setView(imageView2)
                .setWidth(Screen.width,0.2f)
                .setHeight(Screen.width,0.2f)
                .setX(Screen.width,0.7f)
                .setY(Screen.height,0.02f)
                .setTag("second")
                .setMoveType(MoveType.inactive)
                .setFilter(true,B_Activity.class,C_Activity.class)
                .build();
    }
}
