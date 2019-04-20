package com.upload.controller;

import com.upload.IUploadConfig;
import com.upload.UploadContact;
import communal.Result;
import communal.util.DateUtil;
import communal.util.RandomUtil;
import communal.util.UUIDGeneratorUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Date;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import java.io.FileNotFoundException;

@Api(tags="上传模块")
@RestController
public class UploadController {

    final static Logger logger = LogManager.getLogger(UploadController.class);

    @Autowired
    @Qualifier("defaultUpload")
    private IUploadConfig defaultUpload;

    /**
     *
     * @param file
     * @return
     */
    @ApiOperation(value="文件上传", notes="")
    @PostMapping(value="fileUpload", produces=MediaType.APPLICATION_JSON)
    @ResponseBody
    public Result fileUpload(@RequestParam(value="file")MultipartFile file, HttpServletResponse response) {

        //加这句可以实现跨域上传
        response.setHeader("Access-Control-Allow-Origin", "*");

        Result result = defaultUpload.check(file.getSize(), file.getOriginalFilename());
        if (!result.isSuccess()) {
            return result;
        }

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
                logger.error("没有在该目录下找到文件:{}", e);
                return new Result(false, "没有在该目录下找到文件!");
            } catch (IOException e) {
                logger.error("IOException:{}", e);
                return new Result(false, "IOException!");
            }
        }

        logger.info("文件名:" + accessoryPath + fileName);
        return new Result(true, null,accessoryPath + fileName);
    }

    /**
     *
     * @param file
     * @param field
     * @return
     */
/*    @ApiOperation(value="文件上传", notes="")
    @PostMapping(value="fileUpload", produces=MediaType.APPLICATION_JSON)
    @ResponseBody
    public Result fileUpload(@RequestParam(value="file")MultipartFile file, @RequestParam(value = "field")String field) {

        IUploadConfig uploadConfig = UploadFactory.getInstanc(field);
        Result result = uploadConfig.check(file.getSize(), file.getOriginalFilename());
        if (!result.isSuccess()) {
            return result;
        }

        if (file.isEmpty()) {
            logger.error("文件不能为空!");
        }

        String fileName = file.getOriginalFilename();                               //文件名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));      //扩展名
        String saveFolder = uploadConfig.getSaveFolder();                           //临时保存目录
        String accessoryPath = uploadConfig.getAccessoryPath();                     //访问路径
        fileName = new Date().getTime() +
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
                logger.error("没有在该目录下找到文件:{}", e);
            } catch (IOException e) {
                logger.error("IOException:{}", e);
            }
        }

        logger.info("文件名:" + accessoryPath + fileName);
        return new Result(true, null,accessoryPath + fileName);
    }*/
}
