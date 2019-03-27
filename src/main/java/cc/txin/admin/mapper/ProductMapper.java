package cc.txin.admin.mapper;

import cc.txin.admin.entity.Product;
import cc.txin.common.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Author:   lcl
 * Date:     2018/10/22 15:41
 * Description: 产品映射
 */
public interface ProductMapper extends BaseMapper<Product> {
   List<Product> findByParameter(Map<String,Object> map);
}
