package com.upload.reName;

import com.oreilly.servlet.multipart.FileRenamePolicy;
import communal.util.RandomUtil;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.Date;

@Component("randomFileNamePolicy")
public class RandomFileNamePolicy implements FileRenamePolicy {

    public File rename(File file) {

        //文件名格式:16位随机字符-当前时间戳
        String newFileName = RandomUtil.generateNominateString(32) + "-" + new Date().getTime();
        return new File(file.getParent(), newFileName);
    }
}
