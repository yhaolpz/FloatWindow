# FloatWindow 安卓任意界面悬浮窗

![悬浮按钮图](https://raw.githubusercontent.com/yhaolpz/FixedFloatWindow/master/slide.gif)

特性：
===

1.支持拖动，提供自动贴边等动画

2.内部自动进行权限申请操作

3.可自由指定要显示悬浮窗的界面

4.应用退到后台时，悬浮窗会自动隐藏

5.位置不可变的悬浮窗无需权限申请

6.位置及宽高可设置百分比值，轻松适配各分辨率

7.链式调用，简洁清爽


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
	        compile 'com.github.yhaolpz:FixedFloatWindow:1.0.6'
	}
```

使用：
===

**1.创建悬浮控件**

```java
        FloatWindow
              .with(getApplicationContext())
              .setView(view)
              .build();

```

setView 方法可设置 View 子类或 xml 布局。

**2.控件宽高**

```java
        FloatWindow
              .with(getApplicationContext())
              .setView(view)
              .setWidth(100)                   //100px
              .setHeight(Screen.width,0.2f)    //屏幕宽度的 20%
              .build();

```

可设置具体数值或屏幕宽/高百分比，默认为 wrap_content。

**3.显示位置**

```java
        FloatWindow
              .with(getApplicationContext())
              .setView(view)
              .setX(100)                      //100px
              .setY(Screen.width,0.2f)        //屏幕宽度的 20%
              .build();

```

可设置具体数值或屏幕宽/高百分比，默认为 0，以屏幕左上角为原点。

**4.指定界面显示**

```java
        FloatWindow
              .with(getApplicationContext())
              .setView(view)
              .setFilter(true, A_Activity.class, C_Activity.class)
              .build();

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

**5.可拖动悬浮窗**

```java
        FloatWindow
              .with(getApplicationContext())
              .setView(view)
              .setMoveType(MoveType.slide)         //可拖动，释放后自动贴边
              .build();

```

共提供 4 种 MoveType :

MoveType.slide   : 可拖动，释放后自动贴边

MoveType.back    : 可拖动，释放后自动回到原位置

MoveType.active  : 可拖动

MoveType.free    : 不可拖动，但可改变位置

如不设置，则为 fixed 模式，不可改变位置。

**6.悬浮窗动画**

```java
        FloatWindow
              .with(getApplicationContext())
              .setView(view)
              .setMoveType(MoveType.slide)
              .setMoveStyle(500, new AccelerateInterpolator())  //贴边动画时长为500ms，加速插值器
              .build();

```

自定义贴边或回到原位置过程的动画效果，只在 MoveType.slide 或 MoveType.back 模式下设置此项才有意义。默认减速插值器，默认动画时长为 300ms。


**7.后续操作**

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

以上操作应待悬浮窗初始化后进行，注意不能对 fixed 模式悬浮窗修改显示位置。


**8.多个悬浮窗**

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


举个栗子：
===

![悬浮按钮图](https://raw.githubusercontent.com/yhaolpz/FixedFloatWindow/master/back.gif)

此效果实现全部代码为下：

```java

        FloatWindow
                .with(getApplicationContext())
                .setView(imageView)
                .setWidth(Screen.width,0.2f)
                .setHeight(Screen.width,0.2f)
                .setX(Screen.width,0.7f)
                .setY(Screen.height,0.2f)
                .setMoveType(MoveType.back)
                .setMoveStyle(300,null)
                .setFilter(true,A_Activity.class,C_Activity.class)
                .build();

```

在 [sample](https://github.com/yhaolpz/FloatWindow/blob/master/sample/src/main/java/com/example/yhao/floatwindow/BaseApplication.java) 中也有示例代码。

**更新日志**
--

**v1.0.6**

1.支持悬浮窗拖动及相关动效

2.位置及宽高可设置百分比值

3.更改相关类名及使用方法


**v1.0.5**

1.修复未调用show显示悬浮窗bug



**v1.0.4**

1.返回桌面将会自动隐藏控件，无需再监听应用退到后台等操作

2.新增 Activity 过滤器，可自由指定哪些界面显示，哪些界面不显示

3.FixedFloatWindow 类改为 FFWindow



**v1.0.3**

1.修复已知 bug

2.新增 dismiss 方法

3.新增其他方案，如:所有版本都申请权限
















