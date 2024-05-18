package cn.sine2cr.zxsiotcloudweb;

import cn.sine2cr.zxsiotcloudcommon.util.ComputerInfoUtil;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;

import java.io.IOException;
import java.util.Objects;

@Slf4j
@SpringBootApplication
@MapperScan(value = "cn.sine2cr.zxsiotcloudweb.mapper")
public class ZxsIotCloudWebApplication {

    public static void main(String[] args) throws IOException {
        ApplicationContext context = SpringApplication.run(ZxsIotCloudWebApplication.class, args);
        Environment environment = context.getEnvironment();
        int port = Integer.parseInt(Objects.requireNonNull(environment.getProperty("server.port")));
        log.info("Web中心外网地址:http://" + ComputerInfoUtil.getIpAddr() + ":" + port);
        log.info("Web中心内网地址:http://127.0.0.1:" + port);
    }

}
