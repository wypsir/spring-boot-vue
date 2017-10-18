package com.yaping.common.util;


import com.yaping.common.enums.EPlatform;

/**
 *
 * @date 2017/9/7 18:54
 * @description
 */
public class OSEntity {
    private static String OSName = System.getProperty("os.name").toLowerCase();

    private static OSEntity _instance = new OSEntity();

    private EPlatform platform;

    private OSEntity(){}

    public static boolean isLinux(){
        return OSName.indexOf("linux")>=0;
    }

    public static boolean isMacOS(){
        return OSName.indexOf("mac")>=0&& OSName.indexOf("os")>0&& OSName.indexOf("x")<0;
    }

    public static boolean isMacOSX(){
        return OSName.indexOf("mac")>=0&& OSName.indexOf("os")>0&& OSName.indexOf("x")>0;
    }

    public static boolean isWindows(){
        return OSName.indexOf("windows")>=0;
    }

    public static boolean isOS2(){
        return OSName.indexOf("os/2")>=0;
    }

    public static boolean isSolaris(){
        return OSName.indexOf("solaris")>=0;
    }

    public static boolean isSunOS(){
        return OSName.indexOf("sunos")>=0;
    }

    public static boolean isMPEiX(){
        return OSName.indexOf("mpe/ix")>=0;
    }

    public static boolean isHPUX(){
        return OSName.indexOf("hp-ux")>=0;
    }

    public static boolean isAix(){
        return OSName.indexOf("aix")>=0;
    }

    public static boolean isOS390(){
        return OSName.indexOf("os/390")>=0;
    }

    public static boolean isFreeBSD(){
        return OSName.indexOf("freebsd")>=0;
    }

    public static boolean isIrix(){
        return OSName.indexOf("irix")>=0;
    }

    public static boolean isDigitalUnix(){
        return OSName.indexOf("digital")>=0&& OSName.indexOf("unix")>0;
    }

    public static boolean isNetWare(){
        return OSName.indexOf("netware")>=0;
    }

    public static boolean isOSF1(){
        return OSName.indexOf("osf1")>=0;
    }

    public static boolean isOpenVMS(){
        return OSName.indexOf("openvms")>=0;
    }

    /**
     * 获取操作系统名字
     * @return 操作系统名
     */
    public static EPlatform getOSname(){
        if(isAix()){
            _instance.platform = EPlatform.AIX;
        }else if (isDigitalUnix()) {
            _instance.platform = EPlatform.Digital_Unix;
        }else if (isFreeBSD()) {
            _instance.platform = EPlatform.FreeBSD;
        }else if (isHPUX()) {
            _instance.platform = EPlatform.HP_UX;
        }else if (isIrix()) {
            _instance.platform = EPlatform.Irix;
        }else if (isLinux()) {
            _instance.platform = EPlatform.Linux;
        }else if (isMacOS()) {
            _instance.platform = EPlatform.Mac_OS;
        }else if (isMacOSX()) {
            _instance.platform = EPlatform.Mac_OS_X;
        }else if (isMPEiX()) {
            _instance.platform = EPlatform.MPEiX;
        }else if (isNetWare()) {
            _instance.platform = EPlatform.NetWare_411;
        }else if (isOpenVMS()) {
            _instance.platform = EPlatform.OpenVMS;
        }else if (isOS2()) {
            _instance.platform = EPlatform.OS2;
        }else if (isOS390()) {
            _instance.platform = EPlatform.OS390;
        }else if (isOSF1()) {
            _instance.platform = EPlatform.OSF1;
        }else if (isSolaris()) {
            _instance.platform = EPlatform.Solaris;
        }else if (isSunOS()) {
            _instance.platform = EPlatform.SunOS;
        }else if (isWindows()) {
            _instance.platform = EPlatform.Windows;
        }else{
            _instance.platform = EPlatform.Others;
        }
        return _instance.platform;
    }
}
