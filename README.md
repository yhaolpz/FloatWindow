# FloatWindow 安卓任意界面悬浮窗
[![](https://jitpack.io/v/PBK-B/FloatWindow.svg)](https://jitpack.io/#PBK-B/FloatWindow)


![悬浮按钮图](https://raw.githubusercontent.com/yhaolpz/FixedFloatWindow/master/slide.gif)

特性：
===

1.支持拖动，提供自动贴边等动画

2.内部自动进行权限申请操作

3.可自由指定要显示悬浮窗的界面

4.应用退到后台时，悬浮窗会自动隐藏

5.除小米外，4.4~7.0 无需权限申请

6.位置及宽高可设置百分比值，轻松适配各分辨率

7.支持权限申请结果、位置等状态监听

8.链式调用，简洁清爽


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
	implementation 'com.github.PBK-B:FloatWindow:1.0.9'
}
```

使用：
===

**0.声明权限**

```java

     <uses-permission android:name="android.permission.SYSTEM_ALERT_WINDOW" />

```


**1.基础使用**

```java
        FloatWindow
              .with(getApplicationContext())
              .setView(view)
              .setWidth(100)                               //设置控件宽高
              .setHeight(Screen.width,0.2f)
              .setX(100)                                   //设置控件初始位置
              .setY(Screen.height,0.3f)
              .setDesktopShow(true)                        //桌面显示
              .setViewStateListener(mViewStateListener)    //监听悬浮控件状态改变
              .setPermissionListener(mPermissionListener)  //监听权限申请结果
              .build();

```

宽高及位置可设像素值或屏幕宽/高百分比，默认宽高为 wrap_content；默认位置为屏幕左上角，x、y 为偏移量。


**2.指定界面显示**

```java
              .setFilter(true, A_Activity.class, C_Activity.class)

```
此方法表示 A_Activity、C_Activity 显示悬浮窗，其他界面隐藏。

```java
              .setFilter(false, B_Activity.class)
```
此方法表示 B_Activity 隐藏悬浮窗，其他界面显示。

注意：setFilter 方法参数可以识别该 Activity 的子类

也就是说，如果 A_Activity、C_Activity 继承自 BaseActivity，你可以这样设置：

```java
              .setFilter(true, BaseActivity.class)
```


**3.可拖动悬浮窗及回弹动画**

```java
              .setMoveType(MoveType.slide)
              .setMoveStyle(500, new AccelerateInterpolator())  //贴边动画时长为500ms，加速插值器

```

共提供 4 种 MoveType :

MoveType.slide       : 可拖动，释放后自动贴边 （默认）

MoveType.back        : 可拖动，释放后自动回到原位置

MoveType.active      : 可拖动

MoveType.inactive    : 不可拖动


setMoveStyle 方法可设置动画效果，只在 MoveType.slide 或 MoveType.back 模式下设置此项才有意义。默认减速插值器，默认动画时长为 300ms。


**4.后续操作**

```java
        //手动控制
        FloatWindow.get().show();
        FloatWindow.get().hide();

        //修改显示位置
        FloatWindow.get().updateX(100);
        FloatWindow.get().updateY(100);

        //销毁
        FloatWindow.destroy();

```

以上操作应待悬浮窗初始化后进行。


**5.多个悬浮窗**

```java

        FloatWindow
                .with(getApplicationContext())
                .setView(imageView)
                .build();

        FloatWindow
                .with(getApplicationContext())
                .setView(button)
                .setTag("new")
                .build();


        FloatWindow.get("new").show();
        FloatWindow.get("new").hide();
        FloatWindow.destroy("new");

```

创建第一个悬浮窗不需加 tag，之后再创建就需指定唯一 tag ，以此区分，方便进行后续操作。


举个栗子
===

点击查看 : [示例代码](https://github.com/yhaolpz/FloatWindow/blob/master/sample/src/main/java/com/example/yhao/floatwindow/BaseApplication.java) 。

最后：
--
![悬浮按钮图](https://raw.githubusercontent.com/yhaolpz/FixedFloatWindow/master/pay.jpg)

本人已尽量去兼容更多机型，但经济有限，如果你想帮助此库，提 Issues 标出当前版本不适配的机型即可，感谢~

### 新增功能
1, setFlags (可修复悬浮窗 TextEdit 不可弹出输入法) 
```java
// 使悬浮窗获取焦点
FloatWindow.get().updateFlags(WindowManager.LayoutParams.FLAG_LAYOUT_INSET_DECOR);

// 使悬浮窗失去焦点
FloatWindow.get().updateFlags(WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE);

```

**更新日志**
--

**v1.0.9**

 修复拖动点击事件冲突

 添加权限结果监听、位置等状态监听

 支持贴边边距设置


**v1.0.8**

 适配 4.4~8.0 及各大国产机型

 支持桌面显示


**v1.0.7**

 适配 Android 8.0


**v1.0.6**

 支持悬浮窗拖动及相关动效

 位置及宽高可设置百分比值

 更改相关类名及使用方法


**v1.0.5**

 修复未调用show显示悬浮窗bug


**v1.0.4**

 返回桌面将会自动隐藏控件，无需再监听应用退到后台等操作

 新增 Activity 过滤器，可自由指定哪些界面显示，哪些界面不显示

 FixedFloatWindow 类改为 FFWindow


**v1.0.3**

 修复已知 bug

 新增 dismiss 方法

 新增其他方案，如:所有版本都申请权限


















