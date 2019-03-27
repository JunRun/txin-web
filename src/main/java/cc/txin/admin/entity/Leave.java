package cc.txin.admin.entity;

import cc.txin.common.entity.BaseEntity;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class Leave extends BaseEntity {
    //id
    private Long id;
    //名字
    private String name;
    //电话号码
    private String mobile;
    //qq
    private String qq;
    //eml
    private String email;
    //留言
    private String content;



}
