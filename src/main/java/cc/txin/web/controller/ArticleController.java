package cc.txin.web.controller;

import cc.txin.admin.entity.Article;
import cc.txin.admin.enums.ArticleTypeEnum;
import cc.txin.admin.service.IArticleService;
import cc.txin.common.controller.BaseController;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * 前台文章控制器
 * @author: 印修河
 * @date: 2018/10/27 11:40
 */
@Controller
public class ArticleController extends BaseController {

    @Autowired
    private IArticleService articleService;

    /**
     * 文章详情页面
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/article/{id}")
    public String articleList(@PathVariable("id") Long id, Model model){
        Article article = articleService.getById(id);
        if(article != null){
            model.addAttribute("title", article.getTitle());
            model.addAttribute("types", article.getTypes());
            model.addAttribute("createDate", article.getCreateDate());
            model.addAttribute("source", article.getSource());
            model.addAttribute("content", article.getContent());

            //查询热门列表
            Article queryArticle = new Article();
            queryArticle.setLimit(15);
            queryArticle.setTypes(article.getTypes());
            queryArticle.setSort("seq");
            List<Article> articleList = articleService.findLimitByQuery(queryArticle);
            model.addAttribute("articleList", articleList);
        }
        return "web/article-detail";
    }

    /**
     * 新闻中心页面
     * @return
     */
    @RequestMapping("/news")
    public String news(String type, Integer page, Model model){
        //最新的9条新闻，并拆分为前3位及前6两个list
        List<Article> newsTop9 = articleService.findNewsByLimit(9);
        List<Article> newsTop3 = new ArrayList<>();
        List<Article> newsTop6 = new ArrayList<>();
        int i = 0;
        for(Article article : newsTop9){
            if(i < 3){
                newsTop3.add(article);
            }else {
                newsTop6.add(article);
            }
            i++;
        }
        model.addAttribute("newsTop3", newsTop3);
        model.addAttribute("newsTop6", newsTop6);
        //新闻列表
        if(StringUtils.isBlank(type)){
            type=ArticleTypeEnum.COMPANY_NEWS.name();
        }
        if(page == null || page < 1){
            page = 0;
        }else{
            page--;
        }
        Article article = new Article();
        article.setTypes(type);
        article.setLimit(9);
        article.setOffset(page * 9 + 1);
        List<Article> articleList = articleService.findLimitByQuery(article);
        model.addAttribute("articleList", articleList);
        model.addAttribute("type", type);
        model.addAttribute("page", page+1);
        //查询总页数
        long totalPage = 0L;
        Long count = articleService.findCountByQuery(article);
        if(count > 0){
            totalPage = count / 9;
            if(totalPage%9>0){
                totalPage++;
            }
        }
        model.addAttribute("totalPage", totalPage);
        return "web/news";
    }
}
