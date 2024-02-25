package cn.sine2cr.zxsiotcloudgateway;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import org.springframework.util.ResourceUtils;


/**
 * @author Sine2cr
 * @Date 2024/2/21
 * @Mail sine2cr@163.com
 **/
public class MultiplexProtocolChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        SslContext sslContext = SslContextBuilder.forServer(
                ResourceUtils.getFile("classpath:cert/cert.pem"),
                ResourceUtils.getFile("classpath:cert/private.key")
        ).build();
        pipeline.addLast(sslContext.newHandler(socketChannel.alloc()));
        pipeline.addLast(new ProtocolIdentifierAdapter());

    }
}
