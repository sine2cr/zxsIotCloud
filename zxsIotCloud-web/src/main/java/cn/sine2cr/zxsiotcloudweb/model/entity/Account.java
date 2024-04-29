package cn.sine2cr.zxsiotcloudweb.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 账户表
 * @TableName zxs_account
 */
@TableName(value ="zxs_account")
@Data
public class Account implements Serializable {

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
     * 实例数量
     */
    private Integer instanceCount;

    /**
     * 设备数量
     */
    private Integer deviceCount;

    /**
     * 当前状态(0:正常状态,1:禁用状态)
     */
    private Integer status;

    /**
     * 是否删除(0:正常,1:删除)
     */
    @TableLogic(value = "1")
    private Integer isDelete;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 创建时间
     */
    private Date createTime;

    @TableField(exist = false)
    private static final long serialVersionUID = -4039986271861684420L;
}
