package xin.dayukeji.wxofficial.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: yfy
 * @Date: 2019-07-23 11:38
 * @Version 1.0
 * @description 描述
 */
@Configuration
public class ApplicationContextUtils implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        ApplicationContextUtils.applicationContext = applicationContext;
    }

    /**
     * 参数为对象名
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T get(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }

    /**
     * 参数为class
     *
     * @param name
     * @return
     */
    public static Object get(String name) {
        return applicationContext.getBean(name);
    }
}
