package cc.txin.admin.entity;

import cc.txin.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class Download extends BaseEntity {
    /**id**/
    private Long id;
    /**产品名字**/
    private String productName;
    /**产品类型**/
    private  String types;
    /**系统型号**/
    private String version;
    //文件名称
    private  String fileName;
    //版本号
    private String versionNumber;
    //文件附件
    private String accessory;
    //简介
    private String intro;
    //文件大小
    private Double fileSize;

}
