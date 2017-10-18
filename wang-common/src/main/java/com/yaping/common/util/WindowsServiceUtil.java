package com.yaping.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

/**
 * Created by Administrator on 2017/10/14.
 */
public class WindowsServiceUtil {

    public static void startMysqlService() {
        if (OSEntity.isWindows()) {
            Process isAlive = null;
            try {
                Log.info("check windows mysql ServiceImpl is start");
                isAlive = new ProcessBuilder("mysqladmin", "-uroot", "-proot", "ping").start();
                String res = getRes(isAlive);
                Log.info("result={}", res);
                boolean suc = res.indexOf("alive") > -1;
                if (!suc) {
                    try {
                        Log.info("windows mysql ServiceImpl is not start ->run net start mysql start it");
                        Process start = new ProcessBuilder("net", "start", "mysql").start();
                        String res1 = getRes(start);
                        boolean suc1 = res1.indexOf("MySQL 服务已经启动成功") > -1;
                        Log.info("start result={}|status={}", res1, suc1);
                    } catch (IOException e) {
                        Log.error("startMysqlService [start mysql ServiceImpl] error ex={}", ErrorUtil.writer(e));
                    }
                }
            } catch (IOException e) {
                Log.error("startMysqlService [check mysql ServiceImpl] error ex={}", ErrorUtil.writer(e));
            } finally {
                if (isAlive.isAlive()) {
                    isAlive.destroy();
                }
            }
        }
    }

    public static String getRes(Process start) {
        InputStream in = null;
        try {
            byte[] b = new byte[1024];
            int readbytes = -1;
            StringBuffer sb = new StringBuffer();
            //读取进程输出值
            //在JAVA IO中,输入输出是针对JVM而言,读写是针对外部数据源而言
            in = start.getInputStream();
            while ((readbytes = in.read(b)) != -1) {
                sb.append(new String(b, 0, readbytes, "gbk"));
            }
            return sb.toString();
        } catch (IOException e) {
            Log.error("读取流异常");
            return "";
        } finally {
            if (Objects.nonNull(in)) {
                try {
                    in.close();
                } catch (IOException e) {
                    Log.error("关闭流异常");
                }
            }
        }
    }
}
