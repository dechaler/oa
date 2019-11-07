package com.de.controller;

import com.de.Utils.*;
import com.de.entity.Employee;
import com.de.entity.File;
import com.de.service.FileService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * @编写人:de
 * @时间:2019/10/22
 * @描述:文件控制器
 */
@Controller
@RequestMapping("/file")

public class FIleController {
    @Autowired
    private FileService fileService;
    @Autowired
    private JsonResultType<File> jsonResultType;
    private PageInfo<com.de.entity.File> pageInfo;



    @RequestMapping(value = "/upLoadFile", method = RequestMethod.POST)
    @ResponseBody
    public int upLoadFile(@SessionAttribute Employee employee, MultipartFile srcFile,HttpServletResponse response) throws IOException, ParseException {
        String path = null;
        File fileInfo = new File();
        fileInfo.setEmployee(employee);
        fileInfo.setFileName(srcFile.getOriginalFilename());
        fileInfo.setUpTime(DateUtils.dateToStrDateTime(new Date(),DateUtils.DATEFORMATWITHTIME));
        if (OsUtils.isWinOs()){
            path = MyFileUtils.WIN_PATH + fileInfo.getEmployee().getDepartment().getId() + "/" + srcFile.getOriginalFilename();
        }

        if (OsUtils.isLinOs()){
//            path = MyFileUtils.WIN_PATH + fileInfo.getEmployee().getDepartment().getId() + "/" + srcFile.getOriginalFilename();
        }
        fileInfo.setFilePath(path);
        int re = fileService.upLoadFile(fileInfo, srcFile);
        re = ResponseInfo.verifyDatas(response,re);
        return re;
    }


    @RequestMapping("/selectAllFile")
    @ResponseBody
    public JsonResultType<File> selectAllFile(RequestParams params,HttpServletResponse response) {
        int page = params.getPage();
        int limit = params.getLimit();
        PageHelper.startPage(page,limit);
        List<com.de.entity.File> files = fileService.selectAllFile();
        pageInfo = new PageInfo<>(files);
        jsonResultType = ResponseInfo.verifyDatas(response,jsonResultType,pageInfo);
        return jsonResultType;
    }

    @RequestMapping("/selectFileByDepartIdAndEmpId")
    @ResponseBody
    public JsonResultType<File> selectFileByDepartIdAndEmpId(@SessionAttribute Employee employee, RequestParams params,HttpServletResponse response) {
        int page = params.getPage();
        int limit = params.getLimit();
//        Integer departId = params.getDepartId();
        Integer departId = employee.getDepartment().getId();
        Integer empId = params.getEmpId();
        empId = (empId != null) ? (employee.getId()) : null;
        PageHelper.startPage(page,limit);
        List<com.de.entity.File> files = fileService.selectFileByDepartIdAndEmpId(departId,empId);
        pageInfo = new PageInfo<>(files);
        jsonResultType = ResponseInfo.verifyDatas(response,jsonResultType,pageInfo);
        return jsonResultType;
    }

    @RequestMapping("/selectFileByFileName")
    @ResponseBody
    public JsonResultType<File> selectFileByFileName(RequestParams params,HttpServletResponse response) {
        int page = params.getPage();
        int limit = params.getLimit();
        String fileName = params.getFileName();
        PageHelper.startPage(page,limit);
        List<File> files = fileService.selectFileByFileName(fileName);
        pageInfo = new PageInfo<>(files);
        jsonResultType = ResponseInfo.verifyDatas(response,jsonResultType,pageInfo);
        return jsonResultType;
    }


    @RequestMapping("/deleteFileById")
    @ResponseBody
    public int deleteFileById(@SessionAttribute Employee employee, Integer fileEmpId, Integer fileId, java.io.File desFile, HttpServletResponse response) {
        Integer empId = employee.getId();
        int re = fileService.deleteFileById(empId, fileEmpId, fileId, desFile);
        re = ResponseInfo.verifyDatas(response,re);
        return re;
    }
}

