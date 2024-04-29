package cn.sine2cr.zxsiotcloudweb.service;

import cn.sine2cr.zxsiotcloudweb.model.entity.Device;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 用户设备服务
* @author sine2cr
* @createDate 2024-01-11
*/
public interface DeviceService extends IService<Device> {
    long deviceRegister(Device device);

    boolean delDevice(long accountId,  long deviceId);

    boolean modifyDevice(long accountId,  long deviceId, Device device);

//    List<DeviceTypeVO> listType();
}
