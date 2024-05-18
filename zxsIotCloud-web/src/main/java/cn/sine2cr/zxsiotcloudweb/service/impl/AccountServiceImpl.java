package cn.sine2cr.zxsiotcloudweb.service.impl;

import cn.sine2cr.zxsiotcloudweb.mapper.AccountMapper;
import cn.sine2cr.zxsiotcloudweb.model.entity.Account;
import cn.sine2cr.zxsiotcloudweb.service.AccountService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 平台账户服务
* @author sine2cr
* @createDate 2024-01-11
*/
@Service
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account>
    implements AccountService {
    @Resource
    AccountMapper accountMapper;


    @Override
    public boolean accountRegister(long accountId) {
        Account account = new Account();
        account.setAccount(accountId);
        return this.save(account);
    }


    @Override
    public int deviceCount(Long accountId) {
        QueryWrapper<Account> accountQueryWrapper = new QueryWrapper<>();
        accountQueryWrapper.eq("account", accountId);
        Account account =this.getOne(accountQueryWrapper,true);
        return account.getDeviceCount();
    }

    @Override
    public int instanceCount(Long accountId) {
        QueryWrapper<Account> accountQueryWrapper = new QueryWrapper<>();
        accountQueryWrapper.eq("account", accountId);
        Account account = accountMapper.selectOne(accountQueryWrapper);
        return account.getInstanceCount();
    }

    @Override
    public boolean updateInstanceCount(Long accountId, int num) {
        int count = this.deviceCount(accountId);
        UpdateWrapper<Account> accountUpdateWrapper = new UpdateWrapper<>();
        accountUpdateWrapper.eq("account",accountId);
        accountUpdateWrapper.set("instance_count",count+num);
        return this.update(accountUpdateWrapper);
    }

    @Override
    public boolean updateDeviceCount(Long accountId, int num) {
        int count = this.deviceCount(accountId);
        UpdateWrapper<Account> accountUpdateWrapper = new UpdateWrapper<>();
        accountUpdateWrapper.eq("account",accountId);
        accountUpdateWrapper.set("device_count",count+num);
        return this.update(accountUpdateWrapper);
    }
}




