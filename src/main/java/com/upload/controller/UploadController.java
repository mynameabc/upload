package com.upload.controller;

import com.project.Response;
import com.upload.IUploadConfig;
import com.upload.UploadContact;
import communal.Result;
import communal.util.DateUtil;
import communal.util.RandomUtil;
import communal.util.UUIDGeneratorUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;

@Slf4j
@RestController
@Api(tags="上传模块")
public class UploadController {

    @Autowired
    @Qualifier("defaultUpload")
    private IUploadConfig defaultUpload;

    /**
     * 上传
     * @param file
     * @return
     */
    @ApiOperation(value="文件上传", notes="")
    @PostMapping(value="fileUpload")
    public Response fileUpload(@RequestParam(value="file")MultipartFile file, @RequestParam(value="json")String json) {

        Response response = defaultUpload.check(file.getSize(), file.getOriginalFilename());


        String fileName = file.getOriginalFilename();                               //文件名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));      //扩展名
        String saveFolder = UploadContact.UNIVERSAL_DIR;
        String accessoryPath = UploadContact.AccessoryPath;
        fileName = DateUtil.dateFormatToString("yyyyMMddHHmmss") +
                UploadContact.CONNECTOR +
                UUIDGeneratorUtil.getUUID() +
                UploadContact.CONNECTOR +
                RandomUtil.getNumber(12) + suffixName;

        //上传
        {
            try {
                File destFile = new File(saveFolder + fileName);
                if (!destFile.getParentFile().exists()) {destFile.getParentFile().mkdirs();}    //文件夹不存在就新建
                file.transferTo(destFile);              //上传
            } catch (FileNotFoundException e) {
                log.error("没有在该目录下找到文件:{}", e.getCause().getMessage());
                Response.getFAIL("没有在该目录下找到文件!");
            } catch (IOException e) {
                log.error("IOException:{}", e);
                return Response.getFAIL("IOException!");
            }
        }

        log.info("文件名:" + accessoryPath + fileName);
        return Response.getSUCCESS("upload success!", accessoryPath + fileName);
    }
}
