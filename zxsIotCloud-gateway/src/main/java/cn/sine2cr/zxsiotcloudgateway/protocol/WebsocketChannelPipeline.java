package cn.sine2cr.zxsiotcloudgateway.protocol;


import cn.sine2cr.zxsiotcloudgateway.handler.websocket.WebsocketReceiver;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketFrameAggregator;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;

/**
 * @author Sine2cr
 * @Date 2024/2/20
 * @Mail sine2cr@163.com
 **/
public class WebsocketChannelPipeline {


    private WebsocketChannelPipeline() {

    }

    public static ChannelPipeline initChannel(ChannelPipeline pipeline)  {
        pipeline.addLast(new HttpServerCodec());
        pipeline.addLast(new WebSocketFrameAggregator(65536));
        pipeline.addLast(new WebSocketServerProtocolHandler("/"));
        pipeline.addLast(new WebsocketReceiver());
        return pipeline;
    }
}
