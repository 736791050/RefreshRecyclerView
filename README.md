# RefreshRecyclerView

对RecyclerView进行了简单的加工，使用RefreshRecyclerView + BaseAdapter 可以实现下拉刷新及加载更多，并且支持滚动中不加载图片，解决滚动卡顿问题。

注意点：
build.gradle:

dependencies {

    compile fileTree(dir: 'libs', include: ['*.jar'])
    
    testCompile 'junit:junit:4.12'
    
    compile 'com.android.support:appcompat-v7:23.3.0'
    
    compile 'com.android.support:design:23.2.0'
    
}

design:23.2.0可以，但是design:23.3.0就会有一点点问题，原因未知，其它的版本没有测试，所以如果使用最好别用design:23.3.0，有bug。


效果图：

![下拉刷新](https://github.com/736791050/RefreshRecyclerView/blob/master/screen/refresh.jpg)

![加载更多](https://github.com/736791050/RefreshRecyclerView/blob/master/screen/loadmore.jpg)

![滚动不加载](https://github.com/736791050/RefreshRecyclerView/blob/master/screen/loading.jpg)

欢迎留言交流，如果有更好的建议，请多多指教。

博客介绍：http://blog.csdn.net/likuan0214/article/details/51784586
