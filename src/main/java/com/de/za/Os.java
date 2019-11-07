package com.de.za;

import com.de.Utils.OsUtils;

/**
 * @编写人:de
 * @时间:2019/11/7
 * @描述:
 */
public class Os {

    public static void main(String[] args) {
        boolean flag = OsUtils.isWinOs();
        System.out.println(flag);
    }
}
