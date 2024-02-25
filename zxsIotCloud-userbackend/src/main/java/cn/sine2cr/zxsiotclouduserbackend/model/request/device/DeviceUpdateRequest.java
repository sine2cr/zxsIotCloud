package cn.sine2cr.zxsiotclouduserbackend.model.request.device;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Sine2cr
 * @Date 2024/1/13
 * @Mail sine2cr@163.com
 **/
@Data
public class DeviceUpdateRequest implements Serializable {

    private static final long serialVersionUID = 2817295884416240010L;

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

    /**
     * 连接协议
     */
    private String connectType;

    /**
     * 设备序列号
     */
    private String sn;

    /**
     * 设备mac地址
     */
    private String mac;

    /**
     * 设备ip地址
     */
    private String ip;

    /**
     * 设备端口
     */
    private Integer port;

    /**
     * 设备状态(0:正常状态,1:禁用状态)
     */
    private Integer status;
}
