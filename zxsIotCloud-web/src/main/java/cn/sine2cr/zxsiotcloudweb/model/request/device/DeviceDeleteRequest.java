package cn.sine2cr.zxsiotcloudweb.model.request.device;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Sine2cr
 * @Date 2024/1/13
 * @Mail sine2cr@163.com
 **/
@Data
public class DeviceDeleteRequest implements Serializable {

    private static final long serialVersionUID = -101580855596493244L;

    /**
     * 平台账户
     */
    private Long account;

    /**
     * 设备ID
     */
    private Long deviceId;

    /**
     * 设备名称
     */
    private String name;
}
