package cn.sine2cr.zxsiotcloudweb.service.impl;

import cn.sine2cr.zxsiotcloudweb.mapper.InstanceMapper;
import cn.sine2cr.zxsiotcloudweb.model.entity.Instance;
import cn.sine2cr.zxsiotcloudweb.service.IdService;
import cn.sine2cr.zxsiotcloudweb.service.InstanceService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 用户实例服务
 * @author sine2cr
 * @createDate 2024-01-11
 */
@Service
public class InstanceServiceImpl extends ServiceImpl<InstanceMapper, Instance>
        implements InstanceService {
    @Resource
    private IdService idService;

    @Resource
    private InstanceMapper instanceMapper;

    @Override
    public long instanceRegister(long accountId, String instanceName) {
        long id = idService.nextId();
        Instance instance = new Instance();
        instance.setAccount(accountId);
        instance.setInstanceName(instanceName);
        instance.setInstanceId(id);
        boolean b = this.save(instance);
        return b ? id : 0;
    }

    @Override
    public boolean delInstance(long accountId, String instanceName, long instanceId) {
        QueryWrapper<Instance> instanceQueryWrapper = new QueryWrapper<>();
        instanceQueryWrapper.eq("account_id", accountId);
        instanceQueryWrapper.eq("instance_name", instanceName);
        instanceQueryWrapper.eq("instance_id", instanceId);

        return instanceMapper.delete(instanceQueryWrapper) > 0;
    }
}




