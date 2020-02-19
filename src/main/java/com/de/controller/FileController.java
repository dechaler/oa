package com.de.controller;

import com.de.utils.*;
import com.de.entity.Employee;
import com.de.entity.File;
import com.de.service.FileService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @编写人:de
 * @时间:2019/10/22
 * @描述:文件控制器
 */
@Controller
@RequestMapping("/file")

public class FileController {
    @Autowired
    private FileService fileService;
    @Autowired
    private JsonResultType<File> jsonResultType;
    private PageInfo<com.de.entity.File> pageInfo;



    @RequestMapping(value = "/upLoadFile", method = RequestMethod.POST)
    @ResponseBody
    public int upLoadFile(@SessionAttribute Employee employee, MultipartFile srcFile,HttpServletResponse response) throws IOException, ParseException {
        byte[] bytes = srcFile.getBytes();
        //控制文件上传不超过5m
        if (bytes.length > 1024 * 1000 * 5) {
            return -3;
        }
        String filename = srcFile.getOriginalFilename();
        String[] split = filename.split("\\.");
        String type = split[split.length-1];
        if (!FileTypeUtils.isHas(type)) {
            return -2;
        }
        String path = null;
        File fileInfo = new File();
        fileInfo.setEmployee(employee);
        fileInfo.setFileName(filename);
        fileInfo.setUpTime(DateUtils.dateToStrDateTime(new Date(),DateUtils.DATEFORMATWITHTIME));
        if (OsUtils.isWinOs()){
            path = MyFileUtils.WIN_PATH + fileInfo.getEmployee().getDepartment().getId() + "/" + fileInfo.getEmployee().getId() + "/" + srcFile.getOriginalFilename();
        }

        if (OsUtils.isLinOs()){
            path = MyFileUtils.LIN_PATH + fileInfo.getEmployee().getDepartment().getId() + "/" + fileInfo.getEmployee().getId() + "/" + srcFile.getOriginalFilename();
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
    public int deleteFileById(Integer fileId, String  filePath, HttpServletResponse response) {
        int re = fileService.deleteFileById(fileId, filePath);
        re = ResponseInfo.verifyDatas(response,re);
        return re;
    }

    @RequestMapping("/deleteFileByIds")
    @ResponseBody
    public int deleteFileByIds(String fIds,String fPaths,HttpServletResponse response){
        String idsSubstr = fIds.substring(1, fIds.length()- 1);
        String[] ids = idsSubstr.split(",");
        List<Integer> idsList = new ArrayList<>();
        for (int i = 0; i < ids.length; i++) {
            idsList.add(Integer.valueOf(ids[i]));
        }
        String pathsSubstr = fPaths.substring(1, fPaths.length()- 1);
        String[] paths = pathsSubstr.split(",");
        List<String> pathsList = new ArrayList<>();
        for (int i = 0; i < paths.length; i++) {
            String s = paths[i].substring(1, paths[i].length()- 1);
            pathsList.add(s);
        }
        int re = fileService.deleteFileByIds(idsList, pathsList);
        re = ResponseInfo.verifyDatas(response,re);
        return re;
    }

    //下载文件
    @RequestMapping(value = "/download",method = RequestMethod.GET)
    public void download(String filePath, String fileName, HttpServletResponse response, HttpServletRequest request) throws IOException {
        //设置响应类型为二进制
        response.setContentType("application/octet-stream;charset=UTF-8");
        //设置响应头
//        response.addHeader("content-Type","application/octet-stream");

        //防止文件名乱码
        //浏览器设置
        String userAgent = request.getHeader("User-Agent");
        if (userAgent.contains("MSIE") || userAgent.contains("Trident")) {
            //IE浏览器处理
            fileName = java.net.URLEncoder.encode(fileName, "UTF-8");
        } else {
            // 非IE浏览器的处理：
            fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1");
        }
        response.setHeader("content-Disposition", "attachment;fileName="+ fileName);
        ServletOutputStream sos = response.getOutputStream();
        java.io.File file = new java.io.File(filePath);
        if (file.exists()) {
            InputStream is = FileUtils.openInputStream(file);
            StreamUtils.copy(is, sos);
            sos.flush();
            is.close();
            sos.close();
        }

    }















    @RequestMapping(value = "/downloads",method = RequestMethod.GET)
    public void downloads(String filePaths, String fileNames, HttpServletResponse response, HttpServletRequest request) throws IOException {
        //设置响应类型为二进制
        //设置响应头
//        response.addHeader("content-Type","application/octet-stream");
        String[] fns = fileNames.split(",");
        String[] fps = filePaths.split(",");
        String fileName = null;
        String filePath = null;
        ServletOutputStream sos = null;
        InputStream is = null;

        if (fns.length == fps.length){
            for (int i = 0; i < fns.length; i++) {
                filePath = fps[i];
                fileName = new String(fns[i].getBytes("UTF-8"), "ISO-8859-1");
                response.setContentType("application/octet-stream;charset=UTF-8");
                response.setHeader("content-Disposition", "attachment;fileName="+ fileName);
                sos = response.getOutputStream();
                java.io.File file = new java.io.File(filePath);
                if (file.exists()) {
                    is = FileUtils.openInputStream(file);
                    StreamUtils.copy(is, sos);
//                    StreamUtils.
                    sos.flush();
                }
            }

        }
        if (null != is)
            is.close();
        if (null != sos)
            sos.close();



    }







}

