package cc.txin.admin.controller;

import cc.txin.admin.entity.Article;
import cc.txin.admin.service.IArticleService;
import cc.txin.common.model.AjaxResult;
import cc.txin.common.model.PageModel;
import cc.txin.common.util.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文章控制器
 * @author: 印修河
 * @date: 2018/10/12 21:41
 */
@Controller
@RequestMapping("/admin/article")
public class ArticleAdminController {

    /** 上传文件的根路径 */
    @Value("${upload.root.path}")
    private String uploadRootPath;
    @Autowired
    private IArticleService articleService;

    /**
     * 文章列表页面
     * @return
     */
    @RequestMapping("/listPage")
    public String listPage(String types, Model model){
        model.addAttribute("types", types);
        return "admin/article/list";
    }

    /**
     * 文章列表数据
     * @param article
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public PageModel<Article> list(Article article){
        return articleService.findByQuery(article);
    }

    /**
     * 文章详情页面
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/detailsPage")
    public String detailsPage(Long id, Model model){
        if(id != null){
            Article article = articleService.getById(id);
            if(article != null){
                model.addAttribute("id", article.getId());
                model.addAttribute("title", article.getTitle());
                model.addAttribute("source", article.getSource());
                model.addAttribute("author", article.getAuthor());
                model.addAttribute("types", article.getTypes());
                model.addAttribute("image", article.getImage());
                model.addAttribute("info", article.getInfo());
                model.addAttribute("content", article.getContent());
                model.addAttribute("seq", article.getSeq());
            }
        }
        return "admin/article/details";
    }

    /**
     * 保存文章
     * @param article
     * @param imgFile
     * @param model
     * @return
     */
    @RequestMapping("/save")
    public String save(Article article, @RequestParam MultipartFile imgFile, Model model){
        if(imgFile != null){
            // 保存上传的文件
            try {
                String imgPath = FileUploadUtil.upload(imgFile, uploadRootPath, "/article");
                article.setImage(imgPath);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if(article.getId() == null){
            articleService.insert(article);
        } else {
            articleService.update(article);
        }
        model.addAttribute("types", article.getTypes());
        return "admin/article/list";
    }

    /**
     * 删除文章
     * @param id
     */
    @RequestMapping("delete/{id}")
    @ResponseBody
    public AjaxResult delete(@PathVariable("id") Long id){
        articleService.deleteById(id);
        return new AjaxResult("删除文章成功");
    }
}
