package com.upload.quartz;

import com.upload.UploadContact;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Period;
import org.joda.time.Seconds;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.io.File;

/**
 * 定时删除过期文件夹
 */
@Slf4j
public class OverdueHandlerQuartz extends QuartzJobBean {

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        DateTime nowDT = DateTime.now();

        int dayNumber = 7;
        nowDT = nowDT.minusDays(dayNumber);                     //dayNumber天前
        String year = String.valueOf(nowDT.getYear());          //年
        String month = String.valueOf(nowDT.getMonthOfYear());  //月
        String day = String.valueOf(nowDT.getDayOfMonth());     //日

        StringBuffer path = new StringBuffer();                 //文件最近层路径
        path.append(UploadContact.UNIVERSAL_DIR)
                .append(year)
                .append("-")
                .append(month)
                .append(File.separator)
                .append(day)
                .append(File.separator);

        File file = new File(path.toString());
        if (file.exists()) {
            file.delete();
        }
    }
}
