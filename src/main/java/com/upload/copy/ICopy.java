package com.upload.copy;

import communal.Result;

public interface ICopy {

    /**
     * 复制文件到指定目录
     * @param srcFile
     * @param destination
     * @return
     */
    Result copy(String srcFile, String destination);
}
