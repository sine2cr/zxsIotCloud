package cn.sine2cr.zxsiotcloudgateway.handler.websocket;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;

import java.nio.charset.StandardCharsets;

/**
 * @author Sine2cr
 * @Date 2024/2/20
 * @Mail sine2cr@163.com
 **/
public class WebsocketReceiver extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if(msg instanceof WebSocketFrame){
            String s = ((WebSocketFrame) msg).content().toString(StandardCharsets.UTF_8);
            ctx.writeAndFlush(new TextWebSocketFrame(s));
        }

    }
}
