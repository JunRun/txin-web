package cc.txin.admin.entity;

import cc.txin.common.entity.BaseEntity;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author:   lcl
 * @Date:     2018/10/23 15:14
 * @Description: 系统参数实体类
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SystemParameterEntity extends BaseEntity {

    /** 参数编号 */

    private String id;
    /** 参数值 */

    private String value;
    /** 参数描述 */

    private String description;
    /** 参数排序 */

    private Integer seq;
    /** 参数类型id */

    private String typeId;
}
