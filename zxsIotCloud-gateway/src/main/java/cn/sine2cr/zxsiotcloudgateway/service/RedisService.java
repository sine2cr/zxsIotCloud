package cn.sine2cr.zxsiotcloudgateway.service;

import cn.sine2cr.zxsiotcloudcommon.common.ErrorCode;
import cn.sine2cr.zxsiotcloudcommon.exception.BusinessException;
import cn.sine2cr.zxsiotcloudcommon.constant.RedisKeyConstant;
import cn.sine2cr.zxsiotcloudcommon.model.vo.DeviceInfoVO;
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
     * 在redis中获取设备对象
     *
     * @param deviceId
     * @return
     */
    public DeviceInfoVO getDevice(String deviceId) {

        Object result = redisObjectTemplate.opsForHash().get(RedisKeyConstant.REDIS_INFO_KEY, deviceId);
        if (result == null) {
            throw new BusinessException(ErrorCode.DATA_ERROR);
        }
        DeviceInfoVO r = (DeviceInfoVO) result;
        return  r;
    }

    /**
     * 添加输入数据
     * @param deviceId
     * @param score
     * @param data
     * @return
     */
    public boolean setDeviceData(String deviceId, double score, String data) {
        return Boolean.TRUE.equals(redisStringTemplate.opsForZSet().add(deviceId, data, score));
    }

    /**
     * 添加输入数据对象
     * @param deviceId
     * @param score
     * @param data
     * @return
     */
    public boolean setDeviceData(String deviceId, double score, Object data) {
        return Boolean.TRUE.equals(redisObjectTemplate.opsForZSet().add(deviceId, data, score));
    }
}
