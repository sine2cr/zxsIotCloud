package cn.sine2cr.zxsiotcloudweb.model.request.user;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Sine2cr
 * @Date 2024/1/12
 * @Mail sine2cr@163.com
 **/
@Data
public class UserRegisterRequest implements Serializable {
    private static final long serialVersionUID = 618530239645765978L;
    private String userName;
    private String userPassword;
    private String checkPassword;
}
