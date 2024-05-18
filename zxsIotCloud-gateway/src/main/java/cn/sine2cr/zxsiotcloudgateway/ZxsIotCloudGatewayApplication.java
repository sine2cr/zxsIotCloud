package cn.sine2cr.zxsiotcloudgateway;


import cn.sine2cr.zxsiotcloudcommon.util.ComputerInfoUtil;
import cn.sine2cr.zxsiotcloudgateway.util.SpringContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.IOException;
import java.util.Objects;

@Slf4j
//@EnableDubbo
@EnableScheduling
@SpringBootApplication
public class ZxsIotCloudGatewayApplication {

    public static void main(String[] args) throws IOException {
        ApplicationContext context = SpringApplication.run(ZxsIotCloudGatewayApplication.class, args);
        Environment environment = context.getEnvironment();
        int port = Integer.parseInt(Objects.requireNonNull(environment.getProperty("gateway.port")));
        log.info("网关外网地址:https://" + ComputerInfoUtil.getIpAddr() + ":" + port);
        log.info("网关内网地址:https://127.0.0.1:" + port);
        SpringContextUtil.setApplicationContext(context);
        new Server(port).start();
    }

}
