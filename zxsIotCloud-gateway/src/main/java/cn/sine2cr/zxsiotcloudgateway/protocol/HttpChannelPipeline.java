package cn.sine2cr.zxsiotcloudgateway.protocol;


import cn.sine2cr.zxsiotcloudgateway.handler.http.HttpReceiver;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.http.HttpContentCompressor;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;

/**
 * Http协议通道初始化器
 * @author Sine2cr
 * @Date 2024/2/20
 * @Mail sine2cr@163.com
 **/
public class HttpChannelPipeline implements CustomChannelPipeline{


    private HttpChannelPipeline() {

    }

    public static ChannelPipeline initChannel(ChannelPipeline pipeline)  {
        pipeline.addLast(new HttpRequestDecoder());
        pipeline.addLast(new HttpResponseEncoder());
        pipeline.addLast(new HttpContentCompressor());
        pipeline.addLast(new HttpObjectAggregator(65536));
        pipeline.addLast(new HttpReceiver());
        return pipeline;
    }
}
