package com.lib.base.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * ProjectName  TempleteProject-java
 * PackageName  com.lib.base.util
 *@author      xwchen
 * Date         2022/1/26.
 */

public class Arrays {
    /**
     * 系统的Arrays.asList返回的list实际上是数组,不能修改长度
     *
     * @param a
     * @param <T>
     * @return
     */
    @SafeVarargs
    public static <T> List<T> asList(T... a) {
        List<T> list = new ArrayList<>();
        Collections.addAll(list, a);
        return list;
    }
}
