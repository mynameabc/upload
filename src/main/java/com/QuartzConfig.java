package com;

import com.upload.quartz.OverdueHandlerQuartz;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuartzConfig {
    @Bean
    public JobDetail overdueHandlerQuartz() {
        return JobBuilder.newJob(OverdueHandlerQuartz.class).withIdentity("overdueHandlerQuartz").storeDurably().build();
    }

    @Bean
    public Trigger overdueHandlerQuartzTrigger() {
        //cron方式： 每天 23点45分 执行一次
        return TriggerBuilder.newTrigger().forJob(overdueHandlerQuartz())
                .withIdentity("overdueHandlerQuartz")
                .withSchedule(CronScheduleBuilder.cronSchedule("0 45 23 ? * *"))
                .build();
    }
}
