package cc.txin.common.mapper;

import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.List;

public interface BaseMapper<T> {

	Integer insert(T t);

	Integer update(T t);

	Integer deleteById(@Param("id") Serializable id);

	T getById(@Param("id") Serializable id);

	List<T> getAll();

	Long findCountByQuery(T t);

	List<T> findLimitByQuery(T t);

}
