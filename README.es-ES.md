# RefreshRecyclerView

Se ha realizado un procesamiento simple del RecyclerView. Al utilizar RefreshRecyclerView + BaseAdapter, se puede implementar la actualización al deslizar hacia abajo (pull-to-refresh) y la carga de más contenido, y además admite la pausa de carga de imágenes durante el desplazamiento para solucionar los problemas de lag al hacer scroll.

Puntos a tener en cuenta:
build.gradle:

dependencies {

    compile fileTree(dir: 'libs', include: ['*.jar'])
    
    testCompile 'junit:junit:4.12'
    
    compile 'com.android.support:appcompat-v7:23.3.0'
    
    compile 'com.android.support:design:23.2.0'
    
}

design:23.2.0 funciona bien, pero design:23.3.0 presenta algunos problemas por razones desconocidas. No se han probado otras versiones, por lo que se recomienda evitar design:23.3.0 ya que tiene bugs.


Capturas de pantalla:

![下拉刷新](https://github.com/736791050/RefreshRecyclerView/blob/master/screen/refresh.jpg)

![加载更多](https://github.com/736791050/RefreshRecyclerView/blob/master/screen/loadmore.jpg)

![滚动不加载](https://github.com/736791050/RefreshRecyclerView/blob/master/screen/loading.jpg)

Bienvenido a dejar comentarios para intercambiar ideas; si tiene mejores sugerencias, por favor hágamelo saber.

Introducción en el blog: http://blog.csdn.net/likuan0214/article/details/51784586
