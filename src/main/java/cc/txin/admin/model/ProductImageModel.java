package cc.txin.admin.model;

import cc.txin.common.entity.BaseEntity;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Author:   lcl
 * Date:     2018/10/25 17:48
 * Description: 产品图片model
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ProductImageModel extends BaseEntity {
    /**id**/
   private Long id;
    /**图片地址**/
    private String imageUrl;
    /**产品id**/
    private  Long productId;


}

