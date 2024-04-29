package cn.sine2cr.zxsiotcloudweb.service.impl;

import cn.sine2cr.zxsiotcloudweb.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @author Sine2cr
 * @Date 2024/1/12
 * @Mail sine2cr@163.com
 **/
@SpringBootTest
class UserServiceImplTest {
    @Autowired
    UserService userService;

    @Test
    void userRegister() {
        userService.userRegister("test111", "123456", "123456");
    }

    @Test
    void userLogin() {
        userService.userLogin("test", "123456",((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest());
    }

    @Test
    void getLoginUser() {
        userService.getLoginUser(((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest());
    }

    @Test
    void userLogout() {
        userService.userLogout(((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest());
    }

    @Test
    void isAdmin() {
        System.out.println(userService.isAdmin(((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest()));
    }
}
