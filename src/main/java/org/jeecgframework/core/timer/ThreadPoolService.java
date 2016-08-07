package org.jeecgframework.core.timer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;


@WebListener(value = "ThreadPoolService")
public class ThreadPoolService implements ServletContextListener {
    public static final Logger LOG = LoggerFactory.getLogger(ThreadPoolService.class);

    //public static ThreadPoolExecutor poolExecService = null;

    //public static final long APP_START_TIME = System.currentTimeMillis();


    //private Locale[] locales = { Locale.SIMPLIFIED_CHINESE };



    @Override
    public void contextDestroyed(ServletContextEvent event) {

    }

    @Override
    public void contextInitialized(ServletContextEvent event) {
        LOG.info("系统初始化");

        ApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(event
                .getServletContext());
        MyJob myJob = (MyJob) applicationContext.getBean("myJob");
        myJob.doWork();

       /* poolExecService = new ThreadPoolExecutor(10, 10, 60L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());*/

    }
}
