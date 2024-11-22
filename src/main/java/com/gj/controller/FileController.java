package com.gj.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/file")
public class FileController {

    private final Logger log = LoggerFactory.getLogger(FileController.class);

    /**
     * 测试文件上传
     */
    @RequestMapping("/upload")
    public String upload(MultipartFile file, HttpServletRequest request) throws IOException { // 变量名要和form的input type="file"的name名一致
        log.debug("文件名：{}", file.getOriginalFilename());
        log.debug("文件大小：{}", file.getSize());
        log.debug("文件类型：{}", file.getContentType());
        // 处理文件上传 根据相对路径 上传 upload 获取绝对路径(真实路径) /users/desktop
        String realpath = request.getSession().getServletContext().getRealPath("/upload");
        log.debug("获取绝对路径：{}", realpath);
        // 修改文件名
        String fileName = file.getOriginalFilename();
        int i = fileName.lastIndexOf(".");
        String suffix = fileName.substring(i);
        String newFileName = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date())+suffix;
        // 上传文件 参数1将文件写进目录
        file.transferTo(new File(realpath,newFileName));
        return "redirect:/upload.jsp";
    }
}