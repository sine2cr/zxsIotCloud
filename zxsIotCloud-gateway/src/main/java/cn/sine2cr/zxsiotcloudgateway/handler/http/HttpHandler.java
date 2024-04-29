package cn.sine2cr.zxsiotcloudgateway.handler.http;

import cn.sine2cr.zxsiotcloudcommon.common.ErrorCode;
import cn.sine2cr.zxsiotcloudcommon.exception.BusinessException;
import cn.sine2cr.zxsiotcloudcommon.model.entity.HumidityTransducer;
import cn.sine2cr.zxsiotcloudcommon.model.entity.TemperatureTransducer;
import cn.sine2cr.zxsiotcloudgateway.service.RedisService;
import cn.sine2cr.zxsiotcloudgateway.util.SpringContextUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author Sine2cr
 * @Date 2024/2/24
 * @Mail sine2cr@163.com
 **/
public class HttpHandler {
    private final RedisService redisService = SpringContextUtil.getBean(RedisService.class);
    public void temperatureTransducerHandler(ChannelHandlerContext ctx, String json) {
        TemperatureTransducer temperatureTransducer = null;
        try {
            temperatureTransducer = jsonParse(json, TemperatureTransducer.class);
        } catch (JsonProcessingException e) {
            ctx.channel().writeAndFlush(HttpResponse.failResponse(ErrorCode.TRANSDUCER_DATA_ERROR.getMessage()));
            throw new BusinessException(ErrorCode.TRANSDUCER_DATA_ERROR);
        }
        redisService.setDeviceData(temperatureTransducer.getDeviceId(),
                Double.parseDouble(temperatureTransducer.getTimestamp()),
                temperatureTransducer);
    }

    public void humidityTransducerHandler(ChannelHandlerContext ctx, String json) {
        HumidityTransducer humidityTransducer = null;
        try {
            humidityTransducer = jsonParse(json, HumidityTransducer.class);
        } catch (JsonProcessingException e) {
            ctx.channel().writeAndFlush(HttpResponse.failResponse(ErrorCode.TRANSDUCER_DATA_ERROR.getMessage()));
            throw new BusinessException(ErrorCode.TRANSDUCER_DATA_ERROR);
        }
        redisService.setDeviceData(humidityTransducer.getDeviceId(),
                Double.parseDouble(humidityTransducer.getTimestamp()),
                humidityTransducer);
    }
    private <T> T jsonParse(String str, Class<T> clazz) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        //忽略多余字段
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper.readValue(str, clazz);
    }
}
