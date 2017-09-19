package com.wyp.webserver.web.controller;

import com.wyp.common.entity.Result;
import com.wyp.common.util.ErrorUtil;
import com.wyp.common.util.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @aurhor wangyaping 【yapingw@dingtalk.com】
 * @date 2017/9/19 17:34
 * @description 上传文件控制器
 */
@RestController
@RequestMapping("/api")
public class UploadController {

    @Autowired
    private HttpServletRequest request;



    @RequestMapping("upload")
    public Result upload(MultipartFile file) {

        try {
            String uploadDir = request.getSession().getServletContext().getRealPath("/").toString().concat("upload/");

            File dir = new File(uploadDir);
            if (!dir.exists()) {
                dir.mkdir();
            }
            executeUpload(uploadDir, file);
        } catch (Exception e) {
            Log.error("upload error|ex={}", ErrorUtil.writer(e));
            return Result.failure("上传失败");
        }

        return Result.success("上传成功");
    }

    @RequestMapping("uploads")
    public Result uploads(MultipartFile[] files) {
        try {
            String uploadDir = request.getSession().getServletContext().getRealPath("/").toString().concat("uploads/");

            File dir = new File(uploadDir);
            if (!dir.exists()) {
                dir.mkdir();
            }

            for (MultipartFile file : files) {
                executeUpload(uploadDir, file);
            }
        } catch (Exception e) {
            Log.error("upload error|ex={}", ErrorUtil.writer(e));
            return Result.failure("上传失败");
        }

        return Result.success("上传成功");
    }


    /**
     * 上传图片
     *
     * @param dir  上传目录
     * @param file 上传对象
     * @throws IOException
     */
    public void executeUpload(String dir, MultipartFile file) throws IOException {
        String filename = file.getOriginalFilename();
        String suffix = filename.substring(filename.lastIndexOf("."));
        filename = UUID.randomUUID() + suffix;
        File serverFile = new File(dir + filename);
        Log.info(filename);
        file.transferTo(serverFile);
    }

}
