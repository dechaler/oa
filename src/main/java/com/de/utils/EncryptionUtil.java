package com.de.utils;

import org.apache.shiro.crypto.hash.SimpleHash;

/**
 * @Description 加密
 * @Date 2020/2/20
 * @Author
 * @Version
 **/
public class EncryptionUtil {
//    String password = "123456";//明码
//    String algorithmName = "MD5";//加密算法
//    Object source = password;//要加密的密码
//    Object salt = "admin";//盐值，一般都是用户名或者userid，要保证唯一
//    int hashIterations = 167;//加密次数
//    SimpleHash simpleHash = new SimpleHash(algorithmName,source,salt,hashIterations);
//            System.out.println(simpleHash);//打印出经过盐值、加密次数、md5后的密码

//    private  static SimpleHash simpleHash;
    private static String algorithmName[] = {"md5"};


    /**
     * @Description 加密算法
     * @Param 源字符串 加密次数 盐值
     * @Date 2020/2/20
     * @return
     * @Author
     **/
    public static String md5Encryption(Object source,Object salt,Integer times){
        String md5 = algorithmName[0];
        SimpleHash simpleHash = new SimpleHash(md5, source, salt, times);
        return simpleHash.toString();
    }
}
