package com.lib.base.util.inject;

import android.app.Activity;
import android.util.Log;
import android.view.View;

import java.lang.reflect.Field;

public class ViewInjectUtil {
    public static final String TAG="ViewInjectUtil";
    public static void initInActivity(Activity activity) {
        try {
            Field[] fields = activity.getClass().getDeclaredFields();
            if (fields.length > 0) {
                for (Field field : fields) {
                    Log.d(TAG,"field name="+field.getName());
                    ViewInject viewInject = field.getAnnotation(ViewInject.class);//返回ViewInject中声明的所有注解
                    if (null != viewInject) {
                        int viewId = viewInject.id();
                        field.setAccessible(true);//暴力反射private
                        View viewById = activity.findViewById(viewId);
                        field.set(activity, viewById);
                        boolean clickMethod = viewInject.needClick();
                        if (clickMethod) {
                            viewById.setOnClickListener((View.OnClickListener) activity);
                        }
                    }
                }
            }
        } catch (Exception e) {
            throw new MyException("反射注解失败");
        }
    }

    public static void initInView(View view) {
        try {
            Field[] fields = view.getClass().getDeclaredFields();
            if (fields.length > 0) {
                for (Field field : fields) {
                    Log.d(TAG,"field name="+field.getName());
                    ViewInject viewInject = field.getAnnotation(ViewInject.class);//返回ViewInject中声明的所有注解
                    if (null != viewInject) {
                        int viewId = viewInject.id();
                        field.setAccessible(true);//暴力反射private
                        View viewById = view.findViewById(viewId);
                        field.set(view, viewById);
                        boolean clickMethod = viewInject.needClick();
                        if (clickMethod) {
                            viewById.setOnClickListener((View.OnClickListener) view);
                        }
                    }
                }
            }
        } catch (Exception e) {
            throw new MyException("反射注解失败");
        }
    }

    public static void initNotInActivity(Object o, View convertView) {
        try {
            Field[] fields = o.getClass().getDeclaredFields();
            if (fields.length > 0) {
                for (Field field : fields) {
                    Log.d(TAG,"field name="+field.getName());
                    ViewInject viewInject = field.getAnnotation(ViewInject.class);//返回ViewInject中声明的所有注解
                    if (null != viewInject) {
                        int viewId = viewInject.id();
                        field.setAccessible(true);//暴力反射private
                        View viewById = convertView.findViewById(viewId);
                        field.set(o, viewById);
                        boolean clickMethod = viewInject.needClick();
                        if (clickMethod) {
                            viewById.setOnClickListener((View.OnClickListener) o);
                        }
                    }
                }
            }
        } catch (Exception e) {
            throw new MyException("反射注解失败");
        }
    }
}
