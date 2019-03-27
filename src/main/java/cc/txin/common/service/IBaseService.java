package cc.txin.common.service;

import cc.txin.admin.entity.User;
import cc.txin.common.model.PageModel;

import java.io.Serializable;
import java.util.List;

public interface IBaseService<T> {

	Integer insert(T t);

	Integer update(T t);

	Integer deleteById(Serializable id);

	T getById(Serializable id);

	List<T> getAll();

	Long findCountByQuery(T t);

	List<T> findLimitByQuery(T t);

	PageModel<T> findByQuery(T t);



}
