package com.upload;

public class UploadContact {

    /****************************通用上传参数********************************/

    /**
     * 上传编码
     */
    public static final String UNIVERSAL_ENCODING = "UTF-8";

    /**
     * 上传目录
     */
    public static final String UNIVERSAL_DIR = "/web/images/";

    /**
     * 文件域名
     */
    public static final String UNIVERSAL_FILE_DOMAIN_NAME = "http://image.gumi.com/";

    /**
     * 上传表单文件限定大小:1M
     */
    public static final long UNIVERSAL_MAX_POST_SIZE = 1 * 1024 * 1024L;

    /**
     * 保存在临时目录的时间:7天
     */
    public static final long UNIVERSAL_OVERDUE_WAIT_TIME = 604800L;

    /****************************通用上传参数********************************/

}
