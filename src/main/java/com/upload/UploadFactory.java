package com.upload;

import com.upload.impl.DefaultConfig;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class UploadFactory {

    public static IUploadConfig getInstanc(String field) {

        if (StringUtils.isEmpty(field)) {
            //取默认
        }

        return new DefaultConfig();
    }
}
