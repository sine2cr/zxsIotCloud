package cn.sine2cr.zxsiotcloudgateway.handler.http;

import cn.sine2cr.zxsiotcloudcommon.model.entity.InputDataEntity;
import cn.sine2cr.zxsiotcloudgateway.service.ProxyService;
import cn.sine2cr.zxsiotcloudgateway.service.RedisService;
import cn.sine2cr.zxsiotcloudgateway.util.SpringContextUtil;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author Sine2cr
 * @Date 2024/2/24
 * @Mail sine2cr@163.com
 **/
public class HttpHandler {
    private final RedisService redisService = SpringContextUtil.getBean(RedisService.class);

    ProxyService proxyService = SpringContextUtil.getBean(ProxyService.class);
    public void saveToKafka(ChannelHandlerContext ctx, String json) {

    }

    public void saveToRedis(ChannelHandlerContext ctx, InputDataEntity inputData) {
        redisService.setDeviceData(inputData.getDeviceId(), Double.parseDouble(inputData.getTimestamp()), inputData);
    }


    public void proxy(String proxyIP, String proxyPort,String data){
        proxyService.proxy(proxyIP, proxyPort, data);
    }
}
