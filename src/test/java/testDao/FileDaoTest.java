package testDao;

import com.de.Utils.DateUtils;
import com.de.dao.EmpDao;
import com.de.dao.FileDao;
import com.de.entity.Employee;
import com.de.entity.File;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * @编写人:de
 * @时间:2019/10/11
 * @描述:测试文件Dao接口
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class FileDaoTest {
    @Autowired
    private FileDao fileDao;
    @Autowired
    private EmpDao empDao;
    @Test
    public void upFile() throws ParseException {
        File file = new File();
        file.setFileName("开会报告");
        file.setFilePath("d:/a.txt");
        List<Employee> employees = empDao.selectEmpByIdOrNameOrDepartId(1000, null,null);
        file.setEmployee(employees.get(0));
        Date date = new Date();
        String dateTime = DateUtils.dateToStrDateTime(date, "yyyy-MM-dd HH:mm:ss");
        file.setUpTime(dateTime);
        int re = fileDao.upLoadFile(file);
        if (re > 0)
            System.out.println("成功上传" + re + "一个文件");
        else
            System.out.println("上传失败");

    }

    @Test
    public void selectAllFile(){
        List<File> files = fileDao.selectAllFile();
        for (File file : files) {
            System.out.println(file);
        }
    }


    @Test
    public void selectFileByDepartIdAndEmpId(){
        List<File> files = fileDao.selectFileByDepartIdAndEmpId(2, null);
        for (File file : files) {
            System.out.println(file);
        }
    }

    @Test
    public void deleteFileById(){
        int re = fileDao.deleteFileById(6);
        System.out.println("成功删除"+ re+"条记录");

    }
}
