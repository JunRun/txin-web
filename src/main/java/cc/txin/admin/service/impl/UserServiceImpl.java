package cc.txin.admin.service.impl;

import cc.txin.admin.entity.User;
import cc.txin.admin.mapper.UserMapper;
import cc.txin.admin.service.IUserService;
import cc.txin.common.service.impl.BaseServiceImpl;
import cc.txin.common.util.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl extends BaseServiceImpl<User> implements IUserService  {

    @Autowired
    UserMapper userMapper;

    @Override
    public User  findByUsername(String username, String password) {
        User user=userMapper.findByUsername(username);
        password = PasswordUtil.encript(password.trim());
        if (user != null && password.equals(user.getPassword())) {
            return user;
        }
        return null;

    }



}
