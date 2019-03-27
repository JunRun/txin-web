package cc.txin.admin.controller;

import cc.txin.admin.entity.Banner;
import cc.txin.admin.service.IBannerService;
import cc.txin.common.controller.BaseController;
import cc.txin.common.model.AjaxResult;
import cc.txin.common.model.PageModel;
import cc.txin.common.util.Assert;
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
 * Author:   lcl
 * Date:     2018/10/17 20:25
 * Description: 轮播器控制器
 */
@Controller
@RequestMapping("/admin/banner")
public class BannerController extends BaseController {
    /** 上传文件的根路径 */
    @Value("${upload.root.path}")
    private String uploadRootPath;
    @Autowired
    private IBannerService bannerService;

    /**
     * banner页面
     * @return
     */
    @RequestMapping("/listPage")
    public String listPage(){
        return "admin/banner/list";
    }

    @RequestMapping("list")
    @ResponseBody
    public PageModel<Banner> list(Banner banner) {
        return bannerService.findByQuery(banner);
    }

    @RequestMapping("detailPage")
    public String detailPage(Long id, Model model){
        if(id != null){
            Banner banner=bannerService.getById(id);
            if(banner != null){
                model.addAttribute("id", banner.getId());
                model.addAttribute("title",banner.getTitle());
                model.addAttribute("imageUrl",banner.getImageUrl());
                model.addAttribute("seq",banner.getSeq());
                model.addAttribute("url",banner.getUrl());
//                model.addAttribute("show",banner.getExhibition()? "是":"否");
            }
        }
        return "admin/banner/bannerDetails";
    }


    @RequestMapping("/save")
    public String save(Banner banner, @RequestParam MultipartFile imgFile){
        if(imgFile != null){
            // 保存上传的文件
            try {
                String imgPath = FileUploadUtil.upload(imgFile, uploadRootPath, "/article");
                banner.setImageUrl(imgPath);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (banner.getId()==null){
            bannerService.insert(banner);
        }else{
            bannerService.update(banner);
        }
        return "admin/banner/list";

    }

    @RequestMapping("delete/{id}")
    @ResponseBody
    public AjaxResult delete(@PathVariable("id") Long id){
        Assert.notNull(id,"请选择后删除");
        bannerService.deleteById(id);
        return new AjaxResult("删除成功");
    }
    @RequestMapping("/update")
    public void update(Banner banner){
        bannerService.update(banner);
    }


}
