package cn.sine2cr.zxsiotcloudweb.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 实例表
 *
 * @TableName zxs_instance
 */
@TableName(value = "zxs_instance")
@Data
public class Instance implements Serializable {
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
     * 实例ID
     */

    private Long instanceId;

    /**
     * 实例名称
     */

    private String instanceName;

    /**
     * 状态(0:正常状态,1:禁用状态)
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
    private static final long serialVersionUID = 5124502450911959924L;
}
