package cc.txin.admin.mapper;

import cc.txin.admin.entity.User;
import cc.txin.common.mapper.BaseMapper;

public interface UserMapper extends BaseMapper<User> {
   public User findByUsername(String username);
}
