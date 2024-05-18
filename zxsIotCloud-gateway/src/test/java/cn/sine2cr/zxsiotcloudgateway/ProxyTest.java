package cn.sine2cr.zxsiotcloudgateway;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ProxyTest {
    public static void main(String[] args)
    {
        ServerSocket server = null;
        try {
            server = new ServerSocket(18888);
            System.out.println("server start");
            Socket accept = server.accept();
            InputStream inputStream = accept.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String s;
            while (true) {
                if ((s=reader.readLine())!=null){;
                    System.out.println(s);
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }finally {
            try {
                server.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
