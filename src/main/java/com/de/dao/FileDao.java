package com.de.dao;

import com.de.entity.File;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @编写人:de
 * @时间:2019/9/10
 * @描述:文件上传接口
 */
public interface FileDao {

    /**
     *@描述信息：上传文件
     *
     * @参数： 文件类
     * @返回值：上传成功影响的个数
     * @编写人：de
     * @时间： 2019/9/10
     */
    int upLoadFile(File file);


//    List<File> select
    /**
     *@描述信息：通过id删除文件
     *
     * @参数：   文件id
     * @返回值：删除的格式
     * @编写人：de
     * @时间： 2019/9/10
     */

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

    List<File> selectFileByDepartIdAndEmpId(@Param("departId") Integer departId, @Param("empId") Integer empId);



    /**
     *@描述信息：根据名字查询文件
     *
     * @参数：
     * @返回值：
     * @编写人：de
     * @时间： 2019/11/7
     */

    List<File> selectFileByFileName(@Param("name") String name);

    /**
     *@描述信息：根据文件编号删除文件
     *
     * @参数：文件编号
     * @返回值：删除个数
     * @编写人：de
     * @时间： 2019/10/11
     */
    
    int deleteFileById(Integer id);


    /**
     *@描述信息：批量删除文件
     *
     * @参数：   文件id
     * @返回值：
     * @编写人：de
     * @时间： 2019/11/11
     */
    int deleteFileByIds(@Param("fIds") List<Integer> fIds);
    /**
     *@描述信息：初始化文件信息
     *
     * @参数：
     * @返回值：
     * @编写人：de
     * @时间： 2019/11/2
     */

    int FileInit(List<File> list);




    int delFileByEmpId(Integer empId);




}
