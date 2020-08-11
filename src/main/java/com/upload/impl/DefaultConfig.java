package com.upload.impl;

import com.project.Response;
import com.upload.IUploadConfig;
import com.upload.UploadContact;
import communal.Result;
import communal.util.UUIDGeneratorUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Date;

@Slf4j
@Service("defaultUpload")
public class DefaultConfig implements IUploadConfig {

    public Response check(long fileSize, String getOriginalFilename) {

        //判断单个文件大小
        {
            long size = this.getSingleFileSize();
            if (fileSize > size) {
                return Response.getFAIL("该文件大小超出上限!");
            }
        }

        //如果文件夹不存在就创建
        {
            String path = this.getSaveFolder();
            for (int index = 1; index <= path.length(); index++) {
                if (path.substring(index - 1, index).equals(File.separator)) {
                    File file = new File(path.substring(0, index));
                    if (!file.exists()) {
                        file.mkdir();
                    }
                }
            }
        }
        return Response.getSUCCESS();
    }

    public String getAccessoryPath() {
        return UploadContact.AccessoryPath;
    }

    public String getSaveFolder() {
        return UploadContact.UNIVERSAL_DIR;
    }

    public String getFileName() {

        //文件名格式:1983-03-09_时间戳_64位UUID_雪花算法值
        return new Date().getTime() + "_" + UUIDGeneratorUtil.getUUID();
    }

    public long getSingleFileSize() {
        return UploadContact.UNIVERSAL_MAX_POST_SIZE;
    }

    public long OverdueWaitTime() {
        return UploadContact.UNIVERSAL_OVERDUE_WAIT_TIME;
    }

    public static boolean isHave(String[] strs, String s){

        for(int i = 0; i < strs.length; i++){
            if(strs[i].indexOf(s) != -1){
                return true;
            }
        }
        return false;
    }

    public static void main(String args[]) {

        String [] strs = {".jpg", ".png", ".gif"};
        System.out.println(DefaultConfig.isHave(strs, "."));
    }
}
