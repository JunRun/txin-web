package cc.txin.common.service.impl;

import cc.txin.common.mapper.BaseMapper;
import cc.txin.common.model.PageModel;
import cc.txin.common.service.IBaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * service实现类基类
 * @author: 印修河
 * @date: 2018/10/9 21:21
 */
public abstract class BaseServiceImpl<T> implements IBaseService<T> {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	protected BaseMapper<T> baseMapper;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Integer insert(T t) {
		return baseMapper.insert(t);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Integer update(T t) {
		return baseMapper.update(t);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public Integer deleteById(Serializable id) {
		return baseMapper.deleteById(id);
	}

	@Override
	public T getById(Serializable id) {
		return baseMapper.getById(id);
	}

	@Override
	public List<T> getAll() {
		return baseMapper.getAll();
	}

	@Override
	public Long findCountByQuery(T t) {
		return baseMapper.findCountByQuery(t);
	}

	@Override
	public List<T> findLimitByQuery(T t) {
		return baseMapper.findLimitByQuery(t);
	}

	@Override
	public PageModel<T> findByQuery(T t) {
		PageModel<T> pageModel = new PageModel<T>();
		Long count = baseMapper.findCountByQuery(t);
		pageModel.setTotal(count);
		if(count > 0){
			pageModel.setRows(baseMapper.findLimitByQuery(t));
		}
		return pageModel;
	}
}
