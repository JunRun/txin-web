package cc.txin.admin.service;

import cc.txin.admin.entity.Product;
import cc.txin.common.service.IBaseService;

import java.util.List;
import java.util.Map;

/**
 * Author:   lcl
 * Date:     2018/10/22 15:23
 * Description: 产品接口
 */
public interface IProductService extends IBaseService<Product> {
    List<Product> findByParameter(Map<String,Object> map) ;
}
