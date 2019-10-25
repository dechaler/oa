package com.de.za;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @编写人:de
 * @时间:2019/10/23
 * @描述:
 */
public class Exm {


    public Date getDateTime(String  strdate,String format) throws ParseException {
        SimpleDateFormat sf = new SimpleDateFormat(format);
        Date date = sf.parse(strdate);
        return date;
    }


}
