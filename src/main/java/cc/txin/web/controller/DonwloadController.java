package cc.txin.web.controller;


import cc.txin.admin.entity.Download;
import cc.txin.admin.entity.Product;
import cc.txin.admin.entity.SystemParameterEntity;
import cc.txin.admin.model.ProductImageModel;
import cc.txin.admin.service.IDownloadService;
import cc.txin.admin.service.ISystemParameterService;
import cc.txin.admin.service.ISystemParameterTypeService;
import cc.txin.common.controller.BaseController;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
public class DonwloadController extends BaseController {

    @Autowired
    private IDownloadService downloadService;
    @Autowired
    private ISystemParameterService systemParameterService;

    @RequestMapping("/download")
    public String download( Download download,Model model) {
        //设置为空避免分页
        download.setOffset(null);
        List<Download> downloadList = downloadService.findLimitByQuery(download);
        model.addAttribute("downloadList", downloadList);
        //系统参数
        List<SystemParameterEntity> typesList = systemParameterService.findByType("product_types");
        List<SystemParameterEntity> versionList  = systemParameterService.findByType("version");

        model.addAttribute("versionList",versionList);
        model.addAttribute("typesList",typesList);

        return "web/download-center";
    }



}





