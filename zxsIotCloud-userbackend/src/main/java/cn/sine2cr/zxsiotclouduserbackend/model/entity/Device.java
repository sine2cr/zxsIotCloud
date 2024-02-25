package cn.sine2cr.zxsiotclouduserbackend.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 设备表
 *
 * @TableName zxs_device
 */
@TableName(value = "zxs_device")
@Data
public class Device implements Serializable {
    /**
     * UUID
     */
    @TableId
    private Long uuid;

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
     *  设备类型
     */

    private String type;

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

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 创建时间
     */
    private Date createTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 820631033890065008L;
}
