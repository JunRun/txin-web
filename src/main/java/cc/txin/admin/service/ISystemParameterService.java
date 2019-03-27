package cc.txin.admin.service;

import cc.txin.admin.entity.SystemParameterEntity;
import cc.txin.common.service.IBaseService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author:   lcl
 * Date:     2018/10/23 15:18
 * Description: 系统参数服务类
 */

public interface ISystemParameterService extends IBaseService<SystemParameterEntity> {

    /**
     * 删除系统参数
     * @param systemParameterEntity
     */
    void deleteById(SystemParameterEntity systemParameterEntity);
    /**
     * 通过参数类型查找参数list
     * @param type
     * @return
     */
    List<SystemParameterEntity> findByType(String type);

}
