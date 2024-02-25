package cn.sine2cr.zxsiotcloudgateway.service;

import cn.sine2cr.zxsiotcloud.zxsiotcloudcommon.common.ErrorCode;
import cn.sine2cr.zxsiotcloud.zxsiotcloudcommon.exception.BusinessException;
import cn.sine2cr.zxsiotcloudgateway.constant.RedisTypeConstant;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author Sine2cr
 * @Date 2024/2/24
 * @Mail sine2cr@163.com
 **/

@Component
public class RedisService {

    @Resource
    public RedisTemplate<String, Object> redisObjectTemplate;
    @Resource
    public RedisTemplate<String, String> redisStringTemplate;


    /**
     * 在redis中获取设备类型
     *
     * @param deviceId
     * @return
     */
    public String getDeviceType(String deviceId) {

        Object result = redisStringTemplate.opsForHash().get(RedisTypeConstant.REDIS_TYPE_KEY, deviceId);
        if (result == null) {
            throw new BusinessException(ErrorCode.DATA_ERROR);
        }
        //获取的字符串带有双引号，需要去掉
        return  result.toString().substring(1,result.toString().length() - 1);
    }

    public boolean setDeviceData(String deviceId, double score, String data) {
        return Boolean.TRUE.equals(redisStringTemplate.opsForZSet().add(deviceId, data, score));
    }
    public boolean setDeviceData(String deviceId, double score, Object data) {
        return Boolean.TRUE.equals(redisObjectTemplate.opsForZSet().add(deviceId, data, score));
    }
}
