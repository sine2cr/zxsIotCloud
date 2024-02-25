package cn.sine2cr.zxsiotcloudgateway.util;

import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;

/**
 * @author Sine2cr
 * @Date 2024/2/21
 * @Mail sine2cr@163.com
 **/

public class EntityParseUtil {
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    private EntityParseUtil() {
    }

    public Class<Object> parseEntity(String deviceID) {
        redisTemplate.opsForHash().put("device", deviceID, "1");
        return null;
    }

}
