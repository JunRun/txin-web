package cc.txin.admin.entity;

import cc.txin.common.entity.BaseEntity;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Author:   lcl
 * Date:     2018/10/23 15:16
 * Description: 系统参数类型实体类
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SystemParameterTypeEntity extends BaseEntity {

    /** 参数类型编号 */

    private String id;
    /** 参数类型名称 */

    private String name;
    /** 参数类型描述 */

    private String description;
    /** 参数类型排序 */

    private Integer seq;
}
