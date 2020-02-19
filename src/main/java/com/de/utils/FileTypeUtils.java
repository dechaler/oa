package com.de.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Description 文件类型后缀
 * @Date 2020/2/16
 * @Author
 * @Version
 **/
public class FileTypeUtils {
    public static String[] temp = {"doc","docx","pdf","dot","dotx","wpt","txt"};
    public static List<String> fileType = new ArrayList<>(Arrays.asList(temp));




    public static boolean isHas(String type) {
        boolean flag = fileType.contains(type);
        return flag;
    }
}
