package com.upload;

import communal.util.DateUtil;
import communal.util.RandomUtil;
import communal.util.UUIDGeneratorUtil;

import java.util.Date;

public class Test {

    public static void main(String args[]) {

        String suffixName = ".jpg";
        System.out.println(
                DateUtil.dateFormatToString("yyyyMMddHHmmss") +
                        UploadContact.CONNECTOR +
                        UUIDGeneratorUtil.getUUID() +
                        UploadContact.CONNECTOR +
                        RandomUtil.getNumber(12) +
                        suffixName);
    }
}
