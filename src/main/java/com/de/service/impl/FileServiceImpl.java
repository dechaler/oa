package com.de.service.impl;

import com.de.dao.FileDao;
import com.de.entity.File;
import com.de.service.FileService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

/**
 * @编写人:de
 * @时间:2019/10/11
 * @描述:文件业务实现类
 */
@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private FileDao fileDao;


    //事务管理的上传方法，成功返回上传个数，失败返回0
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    @Override
    public int upLoadFile(File file, java.io.File srcFile) throws IOException {
        int re = fileDao.upLoadFile(file);
        //其他操作
        if (re > 0) {
            String path = file.getFilePath();
            java.io.File desFile = new java.io.File(path);
            FileUtils.copyFile(srcFile, desFile);
            return re;
        } else {
            return 0;
        }
    }


    @Override
    public List<File> selectAllFile() {
        return fileDao.selectAllFile();
    }

    @Override
    public List<File> selectFileByDepartIdAndEmpId(Integer departId, Integer empId) {
        if ((null != empId) && (!"".equals(empId.toString()))) {
            List<File> files = fileDao.selectFileByDepartIdAndEmpId(departId, empId);
            return files;
        } else {
            List<File> files = fileDao.selectFileByDepartIdAndEmpId(departId, null);
            return files;
        }
    }

    //事务管理的删除文件，成功返回个数，失败返回0
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    @Override
    public int deleteFileById(Integer onLineEmpId, Integer fileEmpId, Integer id, java.io.File desFile) {
        if (onLineEmpId.equals(fileEmpId) && desFile.exists()) {
            int re = fileDao.deleteFileById(id);
            if (re > 0) {
                boolean flag = FileUtils.deleteQuietly(desFile);
                if (flag) {
                    return re;
                } else {
                    //确保事务回滚
                    throw new RuntimeException();
                }
            } else {
                return 0;
            }
        } else {
            return 0;
        }
    }
}

//                final String desPath = MyFileUtils.UPLOAD_PATH + fileEmpId + "/" + desFile.getName();
//                final String desPath = desFile.getPath();
//                java.io.File rollFile = new java.io.File(desPath);
//                rollFile.createNewFile();
//                FileUtils.copyToFile(is,rollFile);
//                if (null != is)
//                    is.close();
//                throw e;


