package cn.sine2cr.zxsiotcloudweb.controller;

import cn.sine2cr.zxsiotcloudcommon.common.BaseResponse;
import cn.sine2cr.zxsiotcloudcommon.util.ResponseUtil;
import cn.sine2cr.zxsiotcloudweb.model.entity.Device;
import cn.sine2cr.zxsiotcloudweb.model.request.device.DeviceDeleteRequest;
import cn.sine2cr.zxsiotcloudweb.model.request.device.DeviceRegisterRequest;
import cn.sine2cr.zxsiotcloudweb.model.request.device.DeviceUpdateRequest;
import cn.sine2cr.zxsiotcloudweb.service.DeviceService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Sine2cr
 * @Date 2024/1/13
 * @Mail sine2cr@163.com
 **/
@RestController
@RequestMapping("/device")
public class DeviceController {

    @Autowired
    private DeviceService deviceService;
    @PostMapping("/register")
    public BaseResponse<Long> deviceRegister(@RequestBody DeviceRegisterRequest deviceRegisterRequest){
        Device device = new Device();
        BeanUtils.copyProperties(deviceRegisterRequest,device);
        return ResponseUtil.success(deviceService.deviceRegister(device));
    }
    @PostMapping("/delete")
    public BaseResponse<Long> deviceDelete(@RequestBody DeviceDeleteRequest deviceDeleteRequest){
        Device device = new Device();
        BeanUtils.copyProperties(deviceDeleteRequest,device);
        return ResponseUtil.success(deviceService.deviceRegister(device));
    }
    @PostMapping("/update")
    public BaseResponse<Long> deviceUpdate(@RequestBody DeviceUpdateRequest deviceUpdateRequest){
        Device device = new Device();
        BeanUtils.copyProperties(deviceUpdateRequest,device);
        return ResponseUtil.success(deviceService.deviceRegister(device));
    }
//    @GetMapping("/list")
//    public BaseResponse<List<DeviceVO>> deviceList(@RequestBody DeviceUpdateRequest deviceUpdateRequest){
//        Device device = new Device();
//        BeanUtils.copyProperties(deviceUpdateRequest,device);
//        return ResponseUtil.success(deviceService.deviceRegister(device));
//    }
//    @GetMapping("/listType")
//    public BaseResponse<List<DeviceTypeVO>> deviceListType(){
//        return ResponseUtil.success(deviceService.listType());
//    }
}
