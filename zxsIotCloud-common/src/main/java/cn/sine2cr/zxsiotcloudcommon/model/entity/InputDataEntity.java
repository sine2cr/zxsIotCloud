package cn.sine2cr.zxsiotcloudcommon.model.entity;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * 设备上传数据类
 * @author Sine2cr
 * @Date 2024/3/22
 * @Mail sine2cr@163.com
 **/
@Data
public class InputDataEntity {
    /**
     * 设备ID
     */
    String deviceId;

    /**
     * 签名值
     */
    String signature;

    /**
     * 数据长度
     */
    String dataLength;

    /**
     * 优先级
     */
    String priority;

    /**
     * 发送时间
     */
    String timestamp;

    /**
     * 传输数据
     */
   String data;
}
