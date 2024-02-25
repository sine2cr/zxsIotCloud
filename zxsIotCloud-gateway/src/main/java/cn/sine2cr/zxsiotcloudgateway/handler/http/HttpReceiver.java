package cn.sine2cr.zxsiotcloudgateway.handler.http;


import cn.sine2cr.zxsiotcloud.zxsiotcloudcommon.common.ErrorCode;
import cn.sine2cr.zxsiotcloud.zxsiotcloudcommon.exception.BusinessException;
import cn.sine2cr.zxsiotcloud.zxsiotcloudcommon.model.entity.DefaultTransducer;
import cn.sine2cr.zxsiotcloudgateway.constant.DeviceTypeConstant;
import cn.sine2cr.zxsiotcloudgateway.service.RedisService;
import cn.sine2cr.zxsiotcloudgateway.util.SpringContextUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.HttpContent;
import io.netty.handler.codec.http.HttpRequest;

import java.nio.charset.StandardCharsets;

public class HttpReceiver extends ChannelInboundHandlerAdapter {
    private final RedisService redisService = SpringContextUtil.getBean(RedisService.class);

    private final HttpHandler httpHandler = new HttpHandler();
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        if (msg instanceof HttpRequest) {
            HttpContent message = (HttpContent) msg;
            String json = message.content().toString(StandardCharsets.UTF_8);
            DefaultTransducer defaultTransducer;
            String deviceType;
            //判断设备类型
            try {
                defaultTransducer = jsonParse(json, DefaultTransducer.class);
                deviceType = redisService.getDeviceType(defaultTransducer.getDeviceId());
                switch (deviceType) {
                    case DeviceTypeConstant.TEMPERATURE:
                        httpHandler.temperatureTransducerHandler(ctx, json);
                        break;
                    case DeviceTypeConstant.HUMIDITY:
                        httpHandler.humidityTransducerHandler(ctx, json);
                        break;
                    default:
                        throw new BusinessException(ErrorCode.DATA_ERROR);
                }
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                ctx.channel().writeAndFlush(HttpResponse.failResponse(ErrorCode.TRANSDUCER_DATA_ERROR.getMessage()));
                throw new BusinessException(ErrorCode.HTTP_ERROR);
            } catch (Exception e) {
                e.printStackTrace();
                ctx.channel().writeAndFlush(HttpResponse.failResponse(ErrorCode.TRANSDUCER_DATA_ERROR.getMessage()));
                throw new BusinessException(ErrorCode.SYSTEM_ERROR);
            }
            ctx.channel().writeAndFlush(
                    HttpResponse.successResponse(
                            "设备:"+defaultTransducer.getDeviceId() + " success"));
        }
    }


    private <T extends Object> T jsonParse(String str, Class<T> clazz) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        //忽略多余字段
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        T t = objectMapper.readValue(str, clazz);
        return t;
    }


}
