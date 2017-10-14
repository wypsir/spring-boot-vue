package com.yaping.common.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

public class ErrorUtil {

    /**
     * 输出堆栈信息到日志
     */
    public static StringWriter writer(Exception e) {
        StringWriter sw = null;
        PrintWriter pw = null;
        try {
            sw = new StringWriter();
            pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            pw.flush();
            sw.flush();
        } finally {
            if (sw != null) {
                try {
                    sw.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (pw != null) {
                pw.close();
            }
        }
        return sw;
    }
}