package cc.txin.admin.controller;

import cc.txin.admin.entity.Product;
import cc.txin.admin.model.ProductImageModel;
import cc.txin.admin.service.IProductImageService;
import cc.txin.admin.service.IProductService;
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
 * @Author:   lcl
 * @Date:     2018/10/22 15:16
 * @Description: 产品 控制器
 */
@RequestMapping("admin/product")
@Controller
public class ProductAdminController extends BaseController {
    /** 上传文件的根路径 */
    @Value("${upload.root.path}")
    private String uploadRootPath;
    @Autowired
    private IProductService productService;

    @Autowired
    private IProductImageService productImageService;

    /**
     * 产品页面
     * @return
     */
    @RequestMapping("listPage")
    public String listPage(){
        return "admin/product/list";
    }

    @RequestMapping("list")
    @ResponseBody
    public PageModel<Product> list(Product product) {

        PageModel<Product> list= productService.findByQuery(product);
        return list;
    }

    @RequestMapping("productDetailsPage")
    public String detailPage(Long id, Model model){
        if(id != null){
            Product product=productService.getById(id);
            if(product != null){
                model.addAttribute("id", product.getId());
                model.addAttribute("productName",product.getProductName());
                model.addAttribute("model",product.getModel());
                model.addAttribute("processor",product.getProcessor());
                model.addAttribute("chips",product.getChips());
                model.addAttribute("memory",product.getMemory());
                model.addAttribute("storageFunction",product.getStorageFunction());
                model.addAttribute("hardDiskSlot",product.getHardDiskSlot());
                model.addAttribute("network",product.getNetwork());
                model.addAttribute("expansionSlots",product.getExpansionSlots());
                model.addAttribute("inputOutput",product.getInputOutput());
                model.addAttribute("powerSupply",product.getPowerSupply());
                model.addAttribute("operatingSystem",product.getOperatingSystem());
                model.addAttribute("size",product.getSize());
                model.addAttribute("types",product.getTypes());
                model.addAttribute("environmentalScience",product.getEnvironmentalScience());
                model.addAttribute("imageList",product.getImageModelList());
            }
        }
        return "admin/product/productDetails";
    }


    @RequestMapping("/save")
    public String save(Product product,MultipartFile[] file){

        if (product.getId()==null){
            productService.insert(product);
        }else{
            productService.update(product);
        }


        if( file.length>=0){
            for(MultipartFile imgFile:file){
                ProductImageModel productImageModel=new ProductImageModel();
                productImageModel.setProductId(product.getId());
                // 保存上传的文件
            try {
                String imgPath = FileUploadUtil.upload(imgFile, uploadRootPath, "/product");
                productImageModel.setImageUrl(imgPath);
                productImageService.insert(productImageModel);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        }
        return "admin/product/list";
    }

    @RequestMapping("delete/{id}")
    @ResponseBody
    public AjaxResult delete(@PathVariable("id") Long id){
        Assert.notNull(id,"请选择后删除");
        productService.deleteById(id);
        return new AjaxResult("删除成功");
    }

    @RequestMapping("deleteImage/{id}")
    @ResponseBody
    public AjaxResult deleteImage(@PathVariable("id") Long id){
        Assert.notNull(id,"请选择后删除");
        productImageService.deleteById(id);
        return new AjaxResult("删除成功");
    }

}
