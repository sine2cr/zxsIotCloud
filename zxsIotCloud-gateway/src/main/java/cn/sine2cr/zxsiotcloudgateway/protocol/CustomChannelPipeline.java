package cn.sine2cr.zxsiotcloudgateway.protocol;

import io.netty.channel.ChannelPipeline;

/**
 * @author Sine2cr
 * @Date 2024/3/3
 * @Mail sine2cr@163.com
 **/
public interface CustomChannelPipeline {
    static ChannelPipeline initChannel(ChannelPipeline pipeline) {
        return pipeline;
    }
}
