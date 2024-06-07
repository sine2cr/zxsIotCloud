package cn.sine2cr.zxsiotcloudgateway;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Netty服务启动类
 *
 * @author Sine2cr
 * @Date 2024/2/21
 * @Mail sine2cr@163.com
 **/
public class Server {

    private final int port;

    public Server(int port) {
        this.port = port;
    }

    /**
     * 网关启动方法
     * 可用于扩展多种启动方式
     */
    public void start()  {
        EventLoopGroup bossGroup = new NioEventLoopGroup(2);
        EventLoopGroup workerGroup = new NioEventLoopGroup(8);
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new MultiplexProtocolChannelInitializer())
                    .childOption(ChannelOption.SO_KEEPALIVE, true);
            ChannelFuture f = b.bind(port).sync();
            f.channel().closeFuture().sync();
        } catch (Exception e) {
            throw new RuntimeException("网关内部错误");
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }


}
