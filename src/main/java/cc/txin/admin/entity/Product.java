package cc.txin.admin.entity;

import cc.txin.admin.model.ProductImageModel;
import cc.txin.common.entity.BaseEntity;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;


/**
 * Author:   lcl
 * Date:     2018/10/22 13:37
 * Description: 产品实体类
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Product extends BaseEntity {

    /**id**/
    private Long id;
    /**产品名字**/
    private String productName;
    /**系统型号**/
    private String model;
    /**处理器**/
    private String processor;
    /**芯片组**/
    private String chips;
    /**内存**/
    private String memory;
    /**储存功能**/
    private String storageFunction;
    /**硬盘插槽**/
   private String  hardDiskSlot;
   /**网络**/
   private String network;
   /**扩展插槽**/
   private String expansionSlots;
    /**I/O**/
   private String inputOutput;
   /**电源供应**/
   private String powerSupply;
   /**系统支持**/
   private String operatingSystem;
   /**整机尺寸**/
   private  String size;
   /**工作环境**/
   private String environmentalScience;
   /**产品类型**/
   private  String types;
   /**产品是否在首页显示**/
   private Boolean showImage;
    /**产品图片列表**/
   List<ProductImageModel> imageModelList;

}
