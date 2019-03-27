package cc.txin.admin.service.impl;

import cc.txin.admin.entity.SystemParameterEntity;
import cc.txin.admin.mapper.SystemParameterMapper;
import cc.txin.admin.service.ISystemParameterService;
import cc.txin.common.service.impl.BaseServiceImpl;
import cc.txin.common.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author:   lcl
 * Date:     2018/10/23 15:28
 * Description: 系统参数服务接口实现类
 */
@Service("systemParameterService")
public class SystemParameterServiceImpl extends BaseServiceImpl<SystemParameterEntity> implements ISystemParameterService {

    @Autowired
    private SystemParameterMapper systemParameterDao;

    @Override
    @CacheEvict(value = "systemParameter", key = "'systemParameter_' + #systemParameterEntity.typeId")
    public void deleteById(SystemParameterEntity systemParameterEntity) {
        super.deleteById(systemParameterEntity.getId());
    }

    @Override
    @Cacheable(value = "systemParameter", key = "'systemParameter_' + #type")
    public List<SystemParameterEntity> findByType(String type) {
        Assert.notNull(type, "请上送系统参数类型");
        return systemParameterDao.findByType(type);
    }


}
