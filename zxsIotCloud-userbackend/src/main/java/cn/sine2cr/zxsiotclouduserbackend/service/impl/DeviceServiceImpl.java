package cn.sine2cr.zxsiotclouduserbackend.service.impl;

import cn.sine2cr.zxsiotcloud.zxsiotcloudcommon.common.ErrorCode;
import cn.sine2cr.zxsiotcloud.zxsiotcloudcommon.exception.BusinessException;
import cn.sine2cr.zxsiotclouduserbackend.mapper.DeviceMapper;
import cn.sine2cr.zxsiotclouduserbackend.model.entity.Device;
import cn.sine2cr.zxsiotclouduserbackend.service.AccountService;
import cn.sine2cr.zxsiotclouduserbackend.service.DeviceService;
import cn.sine2cr.zxsiotclouduserbackend.service.IdService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 用户设备业务
 *
 * @author sine2cr
 * @createDate 2024-01-11
 */
@Service
public class DeviceServiceImpl extends ServiceImpl<DeviceMapper, Device>
        implements DeviceService {
    @Resource
    private IdService idService;
    @Resource
    private DeviceMapper deviceMapper;
    @Resource
    private AccountService accountService;

    @Override
    public long deviceRegister(Device device) {
        if (device == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "业务参数异常");
        }
        accountService.updateDeviceCount(device.getAccount(), 1);
        return this.save(device) ? device.getDeviceId() : 0;
    }

    @Override
    public boolean delDevice(long accountId, long deviceId) {
        if (accountId == 0 || deviceId == 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "业务参数异常");
        }
        QueryWrapper<Device> deviceQueryWrapper = new QueryWrapper<>();
        deviceQueryWrapper.eq("device_id", deviceId);
        deviceQueryWrapper.eq("account", accountId);
        return this.remove(deviceQueryWrapper) && accountService.updateDeviceCount(accountId, -1);
    }

    @Override
    public boolean modifyDevice(long accountId, long deviceId, Device device) {
        if (accountId == 0 || deviceId == 0 || device == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "业务参数异常");
        }
        UpdateWrapper<Device> deviceUpdateWrapper = new UpdateWrapper<>();
        deviceUpdateWrapper.eq("account", accountId);
        deviceUpdateWrapper.eq("device_id", deviceId);
        int r = deviceMapper.update(device, deviceUpdateWrapper);
        return r > 0;
    }


}




