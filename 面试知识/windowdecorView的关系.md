
# window,activity,decorView,viewroot的关系 #


#### 初探Window ####

相信大部分学习Android第一个学到的都是Activity，如果没有研究一下，很容易会理解成Activity就是一张白纸，然后我们在上面画上各种View。

但其实并不是，因为Android同一时间并不是只绘制一个应用。比如状态栏就不是某个我们开发的应用绘制的。比如我们处于A应用时，能看到B应用的Toast。比如我们随时能看到QQ、微信的消息悬浮窗。比如在Android早些时代，那些各种卫士吹嘘的提速手机用的悬浮球。这些例子都说明，Android需要同一时间绘制多个进程所要显示的UI。

很容易猜出，Android应该有个统一调度，然后绘制各个应用UI的大管家。事实上也的确如此，就是我们的SystemServer进程里面的WindowManagerService这个服务了。如同ActivityManagerService用于管理所有应用的Activity，PackageManagerService用于管理所有应用的信息查询一样。手机屏幕的某一时刻可能同时有多个Window叠放并存着，此时就需要WindowManagerService（简称WMS）管理协调他们。

#### Window的定义和分类 ####

所以Window是个什么东西？

网上一直有个很形象的比喻。Activity就像工匠，Window就像是窗户，View就像是窗花，LayoutInflater像剪刀，Xml配置像窗花图纸。

Window就是用来承载和管理View的。可能刚开始Android设计人员并没有区分出Activity和Window两者，把他们看成一个整体。但是为了独立生命周期，任务栈等（Activity作用） 和 View绘制（Window作用）这些功能降低耦合，把这个整个划分为现在这样了。每个Activity都会持有一个Window，每个Window会持有一个DecorView。

 

根据《Android开发艺术探索》，Window 有三种类型，分别是**应用 Window**、**子 Window** 和**系统 Window**。应用类 Window 对应一个 Acitivity，子 Window 不能单独存在，需要依附在特定的父 Window 中，比如常见的一些 Dialog 就是一个子 Window。系统 Window是需要声明权限才能创建的 Window，比如 Toast 和系统状态栏都是系统 Window。

Window 是分层的，每个 Window 都有对应的 z-ordered，层级大的会覆盖在层级小的 Window 上面，这和 HTML 中的 z-index 概念是完全一致的。在三种 Window 中，应用 Window 层级范围是 1~99，子 Window 层级范围是 1000~1999，系统 Window 层级范围是 2000~2999。


#### 问题 ####

1. window的创建 ->window的创建在activity的
2. window持有decorView（FramLayout）
3. 怎样显示出来
4. window，decorView，RootView，activity的关系

答案

问题1


# Activity的启动过程 #

[Activity的启动过程](https://github.com/jeanboydev/Android-ReadTheFuckingSourceCode/blob/master/article/android/framework/Android-Activity%E5%90%AF%E5%8A%A8%E8%BF%87%E7%A8%8B.md)

#### 问题 ####


1. ActivityThread 是什么，它是一个线程吗，如何被启动的？
2. ActivityClientRecord 与 ActivityRecord 是什么？
3. Context 是什么，ContextImpl，ContextWapper 是什么？
4. Instrumentation 是什么？
5. Application 是什么，什么时候创建的，每个应用程序有几个 Application？
6. 点击 Launcher 启动 Activity 和应用内部启动 Activity 的区别？
7. Activity 启动过程，onCreate()，onResume() 回调时机及具体作用？


