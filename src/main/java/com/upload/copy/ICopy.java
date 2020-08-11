package com.upload.copy;

import com.project.Response;

public interface ICopy {

    /**
     * 复制文件到指定目录
     * @param srcFile
     * @param destination
     * @return
     */
    Response copy(String srcFile, String destination);
}
