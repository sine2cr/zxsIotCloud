package cn.sine2cr.zxsiotclouduserbackend;

import cn.sine2cr.zxsiotcloud.zxsiotcloudcommon.util.ComputerInfoUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;

import java.io.IOException;
import java.util.Objects;

@Slf4j
@SpringBootApplication
@EnableDubbo
@MapperScan(value = "cn.sine2cr.zxsiotclouduserbackend.mapper")
public class ZxsIotCloudUserBackendApplication {

    public static void main(String[] args) throws IOException {
        ApplicationContext context = SpringApplication.run(ZxsIotCloudUserBackendApplication.class, args);
        Environment environment = context.getEnvironment();
        int port = Integer.parseInt(Objects.requireNonNull(environment.getProperty("server.port")));
        log.info("用户中心外网地址:https://" + ComputerInfoUtil.getIpAddr() + ":" + port);
        log.info("用户中心内网地址:https://127.0.0.1:" + port);
    }

}
