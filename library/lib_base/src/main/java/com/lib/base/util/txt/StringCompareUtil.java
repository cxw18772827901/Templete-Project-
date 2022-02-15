package com.lib.base.util.txt;

import android.text.TextUtils;

/**
 * 比较两个字符串相似度工具类
 */
public class StringCompareUtil {

    /**
     * 字符串相似度匹配，范围在[0~1.0]
     *
     * @param s1 字符串1
     * @param s2 字符串2
     * @return 返回相似度
     */
    public static double compare(String s1, String s2, boolean needTrim) {
        double compare;
        try {
            if (isEmpty(s1) && isEmpty(s2)) {
                compare = 1;
            } else if ((isEmpty(s1) && !isEmpty(s2)) || (!isEmpty(s1) && isEmpty(s2))) {
                compare = 0;
            } else {
                if (needTrim) {
                    s1 = s1.trim();
                    s2 = s2.trim();
                }
                int n = s1.length();
                int m = s2.length();
                compare = 1.0 - (compare(s1, n, s2, m) / (Math.max(n, m)));
            }
        } catch (Exception e) {
            compare = 0;
        }
        return compare;
    }

    private static boolean isEmpty(String str) {
        return TextUtils.isEmpty(str);
    }

    private static double compare(String s1, int n, String s2, int m) {
        int[][] matrix = new int[n + 1][m + 1];
        for (int i = 0; i <= n; i++) {
            matrix[i][0] = i;
        }
        for (int i = 0; i <= m; i++) {
            matrix[0][i] = i;
        }
        for (int i = 1; i <= n; i++) {
            int s1i = s1.codePointAt(i - 1);
            for (int j = 1; j <= m; j++) {
                int s2j = s2.codePointAt(j - 1);
                int cost = s1i == s2j ? 0 : 1;
                matrix[i][j] = min3(matrix[i - 1][j] + 1, matrix[i][j - 1] + 1, matrix[i - 1][j - 1] + cost);
            }
        }
        return matrix[n][m];
    }

    private static int min3(int a, int b, int c) {
        return Math.min(Math.min(a, b), c);
    }

}
