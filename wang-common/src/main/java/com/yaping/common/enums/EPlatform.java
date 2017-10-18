package com.yaping.common.enums;

/**
 *
 * @date 2017/9/7 18:53
 * @description
 */
public enum EPlatform {
    Any("any"),
    Linux("Linux"),
    Mac_OS("Mac OSEntity"),
    Mac_OS_X("Mac OSEntity X"),
    Windows("Windows"),
    OS2("OSEntity/2"),
    Solaris("Solaris"),
    SunOS("SunOS"),
    MPEiX("MPE/iX"),
    HP_UX("HP-UX"),
    AIX("AIX"),
    OS390("OSEntity/390"),
    FreeBSD("FreeBSD"),
    Irix("Irix"),
    Digital_Unix("Digital Unix"),
    NetWare_411("NetWare"),
    OSF1("OSF1"),
    OpenVMS("OpenVMS"),
    Others("Others");

    EPlatform(String desc){
        this.desc = desc;
    }

    public String toString(){
        return desc;
    }

    private String desc;

    public String getDesc() {
        return desc;
    }

}
