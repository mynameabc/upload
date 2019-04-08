package com.upload.controller;

import com.upload.IUploadConfig;
import com.upload.UploadFactory;
import communal.Result;
import com.upload.copy.ICopy;
import communal.util.UUIDGeneratorUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MediaType;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;

@Api(tags="上传模块")
@RestController
public class UploadController {

    @Autowired
    private ICopy copyImpl;

    @ApiOperation(value="测试", notes="")
    @GetMapping(value="test", produces=MediaType.APPLICATION_JSON)
    public void test(HttpServletRequest request) {
        System.out.println("你好!");
    }

    @ApiOperation(value="文件复制", notes="")
    @GetMapping(value="copyFile", produces=MediaType.APPLICATION_JSON)
    public Result copyFile(@RequestParam("srcFile") String srcFile, @RequestParam("destination") String destination) {
        return copyImpl.copy(srcFile, destination);
    }

    /**
     *
     * @param file
     * @param field
     * @return
     */
    @ApiOperation(value="文件上传", notes="")
    @PostMapping(value="fileUpload", produces=MediaType.APPLICATION_JSON)
    @ResponseBody
    public Result fileUpload(@RequestParam(value="file")MultipartFile file, @RequestParam(value = "field")String field) {

        IUploadConfig uploadConfig = UploadFactory.getInstanc(field);
        Result result = uploadConfig.check(file.getSize(), file.getOriginalFilename());
        if (!result.isSuccess()) {
            return result;
        }

        if (file.isEmpty()) {
            System.out.println("文件为空!");
        }

        //判断扩展名
        {

        }

        //判断文件大小
        {
            Long size = 1 * 1024 * 1024L;
            if (file.getSize() > size) {
                return new Result(false, "该文件大小超出上限!");
            }
        }

        //如果文件夹不存在就新建
        {

        }

        String fileName = file.getOriginalFilename();                               //文件名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));      // 后缀名

        String saveFolder = uploadConfig.getSaveFolder();
        fileName = new Date().getTime() + "_" + UUIDGeneratorUtil.getUUID() + "_" + suffixName;
        String accessoryPath = uploadConfig.getAccessoryPath();

        try {
            File destFile = new File(saveFolder + fileName);
            if (!destFile.getParentFile().exists()) {
                destFile.getParentFile().mkdirs();
            }
            file.transferTo(destFile);
        } catch (FileNotFoundException e) {
            System.out.println("这里是FileNotFoundException");
            e.printStackTrace();
            e.getMessage();
        } catch (IOException e) {
            System.out.println("这里是IOException");
            e.printStackTrace();
            e.getMessage();
        }

        System.out.println(accessoryPath + fileName);
        return new Result(true, null,accessoryPath + fileName);
    }
}
