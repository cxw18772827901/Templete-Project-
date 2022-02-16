package com.lib.base.util.txt.label;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.widget.TextView;

import com.lib.base.R;
import com.lib.base.util.txt.boldSpan.FakeBoldSpan;
import com.lib.base.util.txt.boldSpan.Spanny;
import com.lib.base.util.txt.superSoan.SpanClickListener;
import com.lib.base.util.txt.superSoan.SpanData;
import com.lib.base.util.txt.superSoan.SuperSpanUtil;

import java.util.List;

import androidx.annotation.NonNull;

/**
 * txt工具类
 * ProjectName  XSCat
 * PackageName  com.bigheadhorse.xscat.utils
 * Author       Administrator
 * Date         2021/6/11.
 */
public class TxtUtil {
    private static final int BG_COLOR = R.color.cl_yellow;
    private static final int TV_COLOR = R.color.cl_333333;

    /**
     * 修改多个标签颜色,只支持同一个颜色
     *
     * @param tv
     * @param color
     * @param content
     * @param label
     */
    public static void setMultipleLabelColor(TextView tv, String color, String content, @NonNull String... label) {
        for (String s : label) {
            content = content.replace(s, "<font color='" + color + "'>" + s + "</font>");
        }
        tv.setText(Html.fromHtml(content));
    }

    /**
     * 修改多个标签颜色,只支持同一个颜色
     *
     * @param color
     * @param content
     * @param label
     */
    public static String getText(String color, String content, @NonNull String... label) {
        for (String s : label) {
            content = content.replace(s, "<font color='" + color + "'>" + s + "</font>");
        }
        return content;
    }

    /**
     * 动态调整字体的宽度,默认0.8f
     *
     * @param textView
     * @param text
     */
    public static void setTextBold(@NonNull TextView textView, String text) {
        textView.setText(new Spanny().append(text, new FakeBoldSpan()));
    }

    /**
     * 动态调整字体的宽度
     *
     * @param textView
     * @param text
     * @param size
     */
    public static void setTextBold(@NonNull TextView textView, String text, float size) {
        textView.setText(new Spanny().append(text, new FakeBoldSpan(size)));
    }

    /**
     * 文本开头添加单标签(带背景色圆角)
     *
     * @param context
     * @param tv
     * @param content
     * @param size
     * @param lable1
     */
    public static void addStartLabel(Context context, @NonNull TextView tv, String content, int size, String lable1) {
        SpannableString spanText = new SpannableString(" " + " " + content);
        spanText.setSpan(new VerticalImageSpan(getDrawable(context, size, lable1)), 0, 1, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        tv.setText(spanText);
    }

    /**
     * 文本开头添加双标签(带背景色圆角)
     *
     * @param context
     * @param tv
     * @param content
     * @param size
     * @param lable1
     * @param lable2
     */
    public static void addDoubleStartLabel(Context context, @NonNull TextView tv, String content, int size, String lable1, String lable2) {
        SpannableString spanText = new SpannableString(" " + " " + " " + " " + content);
        spanText.setSpan(new VerticalImageSpan(getDrawable(context, size, lable1)), 0, 1, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        spanText.setSpan(new VerticalImageSpan(getDrawable(context, size, lable2)), 2, 3, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        tv.setText(spanText);
    }

    /**
     * 获取标签Drawable
     *
     * @param context
     * @param size
     * @param lable1
     * @return
     */
    @NonNull
    private static Drawable getDrawable(@NonNull Context context, int size, @NonNull String lable1) {
        Drawable drawable = TextDrawable.builder()
                .beginConfig()
                .width((int) ((lable1.length() - 0.7) * size))
                .height(size)
                .textColor(context.getResources().getColor(TV_COLOR))
                .fontSize((int) (size * 0.6))
                .bold()
                .endConfig()
                .buildRoundRect(lable1, context.getResources().getColor(BG_COLOR), size / 6);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        return drawable;
    }

    /**
     * 超级富文本,可以修改多个标签,包括颜色,字号,粗体,可点击
     *
     * @param tv        TextView
     * @param colorStr  注意以#开头,六位或者八位,不支持三位
     * @param size      标签字号
     * @param clickable 标签可点击
     * @param bold      标签粗体
     * @param contents  文本和标签list
     * @param listener  点击回调
     */
    public static void setSuperLabel(TextView tv, String colorStr, int size, boolean clickable, boolean bold, List<SpanData> contents, SpanClickListener listener) {
        SuperSpanUtil.setSuperLabel(tv, colorStr, size, clickable, bold, contents, listener);
    }

    @NonNull
    @SuppressLint("DefaultLocale")
    public static String getTwoPoint(double value) {
        return String.format("%.2f", value);
    }

    @NonNull
    @SuppressLint("DefaultLocale")
    public static String getTwoPoint(String value) {
        try {
            return String.format("%.2f", Float.parseFloat(value));
        } catch (NumberFormatException e) {
            return "";
        }
    }

    @NonNull
    @SuppressLint("DefaultLocale")
    public static String getPoint(double value) {
        return String.format("%.1f", value);
    }

    @NonNull
    @SuppressLint("DefaultLocale")
    public static String getPoint(String value) {
        try {
            return String.format("%.1f", Float.parseFloat(value));
        } catch (NumberFormatException e) {
            return "";
        }
    }

    @SuppressLint("DefaultLocale")
    public static String getPointNo0(double value) {
        String format = String.format("%.1f", value);
        return format.endsWith(".0") ? format.replace(".0", "") : format;
    }

    @SuppressLint("DefaultLocale")
    public static String getPointNo0(String value) {
        try {
            String format = String.format("%.1f", Float.parseFloat(value));
            return format.endsWith(".0") ? format.replace(".0", "") : format;
        } catch (NumberFormatException e) {
            return "";
        }
    }
}
