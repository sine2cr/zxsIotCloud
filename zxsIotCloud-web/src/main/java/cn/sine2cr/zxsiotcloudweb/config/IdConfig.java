package cn.sine2cr.zxsiotcloudweb.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * id配置
 * @author Sine2cr
 * @Date 2024/1/13
 * @Mail sine2cr@163.com
 **/
@Component
public class IdConfig {
    @Value("${userCenter.workerId}")
    private  long workerId ;

    @Value("${userCenter.centerId}")
    private  long datacenterId ;
    @Bean
    public long workerId() {
        return workerId;
    }
    @Bean
    public long datacenterId() {
        return datacenterId;
    }
}
