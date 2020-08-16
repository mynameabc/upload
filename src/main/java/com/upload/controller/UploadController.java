package com.upload.controller;

import com.project.Response;
import com.upload.CatalogueHandler;
import com.upload.IUploadConfig;
import com.upload.UploadContact;
import communal.util.DateUtil;
import communal.util.RandomUtil;
import communal.util.UUIDGeneratorUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@Api(tags="上传模块")
public class UploadController {

    @Autowired
    @Qualifier("defaultUpload")
    private IUploadConfig defaultUpload;

    /**
     * 单个文件上传
     * @param file
     * @param jsonParameter
     * @return
     */
    @ApiOperation(value="单个文件上传", notes="")
    @PostMapping(value="singleFileUpload")
    public Response singleFileUpload(@RequestParam(value="file")MultipartFile file, @RequestParam(value="jsonParameter")String jsonParameter) {

//        Response response = defaultUpload.check(file.getSize(), file.getOriginalFilename());

        //原文件名
        String fileName = file.getOriginalFilename();

        //原文件扩展名
        String extensionName = FilenameUtils.getExtension(fileName);

        //重命名
        fileName =
                DateUtil.dateFormatToString("yyyyMMddHHmmssSSS") +
                UploadContact.CONNECTOR +
                UUIDGeneratorUtil.getUUID() +
                UploadContact.CONNECTOR +
                RandomUtil.getNumber(12) +
                "." +
                extensionName;

        Map<String, String>pathMap = CatalogueHandler.create();

        //全路径 E:\\2020-8\\15\\
        String path = pathMap.get("path");

        //保存的路径 2020-8\\15\\
        String saveFolder = pathMap.get("saveFolder");

        //上传
        {
            try {
                File destFile = new File(path + fileName);
                file.transferTo(destFile);
            } catch (IOException e) {
                log.error("上传失败:{}", e.getCause().getMessage());
                return Response.getFAIL("上传失败!");
            }
        }

        try {
            String commod = "chmod 777 " + path + fileName;
            log.info("执行命令:{}", commod);
            Runtime.getRuntime().exec(commod);
        } catch (Exception e) {
            log.error("权限设置失败:{}", e.getCause().getMessage());
            return Response.getFAIL("权限设置失败!");
        }

        //http访问路径
        String accessoryPath = UploadContact.AccessoryPath + saveFolder + fileName;
        return Response.getSUCCESS("upload success!", accessoryPath);
    }

    /**
     * 批量文件上传
     * @param file
     * @param jsonParameter
     * @return
     */
    @ApiOperation(value="批量文件上传", notes="")
    @PostMapping(value="batchFileUpload")
    public Response batchFileUpload(@RequestParam(value="file")MultipartFile file, @RequestParam(value="jsonParameter")String jsonParameter) {
        return Response.getSUCCESS("upload success!", "");
    }

    @GetMapping(value = "bbb")
    public String test() {
        log.info("当前用户id:");
        return "你好!";
    }
}
