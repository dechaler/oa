package com.de.init;

import com.de.Utils.DateUtils;
import com.de.Utils.MyFileUtils;
import com.de.dao.FileDao;
import com.de.entity.Employee;
import com.de.entity.File;
import com.de.service.FileService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @编写人:de
 * @时间:2019/11/2
 * @描述:
 */
@Component
public class FileInit {
    private List<File> list = new ArrayList<>();
    public final String[] fp = {"a.doc","b.doc","c.doc"};

    @Autowired
    private FileDao fileDao;

    public static int count = 0;

    private FileService fileService;


    public List<File> init() throws ParseException, IOException {
        for (int i = 0; i < 100; i++) {

            int departId = 1 + (new Random().nextInt(6));
            int n = new Random().nextInt(3);
            String filePath = MyFileUtils.UPLOAD_PATH + departId +"/" + fp[n];
            java.io.File srcFile = new java.io.File("d:/" + fp[n]);
            java.io.File desFile = new java.io.File(filePath);
            if (desFile.exists()) {
                count++;
                FileUtils.copyFile(srcFile,desFile);
                continue;
            }
            File file = new File();
            file.setFilePath(filePath);
            file.setUpTime(DateUtils.dateToStrDateTime(new Date(),DateUtils.DATEFORMATWITHTIME));
            file.setFileName(fp[n]);
            Employee employee = new Employee();
            int empId = 100000 + (new Random().nextInt(7));
            employee.setId(empId);
            file.setEmployee(employee);
            FileUtils.copyFile(srcFile,desFile);
            list.add(file);
        }
        int re = fileDao.FileInit(list);
        System.out.println("初始化" + re + "条文件");
        return list;
    }



    public static void main(String[] args) throws ParseException, IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        FileInit fileInit = context.getBean("fileInit", FileInit.class);
        List<File> init = fileInit.init();
        for (File file : init) {
            System.out.println(file);
        }
        System.out.println(count);
    }
}
