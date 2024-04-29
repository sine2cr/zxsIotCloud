package cn.sine2cr.zxsiotcloudweb.service;

import cn.sine2cr.zxsiotcloudweb.model.entity.Instance;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * 用户实例服务
* @author sine2cr
* @createDate 2024-01-11
*/
public interface InstanceService extends IService<Instance> {

    /**
     * 注册平台实例
     * @param accountId
     * @param instanceName
     * @return long
     */
    long instanceRegister(long accountId, String instanceName);

    /**
     * 删除平台实例
     *
     * @param accountId
     * @param instanceName
     * @param instanceId
     * @return long
     */
    boolean delInstance(long accountId, String instanceName,long instanceId);

}
