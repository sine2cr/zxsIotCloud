package cn.sine2cr.zxsiotcloudweb.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户表
 * @TableName zxs_user
 */
@TableName(value ="zxs_user")
@Data
public class User implements Serializable {
    /**
     * UUID
     */
    @TableId
    private Long uuid;

    /**
     * 平台账户
     */
    private Long userAccount;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 用户密码
     */
    private String userPassword;

    /**
     * 当前状态(0:正常状态,1:禁用状态)
     */
    private Integer userStatus;

    /**
     * 是否为管理员账户(0:普通用户,1:管理员)
     */
    private Integer isManager;

    /**
     * 是否删除(0:正常,1:删除)
     */
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
    private static final long serialVersionUID = 7103954367828901866L;
}
