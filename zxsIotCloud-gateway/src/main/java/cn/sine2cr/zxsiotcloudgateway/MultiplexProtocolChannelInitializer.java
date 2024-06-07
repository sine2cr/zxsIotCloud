package cn.sine2cr.zxsiotcloudgateway;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import org.springframework.util.ResourceUtils;


/**
 * 自定义多协议通道初始化器
 * @author Sine2cr
 * @Date 2024/2/21
 * @Mail sine2cr@163.com
 **/
public class MultiplexProtocolChannelInitializer extends ChannelInitializer<SocketChannel> {

    /**
     * 初始化通道添加ssl解密和协议适配器
     * @param socketChannel
     * @throws Exception
     */
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        //加载密钥
        SslContext sslContext = SslContextBuilder.forServer(
                ResourceUtils.getFile("classpath:cert/cert.pem"),
                ResourceUtils.getFile("classpath:cert/private.key")
        ).build();
        //添加SSL加密套件、协议适配器
        pipeline.addLast(sslContext.newHandler(socketChannel.alloc()));
        pipeline.addLast(new ProtocolIdentifierAdapter());

    }
}
