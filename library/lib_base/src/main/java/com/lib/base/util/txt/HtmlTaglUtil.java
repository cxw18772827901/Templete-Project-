package com.lib.base.util.txt;

import android.graphics.Color;
import android.graphics.Typeface;
import android.text.Editable;
import android.text.Html;
import android.text.Spannable;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.widget.TextView;

import com.lib.base.util.DebugUtil;
import com.lib.base.util.OUtil;

import org.xml.sax.XMLReader;

import java.lang.reflect.Field;
import java.util.HashMap;

/**
 * HtmlTagHandler.setText(mViewBinding.tv1, "#ff0000", "50px", true, "123我的321", "我的");
 * ProjectName  xuanshangmao
 * PackageName  com.xinren.four.utils
 * Author       Administrator
 * Date         2021/11/15.
 */

public class HtmlTaglUtil implements Html.TagHandler {
    public static final String TAG = "HtmlUtil";
    // 自定义标签名称
    private final String tagName;
    private final int labelLength;
    // 标签开始索引
    private int startIndex = 0;
    // 存放标签全部属性键值对
    final HashMap<String, String> attributes = new HashMap<>();

    public HtmlTaglUtil(String tagName, int labelLength) {
        this.tagName = tagName;
        this.labelLength = labelLength;
    }

    /**
     * 简易使用样例,自动处理一个标签粗体,颜色,字体大小属性;多个标签不要用这个方法,自己手动拼吧
     * String text = "行首<myfont color='red' size='50px' bold='true'>" + "中间要显示的数据" + "</myfont>行尾";
     * Spanned spanned = Html.fromHtml(text, null, new HtmlTagHandler("myfont"));
     * mViewBinding.tv.setText(spanned);
     *
     * @param tv      TextView
     * @param color   色值,#开头,6位或8位
     * @param size    字体大小,px
     * @param bold    是否粗体
     * @param content 整个文本
     * @param label   标签部分文本
     */
    public static void setText(TextView tv, String color, String size, boolean bold, String content, String label) {
        if (OUtil.isNull(content)) {
            tv.setText("");
            return;
        }
        if (OUtil.isNull(label) || !content.contains(label)) {
            tv.setText(content);
            return;
        }
        String[] split = content.split(label);
        if (split.length == 0) {
            tv.setText(content);
            return;
        }
        String start = content.split(label)[0];
        String end = "";
        if (split.length == 2) {
            end = content.split(label)[1];
        }
        StringBuilder sb = new StringBuilder();
        sb.append(start)
                .append("<tag");
        if (OUtil.isNotNull(color)) {
            sb.append(" color='").append(color).append("'");
        }
        if (OUtil.isNotNull(size)) {
            sb.append(" size='").append(size).append("'");
        }
        if (bold) {
            sb.append(" bold='").append("true").append("'");
        }
        sb.append(">")
                .append(label)
                .append("</tag>")
                .append(end);
        String s = sb.toString();
        DebugUtil.logD(TAG, "s=" + s);
        tv.setText(Html.fromHtml(s, null, new HtmlTaglUtil("tag", label.length())));
    }

    @Override
    public void handleTag(boolean opening, String tag, Editable output, XMLReader xmlReader) {
        // 判断是不是当前须要的tag
        if (tag.equalsIgnoreCase(tagName)) {
            // 解析全部属性值
            parseAttributes(xmlReader);
            if (opening) {
                startHandleTag(tag, output, xmlReader);
            } else {
                endEndHandleTag(tag, output, xmlReader);
            }
        }
    }

    public void startHandleTag(String tag, Editable output, XMLReader xmlReader) {
        startIndex = output.length();
    }

    public void endEndHandleTag(String tag, Editable output, XMLReader xmlReader) {
        // 标签结束索引
        int endIndex = startIndex + labelLength;// output.length();

        // 获取对应的属性值
        String color = attributes.get("color");
        String size = attributes.get("size");
        String bold = attributes.get("bold");
        if (size != null) {
            size = size.split("px")[0];
        }

        // 设置颜色
        if (!TextUtils.isEmpty(color)) {
            output.setSpan(new ForegroundColorSpan(Color.parseColor(color)), startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        // 设置字体大小
        if (!TextUtils.isEmpty(size)) {
            output.setSpan(new AbsoluteSizeSpan(Integer.parseInt(OUtil.nonNull(size))), startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        //粗体
        if (!TextUtils.isEmpty(bold) && Boolean.parseBoolean(bold)) {
            output.setSpan(new StyleSpan(Typeface.BOLD), startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
//        DebugUtil.logD(TAG, "output=" + output);
    }

    /**
     * 解析全部属性值 * * @param xmlReader
     */
    private void parseAttributes(final XMLReader xmlReader) {
        try {
            Field elementField = xmlReader.getClass().getDeclaredField("theNewElement");
            elementField.setAccessible(true);
            Object element = elementField.get(xmlReader);
            if (element == null) {
                return;
            } else {
                element.getClass();
            }
            Field attsField = element.getClass().getDeclaredField("theAtts");
            attsField.setAccessible(true);
            Object atts = attsField.get(element);
            if (atts == null) {
                return;
            } else {
                atts.getClass();
            }
            Field dataField = atts.getClass().getDeclaredField("data");
            dataField.setAccessible(true);
            String[] data = (String[]) dataField.get(atts);
            if (data == null || data.length == 0) {
                return;
            }
            Field lengthField = atts.getClass().getDeclaredField("length");
            lengthField.setAccessible(true);
            int len = (Integer) lengthField.get(atts);
            for (int i = 0; i < len; i++) {
                attributes.put(data[i * 5 + 1], data[i * 5 + 4]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}