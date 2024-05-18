package cn.sine2cr.zxsiotcloudcommon.model.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Sine2cr
 * @Date 2024/5/2
 * @Mail sine2cr@163.com
 **/
@Data
public class DeviceInfoVO  implements Serializable {

    private static final long serialVersionUID = -1498315597531980889L;

    private String deviceId;

    private String fingerprint;

    private String proxyIp;

    private Integer proxyPort;
}
