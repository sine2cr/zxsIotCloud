package cn.sine2cr.zxsiotcloudweb.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户视图
 * @author Sine2cr
 * @Date 2024/1/12
 * @Mail sine2cr@163.com
 **/
@Data
public class UserVO implements Serializable {
    private static final long serialVersionUID = -8502708542490129482L;

    /**
     * 平台账户
     */
    private Long userAccount;

    /**
     * 用户名
     */
    private String userName;


    /**
     * 当前状态(0:正常状态,1:禁用状态)
     */
    private Integer userStatus;

    /**
     * 是否为管理员账户(0:普通用户,1:管理员)
     */
    private Integer isManager;


    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 创建时间
     */
    private Date createTime;
}
