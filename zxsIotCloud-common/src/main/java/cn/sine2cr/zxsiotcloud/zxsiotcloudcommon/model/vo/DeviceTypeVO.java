package cn.sine2cr.zxsiotcloud.zxsiotcloudcommon.model.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Sine2cr
 * @Date 2024/2/23
 * @Mail sine2cr@163.com
 **/
@Data
public class DeviceTypeVO implements Serializable {

    private static final long serialVersionUID = -7523370039900584902L;

    private String deviceId;

    private String type;
}
