package cn.sine2cr.zxsiotclouduserbackend.service;

import cn.sine2cr.zxsiotclouduserbackend.model.entity.Account;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 平台账户服务
 * @author sine2cr
 * @createDate 2024-01-11
 */
public interface AccountService extends IService<Account> {
    /**
     * 注册平台账户
     *
     * @param accountName
     * @param accountPassword
     * @param checkPassword
     * @return long
     */
    boolean accountRegister(long accountId);


    /**
     * 获取用户平台账号设备数量
     *
     * @param accountId
     * @return int
     */
    int deviceCount(Long accountId);

    /**
     * 获取用户平台账号实例数量
     *
     * @param accountId
     * @return int
     */
    int instanceCount(Long accountId);



    /**
     * 修改用户平台账号实例数量
     * @param accountId
     * @param num 正数为增加，负数为减少
     * @return int
     */
    boolean updateInstanceCount(Long accountId, int num);

    /**
     * 修改用户平台账号设备数量
     * @param accountId
     * @param num 正数为增加，负数为减少
     * @return int
     */
    boolean updateDeviceCount(Long accountId, int num);
}
