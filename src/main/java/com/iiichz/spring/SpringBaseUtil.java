package com.iiichz.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;

public class SpringBaseUtil {

    private static ApplicationContext applicationContext;    //Spring应用上下文环境


    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }


    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }


    public static Object getBean(String name) throws BeansException {
        return applicationContext.getBean(name);
    }


    public static Object getBean(String name, Class requiredType) throws BeansException {
        return applicationContext.getBean(name, requiredType);
    }

    public static <T> T getBean(Class<T> tClass) {
        return applicationContext.getBean(tClass);
    }

    public static boolean containsBean(String name) {
        return applicationContext.containsBean(name);
    }

    public static boolean isSingleton(String name) throws NoSuchBeanDefinitionException {
        return applicationContext.isSingleton(name);
    }

    public static Class getType(String name) throws NoSuchBeanDefinitionException {
        return applicationContext.getType(name);
    }
}
