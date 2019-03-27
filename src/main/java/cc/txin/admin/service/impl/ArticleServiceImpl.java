package cc.txin.admin.service.impl;

import cc.txin.admin.entity.Article;
import cc.txin.admin.mapper.ArticleMapper;
import cc.txin.admin.service.IArticleService;
import cc.txin.common.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * 文章服务实现类
 * @author: 印修河
 * @date: 2018/10/12 21:39
 */
@Service("articleService")
public class ArticleServiceImpl extends BaseServiceImpl<Article> implements IArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public List<Article> findNewsByLimit(Integer limit) {
        return articleMapper.findNewsByLimit(limit);
    }
}
