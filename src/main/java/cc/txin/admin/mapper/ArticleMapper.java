package cc.txin.admin.mapper;

import cc.txin.admin.entity.Article;
import cc.txin.common.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 文章mapper
 * @author: 印修河
 * @date: 2018/10/12 21:40
 */
public interface ArticleMapper extends BaseMapper<Article> {

    /**
     * 更新状态
     * @param id
     * @param status
     * @return
     */
    Integer updateStatus(@Param("id") Long id, @Param("status") Integer status);

    /**
     * 查询最新的9条新闻
     * @return
     */
    List<Article> findNewsByLimit(@Param("limit") Integer limit);
}
