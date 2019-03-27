package cc.txin.admin.controller;




import cc.txin.admin.entity.Partner;

import cc.txin.admin.service.IPartnerService;

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

@Controller
@RequestMapping("/admin/partner")
public class PartnerController extends BaseController {


    @Autowired
    private IPartnerService partnerService;
    /**
     * 上传文件的根路径
     */
    @Value("${upload.root.path}")
    private String uploadRootPath;

    @RequestMapping("/listPage")
    public String listPage() {
        return "admin/partner/list";
    }

    @RequestMapping("list")
    @ResponseBody
    public PageModel<Partner> list(Partner partner) {
        return partnerService.findByQuery(partner);
    }

    @RequestMapping("partnerDetailsPage")
    public String partnerPage(Long id, Model model) {
        if (id != null) {
            Partner partner = partnerService.getById(id);
            if (partner != null) {
                model.addAttribute("id", partner.getId());
                model.addAttribute("name", partner.getName());
                model.addAttribute("image", partner.getImageUrl());
                model.addAttribute("intro", partner.getIntro());

//                model.addAttribute("show",banner.getExhibition()? "是":"否");
            }
        }
        return "admin/partner/details";
    }

    @RequestMapping("/save")
    public String save(Partner partner, @RequestParam MultipartFile imgFile) {
        if (imgFile != null) {
            // 保存上传的文件
            try {
                String imgPath = FileUploadUtil.upload(imgFile, uploadRootPath, "/partner");
                partner.setImageUrl(imgPath);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (partner.getId() == null) {
            partnerService.insert(partner);
        } else {
            partnerService.update(partner);
        }
        return "admin/partner/list";

    }

    @RequestMapping("delete/{id}")
    @ResponseBody
    public AjaxResult delete(@PathVariable("id") Long id) {
        Assert.notNull(id, "请选择后删除");
        partnerService.deleteById(id);
        return new AjaxResult("删除成功");
    }

}





