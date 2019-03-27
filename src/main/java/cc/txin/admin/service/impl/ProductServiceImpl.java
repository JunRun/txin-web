package cc.txin.admin.service.impl;

import cc.txin.admin.entity.Product;
import cc.txin.admin.mapper.ProductMapper;
import cc.txin.admin.service.IProductService;
import cc.txin.common.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Author:   lcl
 * Date:     2018/10/22 15:26
 * Description: 接口实现类
 */
@Service("productService")
public class ProductServiceImpl extends BaseServiceImpl<Product> implements IProductService {
    @Autowired
    private ProductMapper productMapper;
    @Override
    public List<Product> findByParameter(Map<String,Object> map) {
        return productMapper.findByParameter(map);
    }


}
