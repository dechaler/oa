package za;

import com.de.utils.DateUtils;
import org.junit.Test;

import java.text.ParseException;
import java.util.Date;

/**
 * @编写人:de
 * @时间:2019/10/22
 * @描述:
 */
public class TestDateUtils {


    @Test
    public void test() throws ParseException {

        Date date = DateUtils.strDateTimeToDate("2019-10-19 19:20:65", "yyyy-MM-dd HH:mm:ss");
        System.out.println(date.getTime());
    }
}
