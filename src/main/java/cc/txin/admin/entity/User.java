package cc.txin.admin.entity;

import cc.txin.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class User extends BaseEntity {

    private Long id;//id

    private String username;//用户名字

    private String password;//用户密码

    private String realname;//真实姓名

    private String mobile;//电话

    private String email;//电子邮箱
}
