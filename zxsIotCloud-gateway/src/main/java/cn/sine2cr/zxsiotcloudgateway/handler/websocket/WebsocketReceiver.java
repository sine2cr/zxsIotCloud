package cn.sine2cr.zxsiotcloudgateway.handler.websocket;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import cn.hutool.crypto.symmetric.SymmetricCrypto;
import cn.sine2cr.zxsiotcloudcommon.model.entity.InputDataEntity;
import cn.sine2cr.zxsiotcloudcommon.model.vo.DeviceInfoVO;
import cn.sine2cr.zxsiotcloudgateway.handler.http.MessageSender;
import cn.sine2cr.zxsiotcloudgateway.service.RedisService;
import cn.sine2cr.zxsiotcloudgateway.util.SpringContextUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    private final RedisService redisService = SpringContextUtil.getBean(RedisService.class);
    private final WebsocketHandler websocketHandler = new WebsocketHandler();
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if(msg instanceof WebSocketFrame){
            WebSocketFrame message = ((WebSocketFrame) msg);
            String inputStr = message.content().toString(StandardCharsets.UTF_8);
            try {
                InputDataEntity inputData = jsonParse(inputStr, InputDataEntity.class);

                DeviceInfoVO device = redisService.getDevice(inputData.getDeviceId());
                //签名认证算法
                String fingerprint = device.getFingerprint();
                String signature = inputData.getSignature();
                SymmetricCrypto aes = new SymmetricCrypto(SymmetricAlgorithm.AES, fingerprint.getBytes());
                //解码传输数据为字符串形式
                String data = aes.decryptStr(inputData.getData() , CharsetUtil.CHARSET_UTF_8);
                //二次签名
                String serverSignature = SecureUtil.md5(device.getDeviceId() + data + fingerprint);
                if(serverSignature.equals(signature)){

                    if (device.getProxyIp()!=null){
                        websocketHandler.proxy(device.getProxyIp(), String.valueOf(device.getProxyPort()),data);
                    }else{
                        websocketHandler.saveToRedis(ctx,inputData);
                    }
                }else{
                    MessageSender.fail(ctx);
                }
            } catch (JsonProcessingException e) {
                MessageSender.fail(ctx);
                throw new RuntimeException(e);
            }
            MessageSender.success(ctx);
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
