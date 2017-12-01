package com.example.fixedfloatwindow;

import android.view.View;

/**
 * Created by yhao on 17-11-14.
 *
 */

interface FixedFloatView {

    void setView(View view, int width, int height);

    void setView(View view);

    void setGravity(int gravity, int xOffset, int yOffset);

    void show();

    void dismiss();
}
