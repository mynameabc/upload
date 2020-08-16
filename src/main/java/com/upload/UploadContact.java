package com.upload;

public class UploadContact {

    /****************************通用上传参数********************************/

    /**
     * 上传编码
     */
    public static final String UNIVERSAL_ENCODING = "UTF-8";

    /**
     * 上传目录 Windows:E:\\  |  Linux:/usr/local/ 最后的杠一定要加
     */
    public static final String UNIVERSAL_DIR = "/web/images/";
//    public static final String UNIVERSAL_DIR = "E:\\images\\";
/*
    location /images/{
        alias /web/images/;
        autoindex_exact_size off;   #默认为on, 显示出文件的确切大小, 单位是bytes
        autoindex_localtime on;     #改为off后，显示出文件的大概大小，单位是kB或者MB或者GB
        charset utf-8;
    }

    location /images/{
        alias E:\\images\\;
        autoindex_exact_size off;   #默认为on, 显示出文件的确切大小, 单位是bytes
        autoindex_localtime on;     #改为off后，显示出文件的大概大小，单位是kB或者MB或者GB
        charset utf-8;
    }
*/
    /**
     * 访问路径 最后的杠一定要加
     */
    public static final String AccessoryPath = "http://192.168.2.100:80/images/";

    /**
     * 上传表单文件限定大小:1M
     */
    public static final long UNIVERSAL_MAX_POST_SIZE = 1 * 1024L * 1024L;

    /**
     * 保存在临时目录的时间:7天
     */
    public static final long UNIVERSAL_OVERDUE_WAIT_TIME = 604800L;

    /**
     * 文件连接符
     */
    public static final String CONNECTOR = "-";

    /****************************通用上传参数********************************/

}
