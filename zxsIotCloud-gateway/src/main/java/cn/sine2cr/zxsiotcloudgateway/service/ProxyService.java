package cn.sine2cr.zxsiotcloudgateway.service;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * 数据转发服务类
 *
 * @author sine2cr
 */
@Component
public class ProxyService {
    public void proxy(String proxyIP, String proxyPort,String data){
        //todo 找到一个合适的转发方式，提高转发效率
        Socket client = null;
        try {
            client = new Socket(proxyIP,Integer.parseInt(proxyPort));
            OutputStream ops = client.getOutputStream();
            OutputStreamWriter writer = new OutputStreamWriter(ops);
            writer.write(data);
            writer.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                client.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
