package cn.sine2cr.zxsiotcloudgateway.schedule;

import cn.sine2cr.zxsiotcloud.zxsiotcloudcommon.model.vo.DeviceTypeVO;
import cn.sine2cr.zxsiotcloud.zxsiotcloudcommon.service.InnerDeviceService;
import cn.sine2cr.zxsiotcloudgateway.constant.RedisTypeConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;

/**
 * @author Sine2cr
 * @Date 2024/2/24
 * @Mail sine2cr@163.com
 **/
@Slf4j
@Component
public class InitDeviceTypeTask {
    @Resource
    private RedisTemplate<String, String> redisTemplate;
    @DubboReference
    private InnerDeviceService innerDeviceService;


    /**
     * 程序启动后使用dubbo获取设备类型存入redis中
     */
    @PostConstruct
    public void initDeviceType() {
        List<DeviceTypeVO> deviceTypeVOS = innerDeviceService.listType();
        deviceTypeVOS.forEach(deviceTypeVO -> redisTemplate.
                opsForHash().put(
                        RedisTypeConstant.REDIS_TYPE_KEY,
                        deviceTypeVO.getDeviceId(),
                        deviceTypeVO.getType())
        );
//        redisTemplate.expire(RedisTypeConstant.REDIS_TYPE_KEY, Long.MAX_VALUE, TimeUnit.SECONDS);
        log.info("设备类型初始化完成");
    }
}
