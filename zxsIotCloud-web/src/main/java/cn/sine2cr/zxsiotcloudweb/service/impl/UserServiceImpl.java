package cn.sine2cr.zxsiotcloudweb.service.impl;


import cn.sine2cr.zxsiotcloudcommon.common.ErrorCode;
import cn.sine2cr.zxsiotcloudcommon.exception.BusinessException;
import cn.sine2cr.zxsiotcloudweb.mapper.UserMapper;
import cn.sine2cr.zxsiotcloudweb.model.entity.User;
import cn.sine2cr.zxsiotcloudweb.service.AccountService;
import cn.sine2cr.zxsiotcloudweb.service.IdService;
import cn.sine2cr.zxsiotcloudweb.service.UserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import static cn.sine2cr.zxsiotcloudweb.constant.UserConstant.USER_LOGIN_STATE;

/**
 * 用户服务
 * @author sine2cr
 * @createDate 2024-01-11
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {

    /**
     * 账户密码盐值
     */
    private static final String SALT = "sine2cr";
    @Resource
    private UserMapper userMapper;
    @Resource
    private AccountService accountService;
    @Resource
    private IdService idService;

    @Override
    public long userRegister(String userName, String userPassword, String checkPassword) {
        if (StringUtils.isAnyBlank(userName)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数为空");
        }
        checkPassword(userPassword, checkPassword);
        // TODO: 2024/1/12 修改账号生成算法
//        long account = new IdUtil(1, 1).nextId(); //目前写死状态
         long account = idService.nextId(); //目前写死状态
        String encryptPassword = DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes());
        User user = new User();
        user.setUserName(userName);
        user.setUserPassword(encryptPassword);
        user.setUserAccount(account);
        boolean b = this.save(user);
        boolean a = accountService.accountRegister(account);
        return (b && a) ? account : 0L;
    }

    @Override
    public User userLogin(String userName, String userPassword, HttpServletRequest request) {
        if (StringUtils.isAnyBlank(userName, userPassword)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数为空");
        }
        String encryptPassword = DigestUtils.md5DigestAsHex((SALT + userPassword).getBytes());
        // 查询用户是否存在
        User user;
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name", userName);
        queryWrapper.eq("user_password", encryptPassword);
        user = userMapper.selectOne(queryWrapper);
        // 用户不存在
        if (user == null) {
            log.info("用户不存在或密码错误");
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "用户不存在或密码错误");
        }
        //记录用户的登录态
        request.getSession().setAttribute(USER_LOGIN_STATE, user);
        return user;
    }

    @Override
    public User getLoginUser(HttpServletRequest request) {
        // 先判断是否已登录
        Object userObj = request.getSession().getAttribute(USER_LOGIN_STATE);
        User currentUser = (User) userObj;
        if (currentUser == null || currentUser.getUuid() == null) {
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
        }
        // TODO: 2024/1/12 将登录的用户存入redis这里改为直接查redis
        long userId = currentUser.getUuid();
        currentUser = this.getById(userId);
        if (currentUser == null) {
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
        }
        return currentUser;
    }

    @Override
    public boolean userLogout(HttpServletRequest request) {
        if (request.getSession().getAttribute(USER_LOGIN_STATE) == null) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "未登录");
        }
        // 移除登录态
        request.getSession().removeAttribute(USER_LOGIN_STATE);
        return true;
    }

    @Override
    public boolean isAdmin(HttpServletRequest request) {
        Object userObj = request.getSession().getAttribute(USER_LOGIN_STATE);
        User user = (User) userObj;
        return user != null && user.getIsManager() == 1;
    }

    private boolean checkPassword(String userPassword, String checkPassword) {
        if (StringUtils.isAnyBlank(userPassword, checkPassword)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "参数为空");
        }
        if (!userPassword.equals(checkPassword)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "两次输入的密码不一致");
        }
        return true;
    }
}




