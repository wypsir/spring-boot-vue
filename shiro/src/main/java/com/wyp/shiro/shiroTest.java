package com.wyp.shiro;

import org.apache.shiro.authc.credential.PasswordMatcher;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * @date 2017/10/1 10:38
 * @description
 */
public class shiroTest {

    public static void main(String[] args) {
        String md5 = new SimpleHash("md5", "123",null,2).toHex();
        System.out.println(md5);


    }
}
