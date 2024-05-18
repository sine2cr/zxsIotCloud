package cn.sine2cr.zxsiotcloudweb.schedule;

import cn.sine2cr.zxsiotcloudcommon.constant.RedisKeyConstant;
import cn.sine2cr.zxsiotcloudcommon.model.vo.DeviceInfoVO;
import cn.sine2cr.zxsiotcloudweb.model.entity.Device;
import cn.sine2cr.zxsiotcloudweb.service.DeviceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Sine2cr
 * @Date 2024/5/2
 * @Mail sine2cr@163.com
 */
@Slf4j
@Component
public class InitDeviceInfoTask {
    @Resource
    private RedisTemplate<String, String> redisTemplate;
    @Resource
    private DeviceService deviceService;

    private List<DeviceInfoVO> loadDeviceInfo(){
        List<Device> deviceList =deviceService.list();
        List<DeviceInfoVO> deviceInfoVOs = new ArrayList<>(deviceList.size() + 4);
        deviceList.forEach(device -> {
            DeviceInfoVO deviceInfoVO = new DeviceInfoVO();
            deviceInfoVO.setDeviceId(String.valueOf(device.getDeviceId()));
            deviceInfoVO.setFingerprint(device.getFingerprint());
            deviceInfoVO.setProxyIp(device.getProxyIp());
            deviceInfoVO.setProxyPort(device.getProxyPort());
            deviceInfoVOs.add(deviceInfoVO);
        });
        return deviceInfoVOs;
    }

    @PostConstruct
    public void initDeviceInfo() {
        List<DeviceInfoVO> deviceInfoVOs = loadDeviceInfo();
        deviceInfoVOs.forEach(deviceInfoVO -> redisTemplate.
                opsForHash().put(
                        RedisKeyConstant.REDIS_INFO_KEY,
                        deviceInfoVO.getDeviceId(),
                        deviceInfoVO
                        )
        );
//        redisTemplate.expire(RedisKeyConstant.REDIS_TYPE_KEY, Long.MAX_VALUE, TimeUnit.SECONDS);
        log.info("设备信息初始化完成");
    }
}
