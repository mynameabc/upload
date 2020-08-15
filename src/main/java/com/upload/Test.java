package com.upload;

import communal.util.DateUtil;
import communal.util.RandomUtil;
import communal.util.UUIDGeneratorUtil;
import org.joda.time.DateTime;

import java.io.File;
import java.util.Date;

public class Test {

    public static void main(String args[]) {

        String suffixName = ".jpg";
        System.out.println(
                DateUtil.dateFormatToString("yyyyMMddHHmmssSSS") +
                        UploadContact.CONNECTOR +
                        UUIDGeneratorUtil.getUUID() +
                        UploadContact.CONNECTOR +
                        RandomUtil.getNumber(12) +
                        suffixName);

        DateTime nowDT = DateTime.now();
        String year = String.valueOf(nowDT.getYear());          //年
        String month = String.valueOf(nowDT.getMonthOfYear());  //月
        String day = String.valueOf(nowDT.getDayOfMonth());     //日

        //访问路径 2020-8\\15\\
/*        StringBuffer saveFolder = new StringBuffer();
        saveFolder
                .append(year)
                .append("-")
                .append(month)
                .append("/")
                .append(day)
                .append("/");

        System.out.println(saveFolder.toString());*/

/*        String saveFolder = "E:/";
        saveFolder = saveFolder + "2020-08/15";

        File catalogue = new File(saveFolder);
        if (!catalogue.exists()) {
            catalogue.mkdirs();
        }*/
    }
}
