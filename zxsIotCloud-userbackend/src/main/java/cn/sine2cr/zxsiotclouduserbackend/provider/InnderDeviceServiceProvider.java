package cn.sine2cr.zxsiotclouduserbackend.provider;

import cn.sine2cr.zxsiotcloud.zxsiotcloudcommon.model.vo.DeviceTypeVO;
import cn.sine2cr.zxsiotcloud.zxsiotcloudcommon.service.InnerDeviceService;
import cn.sine2cr.zxsiotclouduserbackend.model.entity.Device;
import cn.sine2cr.zxsiotclouduserbackend.service.DeviceService;
import org.apache.dubbo.config.annotation.DubboService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Sine2cr
 * @Date 2024/2/23
 * @Mail sine2cr@163.com
 **/

@DubboService(registry="ns-Registry")
public class InnderDeviceServiceProvider implements InnerDeviceService {
    @Resource
    private DeviceService deviceService;
    @Override
    public List<DeviceTypeVO> listType() {
        List<Device> list = deviceService.list();
        List<DeviceTypeVO> deviceTYpeVOS = new ArrayList<>(list.size() + 4);
        list.forEach(device -> {
            DeviceTypeVO deviceTypeVO = new DeviceTypeVO();
            deviceTypeVO.setDeviceId(String.valueOf(device.getDeviceId()));
            deviceTypeVO.setType(device.getType());
            deviceTYpeVOS.add(deviceTypeVO);
        });

        return deviceTYpeVOS;
    }
}
