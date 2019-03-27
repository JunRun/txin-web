package cc.txin.admin.service;

import cc.txin.admin.model.ProductImageModel;
import cc.txin.common.service.IBaseService;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.List;

/**
 * Author:   lcl
 * Date:     2018/10/25 18:04
 * Description: 产品图片接口
 */
public interface IProductImageService extends IBaseService<ProductImageModel> {
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
