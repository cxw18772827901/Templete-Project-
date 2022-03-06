package com.lib.base.ui.widget.banner;

/**
 * PackageName  com.lib.base.ui.widget.banner
 * ProjectName  TempleteProject-java
 *@author      xwchen
 * Date         2021/12/3.
 */

public interface IShowItem {
    // 将会被显示到viewpager上面
    String getImageUrl();//图片链接

    // 将会被显示到textview上面
    String getTitle();//轮播图的文字

    String getId();//轮播图的类型:1.单一trip;2.行程集合

    String getHrefUrl();//集合行程需要的参数
}
