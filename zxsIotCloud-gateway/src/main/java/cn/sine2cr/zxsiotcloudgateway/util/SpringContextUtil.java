package cn.sine2cr.zxsiotcloudgateway.util;

import org.springframework.context.ApplicationContext;

/**
 * @author Sine2cr
 * @Date 2024/2/24
 * @Mail sine2cr@163.com
 **/
public class SpringContextUtil {

    private static ApplicationContext applicationContext = null;


    private SpringContextUtil() {
    }
    public static void setApplicationContext(ApplicationContext applicationContext){
        SpringContextUtil.applicationContext = applicationContext;
    }
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 通过名称获取 Bean
     * @param name
     * @return
     */
    public static Object getBean(String name) {
        return applicationContext.getBean(name);
    }

    /**
     * 通过类型获取 Bean
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T getBean(Class<T> clazz) {
        return applicationContext.getBean(clazz);
    }
}
