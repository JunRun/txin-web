package cc.txin.web.controller;

import cc.txin.admin.entity.Article;
import cc.txin.admin.enums.ArticleTypeEnum;
import cc.txin.admin.service.IArticleService;
import cc.txin.common.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 常见问题
 * @author: 印修河
 * @date: 2018/11/4 14:24
 */
@Controller
public class ProblemController extends BaseController {

    @Autowired
    private IArticleService articleService;

    @RequestMapping("/problem")
    public String problem(Model model){
        //查询解决方案列表
        Article article = new Article();
        article.setLimit(20);
        article.setSort("seq");
        //查询售后问题
        article.setTypes(ArticleTypeEnum.AFTER_SALES_PROBLEM.name());
        List<Article> afterSalesProblemList = articleService.findLimitByQuery(article);
        model.addAttribute("afterSalesProblemList", afterSalesProblemList);
        //使用问题
        article.setTypes(ArticleTypeEnum.USE_PROBLEM.name());
        List<Article> useProblemList = articleService.findLimitByQuery(article);
        model.addAttribute("useProblemList", useProblemList);
        //购买问题
        article.setTypes(ArticleTypeEnum.BUY_PROBLEM.name());
        List<Article> buyProblemList = articleService.findLimitByQuery(article);
        model.addAttribute("buyProblemList", buyProblemList);
        return "web/problem-list";
    }

}
