package com.de.service;

import com.de.entity.File;
import org.springframework.web.multipart.MultipartFile;

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

     int upLoadFile(File file, MultipartFile srcFile) throws IOException;



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

    /**
     *@描述信息：根据名字查询文件
     *
     * @参数：
     * @返回值：
     * @编写人：de
     * @时间： 2019/11/7
     */

    List<File> selectFileByFileName(String name);


    /**
     *@描述信息：通过文件名删除文件
     *
     * @参数：   文件id
     * @返回值：
     * @编写人：de
     * @时间： 2019/11/11
     */

    int deleteFileById(Integer fileId, String filePath);


    /**
     *@描述信息：批量删除文件
     *
     * @参数：  文件id,文件路径
     * @返回值：
     * @编写人：de
     * @时间： 2019/11/11
     */
    int deleteFileByIds(List<Integer> fIds,List<String> fPaths);
}
