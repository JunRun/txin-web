package cc.txin.admin.controller;


import cc.txin.admin.entity.Download;
import cc.txin.admin.entity.SystemParameterEntity;
import cc.txin.admin.service.IDownloadService;
import cc.txin.admin.service.ISystemParameterService;
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

import java.util.List;


@Controller
@RequestMapping("/admin/download")

public class DonwloadAdminController extends BaseController {


    @Autowired
    private IDownloadService downloadService;
    @Autowired
    private ISystemParameterService systemParameterService;
    /**
     * 上传文件的根路径
     */
    @Value("${upload.root.path}")
    private String uploadRootPath;

    @RequestMapping("/listPage")
    public String listPage() {
        return "admin/product/downloadlist";
    }

    @RequestMapping("list")
    @ResponseBody
    public PageModel<Download> list(Download download) {
        return downloadService.findByQuery(download);
    }
    @RequestMapping("/save")
    public String save(Download download, @RequestParam MultipartFile file) {
        if (file != null) {
            // 保存上传的文件
            try {
                String imgPath = FileUploadUtil.upload(file, uploadRootPath, "/download");
                download.setAccessory(imgPath);
                double filesize=(double) file.getSize()/1024/1024;


                download.setFileSize(filesize);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (download.getId() == null) {
            downloadService.insert(download);
        } else {
            downloadService.update(download);
        }
        return "admin/product/downloadlist";
    }

    @RequestMapping("delete/{id}")
    @ResponseBody
    public AjaxResult delete(@PathVariable("id") Long id) {
        Assert.notNull(id, "请选择后删除");
        downloadService.deleteById(id);
        return new AjaxResult("删除成功");
    }
    @RequestMapping("downloadDetailsPage")
    public String partnerPage(Long id, Model model,Download download) {


        if (id != null) {
            Download download1 = downloadService.getById(id);
            if (download != null) {
                model.addAttribute("id", download1.getId());
                model.addAttribute("productName", download1.getProductName());
                model.addAttribute("types", download1.getTypes());
                model.addAttribute("version", download1.getVersion());
                model.addAttribute("fileName", download1.getFileName());
                model.addAttribute("versionNumber", download1.getVersionNumber());
                model.addAttribute("accessory", download1.getAccessory());
                model.addAttribute("intro", download1.getIntro());
                model.addAttribute("fileSize", download1.getFileSize());




//                model.addAttribute("show",banner.getExhibition()? "是":"否");
            }
        }
        download.setOffset(null);
        List<Download> downloadList = downloadService.findLimitByQuery(download);
        model.addAttribute("downloadList", downloadList);
        //系统参数
        List<SystemParameterEntity> typesList = systemParameterService.findByType("product_types");
        List<SystemParameterEntity> versionList  = systemParameterService.findByType("version");

        model.addAttribute("versionList",versionList);
        model.addAttribute("typesList",typesList);


        return "admin/product/downloadDetails";
    }
}





