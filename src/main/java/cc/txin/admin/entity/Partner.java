package cc.txin.admin.entity;

import cc.txin.common.entity.BaseEntity;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)


//合作伙伴
public class Partner extends BaseEntity {
    private Long id;//id

    private String name;//名字

    private String imageUrl;//图标路径

    private String intro;//简介




}
