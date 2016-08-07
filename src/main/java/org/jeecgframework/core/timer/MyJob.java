package org.jeecgframework.core.timer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import weixin.guanjia.core.service.impl.WechatService;

import java.io.IOException;
import java.util.Date;

/**
 * Created by Administrator on 2016/2/15.
 */
@Component(value = "myJob")
public class MyJob {
    @Autowired
    private WechatService wechatService;
    @Scheduled(cron="0 0 * * * ?")
    public void doWork(){
        System.out.println("定时任务获取AccessToken:" + new Date());
        try {
            wechatService.flushToken();
            //wechatService.getTicket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
