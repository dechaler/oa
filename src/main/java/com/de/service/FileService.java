package com.de.service;

import com.de.entity.File;

import java.io.IOException;
import java.util.List;

/**
 * @编写人:de
 * @时间:2019/10/11
 * @描述:
 */
public interface FileService {

    /**
     *@描述信息：上传文件业务层
     *
     * @参数：文件名，和源文件
     * @返回值：影响个数
     * @编写人：de
     * @时间： 2019/10/11
     */

     int upLoadFile(File file, java.io.File srcFile) throws IOException;



    /**
     *@描述信息：查新全部文件
     *
     * @参数：
     * @返回值：文件列表
     * @编写人：de
     * @时间： 2019/10/11
     */

    List<File> selectAllFile();


    /**
     *@描述信息：根据部门编号和员工号查询文件信息（可选）
     *
     * @参数：
     * @返回值：
     * @编写人：de
     * @时间： 2019/10/11
     */
    List<File> selectFileByDepartIdAndEmpId(Integer departId, Integer empId);


    /**
     *@描述信息：根据文件编号删除文件数据库内容以及源文件
     *
     * @参数：文件编号，在线员工号，文件所属员工号和源文件
     * @返回值：删除个数
     * @编写人：de
     * @时间： 2019/10/11
     */

    int deleteFileById(Integer onLineEmpId, Integer fileEmpId, Integer id, java.io.File desFile);
}
