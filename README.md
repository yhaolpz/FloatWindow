# FixedFloatWindow
Andorid 任意界面悬浮窗，适配 4.x~7.1 及各大国产机型，无需申请权限


特性：
===

1.可在应用内任意界面及桌面显示

2.Andorid 7.1 版本以下无需申请权限

3.适配各大国产机型

4.内部封装权限申请操作

5.简易、轻量

6.位置不可变、Andorid 4.4 以下无法接收触摸事件


集成：
===

第 1 步、在工程的 build.gradle 中添加：

```
	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```
第 2 步、在应用的  build.gradle 中添加：

```
	dependencies {
	        compile 'com.github.yhaolpz:FixedFloatWindow:1.0.2'
	}
```

使用：
===

```java

    Button button = new Button(getApplicationContext());
    button.setText("悬浮按钮");
    button.setBackgroundColor(getResources().getColor(R.color.colorAccent));


    FixedFloatWindow fixedFloatWindow = new FixedFloatWindow(getApplicationContext());
    fixedFloatWindow.setView(button);
    fixedFloatWindow.setGravity(Gravity.RIGHT | Gravity.TOP, 100, 150);
    fixedFloatWindow.show();
//   fixedFloatWindow.hide();
```


效果：
===

![悬浮按钮图](https://raw.githubusercontent.com/yhaolpz/FixedFloatWindow/master/img.jpg)