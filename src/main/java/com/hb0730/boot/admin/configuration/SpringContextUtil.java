package com.hb0730.boot.admin.configuration;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * <p>
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
@Component
public class SpringContextUtil implements ApplicationContextAware {

    private static ApplicationContext appContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        appContext = applicationContext;
    }

    /**
     * 获取ApplicationContext
     * @return ApplicationContext 返回一个ApplicationContext对象
     */
    public static ApplicationContext getApplicationContext(){
        return appContext;
    }

    public static <T> T getBean(Class<T> clazz){
        return appContext.getBean(clazz);
    }

    public static Object getBean(String className){
        return appContext.getBean(className);
    }

}
