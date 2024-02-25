package cn.sine2cr.zxsiotclouduserbackend.service;

import cn.sine2cr.zxsiotclouduserbackend.model.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户服务
* @author sine2cr
* @createDate 2024-01-11
*/
public interface UserService extends IService<User> {
    /**
     * @param userName-用户名
     * @param userPassword-用户密码
     * @param checkPassword-校验密码
     * @return 用户平台账户
     */
    long userRegister(String userName, String userPassword, String checkPassword);

    /**
     * 用户登录
     *
     * @param userName  用户账户
     * @param userPassword 用户密码
     * @param request
     * @return 脱敏后的用户信息
     */
    User userLogin(String userName, String userPassword, HttpServletRequest request);

    /**
     * 获取当前登录用户
     *
     * @param request
     * @return
     */
    User getLoginUser(HttpServletRequest request);


    /**
     * 用户注销
     *
     * @param request
     * @return
     */
    boolean userLogout(HttpServletRequest request);

    /**
     * 是否为管理员
     *
     * @param request
     * @return
     */
    boolean isAdmin(HttpServletRequest request);
}
