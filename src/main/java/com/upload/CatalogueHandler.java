package com.upload;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.joda.time.DateTime;

/**
 * 目录操作类
 */
public class CatalogueHandler {

    public static Map<String, String> create() {

        DateTime nowDT = DateTime.now();
        String year = String.valueOf(nowDT.getYear());          //年
        String month = String.valueOf(nowDT.getMonthOfYear());  //月
        String day = String.valueOf(nowDT.getDayOfMonth());     //日

        //访问路径 2020-8\\15\\
        StringBuffer saveFolder = new StringBuffer();
        saveFolder
                .append(year)
                .append("-")
                .append(month)
                .append("/")
                .append(day)
                .append("/");

        //全路径 E:\\2020-8\\15\\
        StringBuffer path = new StringBuffer();
        path.append(UploadContact.UNIVERSAL_DIR)
                .append(nowDT.getYear())
                .append("-")
                .append(nowDT.getMonthOfYear())
                .append(File.separator)
                .append(nowDT.getDayOfMonth())
                .append(File.separator);

        File catalogue = new File(path.toString());
        if (!catalogue.exists()) {
            catalogue.mkdirs();
        }

        Map<String, String>pathMap = new HashMap<>();
        pathMap.put("path", path.toString());
        pathMap.put("saveFolder", saveFolder.toString());
        return pathMap;
    }

    public static boolean delete(String path) {
        File _path = new File(path);
        if (_path.exists()) {
            return _path.delete();
        }
        return false;
    }

    public static boolean exists(String path) {
        File _path = new File(path);
        return _path.exists();
    }

    public static void main(String args[]) {

        System.out.println(CatalogueHandler.create());
    }
}
