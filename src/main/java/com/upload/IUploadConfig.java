package com.upload;

import com.project.Response;

public interface IUploadConfig {

    /**
     * 检查规则
     * @param fileSize
     * @param getOriginalFilename
     * @return
     */
    Response check(long fileSize, String getOriginalFilename);

    /**
     * 返回访问路径
     * @return
     */
    String getAccessoryPath();

    /**
     * 返回保存的临时目录
     * @return
     */
    String getSaveFolder();

    /**
     * 文件名
     * @return
     */
    String getFileName();

    /**
     * 单个上传文件的大小限制
     * @return
     */
    long getSingleFileSize();

    /**
     * 保存在临时目录的时间
     * @return
     */
    long OverdueWaitTime();
}
