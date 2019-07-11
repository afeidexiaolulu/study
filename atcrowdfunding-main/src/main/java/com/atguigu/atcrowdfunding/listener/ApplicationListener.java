package com.atguigu.atcrowdfunding.listener;

import com.atguigu.atcrowdfunding.util.Const;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * @author Liang Wenjie
 * @version 1.00
 * @time 2019/7/10 0010 下午 10:35
 */

//实现接口
public class ApplicationListener implements ServletContextListener {

    private static Logger logger = LoggerFactory.getLogger(ApplicationListener.class);
    //项目启动时候监控
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        //获取上下文对象
        ServletContext servletContext = servletContextEvent.getServletContext();
        //将上下文路径放上去application域中
        String contextPath = servletContext.getContextPath();
        logger.info("将上下文路径放入域中，路径为{}",contextPath);
        //将上下文路径放入上下文对象中
        servletContext.setAttribute(Const.APP_PATH,contextPath);

    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
