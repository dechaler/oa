package com.de.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @编写人:de
 * @时间:2019/9/20
 * @描述:日期处理工具类
 */
public class DateUtils {


    public static String DATEFORMATWITHTIME = "yyyy-MM-dd HH:mm:ss";
    public static String DATEFORMATWITHDATE = "yyyy-MM-dd";
   /**
    *@描述信息：日期时间转strin类型日期
    *
    * @参数：
    * @返回值：String
    * @编写人：de
    * @时间： 2019/10/22
    */

    public static String dateToStrDateTime(Date date,String dateFormat) throws ParseException {
        DateFormat df = new SimpleDateFormat(dateFormat);
        String dateTime = df.format(date);
        return dateTime;
    }

    /**
     *@描述信息：String类型日期转日期
     *
     * @参数：
     * @返回值：
     * @编写人：de
     * @时间： 2019/10/22
     */

    public static Date strDateTimeToDate(String strDateTime,String dateFormat) throws ParseException {
        DateFormat df = new SimpleDateFormat(dateFormat);
        Date date = df.parse(strDateTime);
        return date;
    }

    /**
     *@描述信息：动态得到规定时间date
     *
     * @参数：   flag，（标志上班时间或上班时间）
     * @返回值：Date
     * @编写人：de
     * @时间： 2019/10/22
     */

    public static Date getDateTime(int kqWay) throws ParseException {
        Date date = null;
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        month += 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        if (kqWay == KqWay.SHANGBAN) {
            final String strDateTime = "" + year + "-" + month + "-" + day  + " " +"09:" + "00:" + "00";
            System.out.println(strDateTime);
            date = strDateTimeToDate(strDateTime, DATEFORMATWITHTIME);
            return date;
        }else if (kqWay == KqWay.XIABAN) {
            final String strDateTime = "" + year + "-" + month + "-" + day  + " " +"18:" + "00:" + "00";
            date = strDateTimeToDate(strDateTime, DATEFORMATWITHTIME);
            System.out.println(strDateTime);
            return date;
        }
        return date;
    }

    /**
     *@描述信息：给定一个date，得到过去或将来天数的date
     *
     * @参数：   指定date，过去或将来day
     * @返回值：
     * @编写人：de
     * @时间： 2019/10/22
     */

    public static Date getFutureOrBeforeDate(Date date, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, day);
        Date endTime =  calendar.getTime();
        return endTime;
    }

    public static void main(String[] args) throws ParseException {
//        long i = 2;
//        Date date = new Date();
//        System.out.println(date);
//        System.out.println("----------------");
//        System.out.println(date.toString());
//        try {
//            String dateTime = dateToStrDateTime(date, "yyyy-MM-dd HH:mm:ss");
//            System.out.println(dateTime);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        Date date = new Date();
//        Date d = getFutureOrBeforeDate(date, 8);
//        String dateTime = dateToStrDateTime(d, "\"yyyy-MM-dd HH:mm:ss\"");
//        System.out.println(dateTime);
        Date dateTime = getDateTime(1);
        System.out.println(dateTime.getTime());
    }
}
