package cc.txin.admin.service;

import cc.txin.admin.entity.Article;
import cc.txin.common.service.IBaseService;

import java.util.List;

/**
 * 文章服务接口类
 * @author: 印修河
 * @date: 2018/10/12 21:39
 */
public interface IArticleService extends IBaseService<Article> {

    /**
     * 查询最新的9条新闻
     * @return
     */
    List<Article> findNewsByLimit(Integer limit);
}
