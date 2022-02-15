package com.lib.base.util;

import android.app.ActivityManager;
import android.content.Context;
import android.os.Process;
import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * 判断当前进程是不是主进程
 * ProjectName  templete-project
 * PackageName  com.lib.base.util
 * Author       Administrator
 * Date         2021/11/26.
 */

public class ProcessUtil {
    public static boolean isMainProcess(Context paramContext) {
        if (paramContext == null) {
            return false;
        }
        String str1 = paramContext.getApplicationContext().getPackageName();
        String str2 = getProcessName(paramContext);
        return str1.equals(str2);
    }

    private static String getProcessName(Context paramContext) {
        String str = getProcessFromFile();
        if (str == null) {
            str = getProcessNameByAM(paramContext);
        }
        return str;
    }

    private static String getProcessFromFile() {
        BufferedReader bufferedReader = null;
        FileInputStream fileInputStream = null;
        try {
            int i = Process.myPid();
            String str = "/proc/" + i + "/cmdline";
            fileInputStream = new FileInputStream(str);
            bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream, "iso-8859-1"));
            StringBuilder stringBuilder = new StringBuilder();
            int j;
            while ((j = bufferedReader.read()) > 0) {
                stringBuilder.append((char) j);
            }
            return stringBuilder.toString();
        } catch (Exception exception) {
            return null;
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
            } catch (IOException iOException) {
                iOException.printStackTrace();
            }
        }
    }

    /**
     * 网易云捕官方写法
     * @param paramContext
     * @return
     */
    private static String getProcessNameByAM(Context paramContext) {
        ActivityManager activityManager = (ActivityManager) paramContext.getSystemService(Context.ACTIVITY_SERVICE);
        if (activityManager == null) {
            return null;
        }
        while (true) {
            String str = null;
            List<ActivityManager.RunningAppProcessInfo> list = activityManager.getRunningAppProcesses();
            if (list != null) {
                for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : list) {
                    if (runningAppProcessInfo.pid == Process.myPid()) {
                        str = runningAppProcessInfo.processName;
                        break;
                    }
                }
            }
            if (!TextUtils.isEmpty(str)) {
                return str;
            } else {
                try {
                    Thread.sleep(100L);
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
            }
        }
    }

    //可能或获取不到
    /*private static String getProcessNameByAM(Context paramContext) {
        ActivityManager activityManager = (ActivityManager) paramContext.getSystemService(Context.ACTIVITY_SERVICE);
        if (activityManager == null) {
            return null;
        }
        String str = null;
        List<ActivityManager.RunningAppProcessInfo> list = activityManager.getRunningAppProcesses();
        if (list != null && list.size() > 0) {
            for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : list) {
                if (runningAppProcessInfo.pid == Process.myPid()) {
                    str = runningAppProcessInfo.processName;
                    break;
                }
            }
        }
        return str;
    }*/
}
