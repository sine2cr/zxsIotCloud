package cn.sine2cr.zxsiotcloudweb.model.request.user;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Sine2cr
 * @Date 2024/1/12
 * @Mail sine2cr@163.com
 **/
@Data
public class UserLoginRequest implements Serializable {

    private static final long serialVersionUID = 3191241716373120793L;

    private String userAccount;

    private String userPassword;
}
