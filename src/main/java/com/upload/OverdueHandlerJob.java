package com.upload;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * 定时删除过期文件
 */
public class OverdueHandlerJob implements Job {

    @Override
    public void execute(JobExecutionContext arg0) throws JobExecutionException {


    }
}
