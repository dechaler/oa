package com.de.service.impl;

import com.de.Utils.MyFileUtils;
import com.de.Utils.OsUtils;
import com.de.dao.FileDao;
import com.de.entity.File;
import com.de.service.FileService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

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
    public int upLoadFile(File file, MultipartFile srcFile) throws IOException {
        String path = file.getFilePath();
        String dirpath = null;
        if (OsUtils.isWinOs()){
           dirpath = MyFileUtils.WIN_PATH + file.getEmployee().getId();
        }
        if (OsUtils.isLinOs()){
        }
        java.io.File dirFile = new java.io.File(dirpath);
        if (!dirFile.exists()){
            dirFile.mkdirs();
        }
        java.io.File desFile = new java.io.File(path);
        if (desFile.exists()) {
//            srcFile.transferTo(desFile);
            return -1;
        }else {
                srcFile.transferTo(desFile);
                int re = fileDao.upLoadFile(file);
                return re;
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

    @Override
    public List<File> selectFileByFileName(String name) {
        List<File> files = fileDao.selectFileByFileName(name);
        return files;
    }

    //事务管理的删除文件，成功返回个数，失败返回0
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    @Override
    public int deleteFileById(Integer fileId, String filePath) {
            int re = fileDao.deleteFileById(fileId);
            if (re > 0) {
                java.io.File desFile = new java.io.File(filePath);
                boolean flag = FileUtils.deleteQuietly(desFile);
                if (flag) {
                    return re;
                }else {
                    return 0;
                }
            } else {
                return 0;
            }
        }


    @Override
    public int deleteFileByIds(List<Integer> fIds,List<String> fPaths) {
        java.io.File desFile;
        int re = fileDao.deleteFileByIds(fIds);
        if (re > 0) {
            for (int i = 0; i < fPaths.size(); i++) {
                desFile = new java.io.File(fPaths.get(i));
                boolean flag = FileUtils.deleteQuietly(desFile);
                if (!flag) {
                    return 0;
                }
            }
        }
        return re;
    }
}


