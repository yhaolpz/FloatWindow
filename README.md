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

7.可灵活选择其他方案，如：所有版本都申请权限 等

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
	        compile 'com.github.yhaolpz:FixedFloatWindow:1.0.3'
	}
```

使用：
===

```java

    FixedFloatWindow FFWindow = new FixedFloatWindow(getApplicationContext());
    FFWindow.setView(view);
    FFWindow.setGravity(Gravity.RIGHT | Gravity.TOP, 100, 150);

    FFWindow.show();
//   FFWindow.hide();
//   FFWindow.dismiss();
```

效果：
===

![悬浮按钮图](https://raw.githubusercontent.com/yhaolpz/FixedFloatWindow/master/img.gif)


如果要选择其他方案，比如所有版本都申请权限，且在内部自动申请，只需在实例化时选择对应类型即可：

```java

    FixedFloatWindow FFWindow = new FixedFloatWindow(
             getApplicationContext(), FixedFloatWindow.ALL_AUTO_REQ );

```

共提供 5 种方案供大家测试、使用:



    1. AUTO_REQ :  默认类型，7.1 以下采用自定义 toast 方式跳过申请权限，7.1 及以上内部自动申请权限 ， 推荐

    2. ME_REQ :  7.1 以下采用自定义 toast 方式跳过权限申请；7.1 及以上需用户自己申请权限，否则报错

    3. ALL_AUTO_REQ : 所有版本都申请权限，内部自动申请

    4. ALL_ME_REQ :  所有版本都申请权限，需要用户自己申请权限，否则报错

    5. NO_REQ :  所有版本都采用自定义 toast 方式跳过权限申请，不兼容 7.1 及以上版本，不推荐



方案历程：
===

起初我的需求是：在app内指定界面实现悬浮控件，控件可隐藏，不需改变位置，尽量不需申请权限

然后开始各种搜索探究，总结为以下几种方案：


方案1：  申请权限

       优点：只要正确引导用户打开权限即可
       缺点：小米默认禁用; 权限对用户不友好


方案2：  每个界面添加，共享元素过渡

       优点：不需权限
       缺点：较复杂，只适用于5.0以上，且悬浮控件不可隐藏（共享元素会闪显控件）


方案3：  TYPE_TOAST

       优点：部分机型不需权限，
       缺点：小米(MIUI8)、7.1.1需要权限，4.4以下无法接受点击事件


方案4：自定义 toast

      优点：不需权限，Nexus6.0/7.0有效,小米(MIUI8)有效
      缺点：Nexus7.1.1及以上不显示,4.4以下无法接受点击事件，小米(MIUI8)及部分机型不可改变位置


对比后结合我的需求，最终选择方案为：

    7.0 以下采用自定义 toast, 7.1及以上申请权限

本库中默认方式即为此最终选择方案。












