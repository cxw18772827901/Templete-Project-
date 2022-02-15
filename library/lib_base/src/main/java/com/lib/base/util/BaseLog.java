package com.lib.base.util;

import android.annotation.SuppressLint;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 带开关的展示日志和文件日志
 *
 * @author BaoHang
 * @version 1.0
 * @data 2012-2-20
 */
@SuppressLint("SimpleDateFormat")
public class BaseLog {
    public static String Tag = "main";

//    public native int Log();

    private static final Boolean MYLOG_SWITCH = false;// Config.GD_INFO_LOGS_ENABLED; // 日志文件总开�?
    private static final Boolean MYLOG_WRITE_TO_FILE = false;// 日志写入文件�?��
    @SuppressLint("SdCardPath")
    private static String MYLOG_PATH_SDCARD_DIR = "/sdcard/";// 日志文件在sdcard中的路径
    private static final int SDCARD_LOG_FILE_SAVE_DAYS = 0;// sd卡中日志文件的最多保存天�?
    private static String MYLOGFILEName = "Log.txt";// 本类输出的日志文件名�?
    private static SimpleDateFormat myLogSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");// 日志的输出格�?
    private static SimpleDateFormat logfile = new SimpleDateFormat("yyyy-MM-dd");// 日志文件格式

    public static void w(String tag, Object msg) { // 警告信息
        log(tag, msg.toString(), 'w');
    }

    public static void e(String tag, Object msg) { // 错误信息
        log(tag, msg.toString(), 'e');
    }

    public static void d(String tag, Object msg) {// 调试信息
        log(tag, msg.toString(), 'd');
    }

    public static void i(String tag, Object msg) {//
        log(tag, msg.toString(), 'i');
    }

    public static void v(String tag, Object msg) {
        log(tag, msg.toString(), 'v');
    }

    public static void w(String tag, String text) {
        log(tag, text, 'w');
    }

    public static void e(String tag, String text) {
        log(tag, text, 'e');
    }

    public static void d(String tag, String text) {
        log(tag, text, 'd');
    }

    public static void i(String tag, String text) {
        log(tag, text, 'i');
    }

    public static void v(String tag, String text) {
        log(tag, text, 'v');
    }

    /**
     * 根据tag, msg和等级，输出日志
     *
     * @param tag
     * @param msg
     * @param level
     * @return void
     * @since v 1.0
     */
    private static void log(String tag, String msg, char level) {
        if (MYLOG_SWITCH) {
            if ('e' == level) { // 输出错误信息
                Log.e(tag, msg);
            } else if ('w' == level) {
                Log.w(tag, msg);
            } else if ('d' == level) {
                Log.d(tag, msg);
            } else if ('i' == level) {
                Log.i(tag, msg);
            } else {
                Log.v(tag, msg);
            }
            if (MYLOG_WRITE_TO_FILE)
                writeLogtoFile(String.valueOf(level), tag, msg);
        }
    }

    /**
     * 打开日志文件并写入日�?
     *
     * @return
     **/
    private static void writeLogtoFile(String mylogtype, String tag, String text) {// 新建或打�?��志文�?
        Date nowtime = new Date();
        String needWriteFiel = logfile.format(nowtime);
        String needWriteMessage = myLogSdf.format(nowtime) + "    " + mylogtype
                + "    " + tag + "    " + text;

        File file = new File(MYLOG_PATH_SDCARD_DIR, needWriteFiel
                + MYLOGFILEName);
//			Log.d("main", "file.path="+file.getAbsolutePath()+",,="+file.getPath());
        try {
            FileWriter filerWriter = new FileWriter(file, true);//后面这个参数代表是不是要接上文件中原来的数据，不进行覆盖
            BufferedWriter bufWriter = new BufferedWriter(filerWriter);
            bufWriter.write(needWriteMessage);
            bufWriter.newLine();
            bufWriter.close();
            filerWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static final String DEBUG_TAG_CHANNEL_ID = "channel";

    public static String readCfgFile(String tag) {// 新建或打�?��志文�?
        File file = new File(MYLOG_PATH_SDCARD_DIR, "mpchatset.txt");
        String line = null;
        try {
            FileReader filerReader = new FileReader(file);
            BufferedReader bufReader = new BufferedReader(filerReader);
            line = bufReader.readLine();

            bufReader.close();
            filerReader.close();
        } catch (IOException e) {
            e.printStackTrace();

        }
        return line;
    }

    /**
     * 删除制定的日志文�?
     */
    public static void delFile() {// 删除日志文件
        String needDelFile = logfile.format(getDateBefore());
        File file = new File(MYLOG_PATH_SDCARD_DIR, needDelFile + MYLOGFILEName);
        if (file.exists()) {
            file.deleteOnExit();
        }
    }

    /**
     * 得到现在时间前的几天日期，用来得到需要删除的日志文件�?
     */
    private static Date getDateBefore() {
        Date nowTime = new Date();
        Calendar now = Calendar.getInstance();
        now.setTime(nowTime);
        now.set(Calendar.DATE, now.get(Calendar.DATE)
                - SDCARD_LOG_FILE_SAVE_DAYS);
        return now.getTime();
    }
}
