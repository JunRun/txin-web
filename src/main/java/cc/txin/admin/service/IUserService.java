package cc.txin.admin.service;

import cc.txin.admin.entity.User;
import cc.txin.common.service.IBaseService;


public interface IUserService extends IBaseService<User> {

  public User findByUsername(String username, String password);


}
