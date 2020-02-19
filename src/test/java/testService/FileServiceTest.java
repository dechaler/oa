package testService;

import com.de.utils.DateUtils;
import com.de.utils.MyFileUtils;
import com.de.entity.Employee;
import com.de.entity.File;
import com.de.service.EmpService;
import com.de.service.FileService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

/**
 * @编写人:de
 * @时间:2019/10/14
 * @描述:测试文件服务类
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class FileServiceTest {

    @Autowired
    private FileService fileService;
    @Autowired
    private EmpService empService;

    @Test
    public void upLoadFile() throws ParseException, IOException {
        File file = new File();
        final String srcPath = "D:/c.txt";
        java.io.File srcFile = new java.io.File(srcPath);
        if (!srcFile.exists())
            srcFile.createNewFile();
        Employee employee = empService.selectEmpById(1002);
        final String desPath = MyFileUtils.UPLOAD_PATH +
                employee.getDepartment().getId() + "/" + srcFile.getName();
        file.setUpTime(DateUtils.dateToStrDateTime(new Date(), DateUtils.DATEFORMATWITHTIME));
        file.setFilePath(desPath);
        file.setFileName(srcFile.getName());
        file.setEmployee(employee);
//        int re = fileService.upLoadFile(file, srcFile);
//        if (re > 0) {
//            System.out.println("成功上传");
//        } else {
//            System.out.println("上传失败");
//        }
    }

    @Test
    public void deleteFileByIdTest()  {
        Integer onLineEmpId = 1000;
        Integer fileEmpId = 1000;
        final String desPath = MyFileUtils.UPLOAD_PATH + fileEmpId + "/a.txt";
        java.io.File desFile = new java.io.File(desPath);
//        int re = fileService.deleteFileById(onLineEmpId, fileEmpId, 34,desFile);
//        if (re > 0) {
//            System.out.println("删除文件成功");
//        } else {
//            System.out.println("删除文件失败");
//        }
    }
}
