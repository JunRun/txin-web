package cc.txin.admin.mapper;

import cc.txin.admin.model.ProductImageModel;
import cc.txin.common.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.List;

/**
 * Author:   lcl
 * Date:     2018/10/25 17:53
 * Description: 产品图片
 */
public interface ProductImageMapper extends BaseMapper<ProductImageModel> {
    /**
     * 获取对应产品的图片
     * @param id
     * @return
     */
    List<ProductImageModel> getByProductId(@Param("id") Serializable id);

    /**
     * 删除产品时删除对应的产品图片
     * @param id
     */
    void deleteByProductId(@Param("id") Serializable id);
}
