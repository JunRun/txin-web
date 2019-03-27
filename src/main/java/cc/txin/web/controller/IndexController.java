package cc.txin.web.controller;

import cc.txin.admin.entity.Article;
import cc.txin.admin.entity.Banner;
import cc.txin.admin.entity.Partner;
import cc.txin.admin.entity.Product;
import cc.txin.admin.enums.ArticleTypeEnum;
import cc.txin.admin.model.ProductImageModel;
import cc.txin.admin.service.*;
import cc.txin.common.controller.BaseController;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 网站首页
 * @author: 印修河
 * @date: 2018/10/27 9:34
 */
@Controller
public class IndexController extends BaseController {

    @Autowired
    private IBannerService bannerService;
    @Autowired
    private IArticleService articleService;
    @Autowired
    private IProductService productService;
    @Autowired
    private IProductImageService productImageService;
    @Autowired
    private IPartnerService partnerService;

    @RequestMapping("")
    public String root(Model model){
        return "forward:/index";
    }

    @RequestMapping("/index")
    public String index(Model model){
        //查询banner
        Banner banner = new Banner();
        banner.setLimit(5);
        banner.setExhibition(true);
        List<Banner> bannerList = bannerService.findLimitByQuery(banner);
        model.addAttribute("bannerList", bannerList);
        //查询合作伙伴
        List<Partner> partnerList = partnerService.getAll();
        model.addAttribute("partnerList", partnerList);
        //查询公司新闻
        Article article = new Article();
        article.setLimit(6);
        article.setSort("seq");
        article.setTypes(ArticleTypeEnum.COMPANY_NEWS.name());
        List<Article> companyNewsList = articleService.findLimitByQuery(article);
        model.addAttribute("companyNewsList", companyNewsList);
        //查询行业新闻
        article.setTypes(ArticleTypeEnum.INDUSTRY_NEWS.name());
        List<Article> industryNewsList = articleService.findLimitByQuery(article);
        model.addAttribute("industryNewsList", industryNewsList);
        //查询产品,只查询四个
        Product product = new Product();
        product.setShowImage(true);
        List<Product> productList = productService.findLimitByQuery(product);
        JSONArray jsonArray = new JSONArray();
        for (Product productEntity:productList){
            JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(productEntity, SerializerFeature.WriteDateUseDateFormat));
            List<ProductImageModel> imageModel = productImageService.getByProductId(productEntity.getId());
            if (imageModel != null && !imageModel.isEmpty()){
                jsonObject.put("imageUrl", imageModel.get(0).getImageUrl());
            }
            jsonArray.add(jsonObject);
        }
        model.addAttribute("productList",jsonArray);
        //查询解决方案列表
        Article solution = new Article();
        solution.setLimit(6);
        solution.setSort("seq");
        solution.setTypes(ArticleTypeEnum.SOLUTION.name());
        List<Article> solutionList = articleService.findLimitByQuery(solution);
        int i = 1;
        for(Article s : solutionList){
            model.addAttribute("solution" + i, s);
            i++;
        }
        return "web/index";
    }

}
