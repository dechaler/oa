package com.de.Utils;

/**
 * @编写人:de
 * @时间:2019/11/7
 * @描述:判段当前运行的操作系统
 */
public class OsUtils {

    public final static String WINDOW= "win";
    public final static String LINUX= "lin";

    public static boolean isWinOs() {
        String osName = System.getProperty("os.name");
        int re = osName.toLowerCase().indexOf(WINDOW);
        if (re != -1) {
            return true;
        } else {
            return false;
        }
    }
        public static boolean isLinOs(){
            String osName = System.getProperty("os.name");
            int re = osName.toLowerCase().indexOf(LINUX);
            if (re != -1) {
                return true;
            }else{
                return false;
            }
    }
}
