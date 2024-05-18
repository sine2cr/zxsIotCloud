package cn.sine2cr.zxsiotcloudweb.model.request.device;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Sine2cr
 * @Date 2024/1/13
 * @Mail sine2cr@163.com
 **/
@Data
public class DeviceRegisterRequest implements Serializable {

    private static final long serialVersionUID = 7337523256349014266L;

    /**
     * 平台账户
     */
    private Long account;

    /**
     * 设备名称
     */
    private String name;

    /**
     * 设备指纹
     */
    private String fingerprint;

    /**
     * 设备转发ip地址
     */
    private String proxyIp;

    /**
     * 设备转发端口号
     */
    private Integer proxyPort;

}
