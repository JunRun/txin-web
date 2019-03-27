package cc.txin.admin.mapper;

import cc.txin.admin.entity.SystemParameterEntity;
import cc.txin.common.mapper.BaseMapper;

import java.util.List;

/**
 * Author:   lcl
 * Date:     2018/10/23 15:42
 * Description: 系统参数映射
 */
public interface SystemParameterMapper extends BaseMapper<SystemParameterEntity> {

    /**
     * 通过参数类型查找参数list
     *
     * @date 2018年10月23日 下午3:20:05
     * @param type
     * @return
     */
    List<SystemParameterEntity> findByType(String type);

}
