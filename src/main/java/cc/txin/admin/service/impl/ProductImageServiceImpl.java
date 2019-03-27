package cc.txin.admin.service.impl;

import cc.txin.admin.mapper.ProductImageMapper;
import cc.txin.admin.model.ProductImageModel;
import cc.txin.admin.service.IProductImageService;
import cc.txin.common.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * Author:   lcl
 * Date:     2018/10/25 18:07
 * Description: 产品图片映射接口实现类
 */
@Service("productImageService")
public class ProductImageServiceImpl extends BaseServiceImpl<ProductImageModel> implements IProductImageService {
   @Autowired
   private ProductImageMapper productImageMapper;

    @Override
    public List<ProductImageModel> getByProductId(Serializable id) {
       return productImageMapper.getByProductId(id);
    }

    @Override
    public void deleteByProductId(Serializable id) {
        productImageMapper.deleteByProductId(id);
    }
}
