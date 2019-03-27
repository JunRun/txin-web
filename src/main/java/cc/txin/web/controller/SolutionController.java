package cc.txin.web.controller;

import cc.txin.admin.entity.Article;
import cc.txin.admin.enums.ArticleTypeEnum;
import cc.txin.admin.service.IArticleService;
import cc.txin.common.controller.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 解决方案控制器
 * @author: 印修河
 * @date: 2018/11/3 22:43
 */
@Controller
public class SolutionController extends BaseController {

    @Autowired
    private IArticleService articleService;

    /**
     * 解决方案列表
     * @param model
     * @return
     */
    @RequestMapping("/solution")
    public String solution(Model model){
        Article article = new Article();
        article.setOffset(null);
        article.setSort("seq");
        article.setTypes(ArticleTypeEnum.SOLUTION.name());
        List<Article> solutionList = articleService.findLimitByQuery(article);
        model.addAttribute("solutionList", solutionList);
        return "web/solution-list";
    }

    /**
     * 解决方案详情
     * @param model
     * @param id 解决方案详情
     * @return
     */
    @RequestMapping("/solution/{id}")
    public String solutionDetail(Model model, @PathVariable("id") Long id){
        //查询解决方案列表
        Article article = new Article();
        article.setOffset(null);
        article.setSort("seq");
        article.setTypes(ArticleTypeEnum.SOLUTION.name());
        List<Article> solutionList = articleService.findLimitByQuery(article);
        model.addAttribute("solutionList", solutionList);
        //查询显示解决方案详情
        Article solution = articleService.getById(id);
        model.addAttribute("solution", solution);
        return "web/solution-detail";
    }
}
