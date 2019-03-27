package cc.txin.web.controller;

import cc.txin.admin.entity.Product;
import cc.txin.admin.entity.SystemParameterEntity;
import cc.txin.admin.entity.SystemParameterTypeEntity;
import cc.txin.admin.model.ProductImageModel;
import cc.txin.admin.service.IProductImageService;
import cc.txin.admin.service.IProductService;
import cc.txin.admin.service.ISystemParameterService;
import cc.txin.admin.service.ISystemParameterTypeService;
import cc.txin.common.controller.BaseController;
import cc.txin.common.model.PageModel;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 前台产品控制器
 * @author: 印修河
 * @date: 2018/10/27 11:14
 */
@Controller
public class ProductController extends BaseController {

    @Autowired
    private IProductService productService;

    @Autowired
    private IProductImageService productImageService;

    @Autowired
    private ISystemParameterService systemParameterService;

    @RequestMapping("/productList")
    public String productList(String type,String cpuS,String servers,String processors,String processorNumbers,String size,Model model){
        Product product=new Product();
        //若参数为空，则改为test
        String test="test";

        List<Product> productList=new ArrayList<>();
        Map<String,Object> map=new HashMap<>();
        if(StringUtils.isNotBlank(type)) {
            map.put("type",type);
        }else {
            type="通用服务器";
            map.put("type",type);
        }
        model.addAttribute("typeParameter",type);
        if(StringUtils.isNotBlank(cpuS)){
            String  cpu[]=cpuS.split(",");
            map.put("chips",cpu);
        }else {
            cpuS=test;
        }

        if (StringUtils.isNotBlank(servers)){
            String server[] =servers.split(",");
            map.put("sever",server);
        }else {
            servers=test;
        }
        model.addAttribute("severParameter",servers);
        if(StringUtils.isNotBlank(processorNumbers)){
            List<String> processorNumber=new ArrayList<>();
            String processorNumberArray[]=processorNumbers.split(",");
            for(String p:processorNumberArray){
               String px=p.substring(0,1)+"x";
                processorNumber.add(px);
            }
            map.put("processorNumber",processorNumber);
        }else {
            processorNumbers=test;
        }
        model.addAttribute("processorNumberParameter",processorNumbers);
        if(StringUtils.isNotBlank(size)){
            String s[]=size.split(",");
            map.put("size",s);
        }else {
            size=test;
        }
        model.addAttribute("sizeParameter",size);

        if (StringUtils.isNotBlank(processors)){
            String []processor=processors.split(",");
            map.put("processor",processor);
        }else {
            processors=test;
        }
        model.addAttribute("processorParameter",processors);
        if(map.size()!=0) {
            List<Product> products = productService.findByParameter(map);
            productList.addAll(products);
        }

        //产品参数
        JSONArray jsonArray=new JSONArray();
        for (Product productEntity:productList){
            JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(productEntity, SerializerFeature.WriteDateUseDateFormat));
            List<ProductImageModel> imageModel= productImageService.getByProductId(productEntity.getId());
            if (imageModel.size()>0){
                jsonObject.put("image",imageModel.get(0).getImageUrl());
            }
            jsonArray.add(jsonObject);

        }
        model.addAttribute("productList",jsonArray);
        //系统参数
        List<SystemParameterEntity> productSys=systemParameterService.findByType("product");
        List<SystemParameterEntity> cpuList = systemParameterService.findByType("cpu");
        List<SystemParameterEntity> processorList = systemParameterService.findByType("processor");
        List<SystemParameterEntity> serverList  = systemParameterService.findByType("server");
        List<SystemParameterEntity> processorNumberList = systemParameterService.findByType("processorNumber");
        List<SystemParameterEntity> sizeList = systemParameterService.findByType("size");


        model.addAttribute("cpuParameter",cpuS);
        model.addAttribute("proList",productSys);
        model.addAttribute("cpuList",cpuList);
        model.addAttribute("processorList",processorList);
        model.addAttribute("serverList",serverList);
        model.addAttribute("processorNumberList",processorNumberList);
        model.addAttribute("sizeList",sizeList);
        return "web/product";

    }


    @RequestMapping("/product/{id}")
    public String productList(@PathVariable("id") Long id,Model model){
        Product product= productService.getById(id);
//       model.addAttribute("product",product);
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
        model.addAttribute("environmentalScience",product.getEnvironmentalScience());
        model.addAttribute("types",product.getTypes());

        List<ProductImageModel> imageModelList =productImageService.getByProductId(id);

        model.addAttribute("imageList",imageModelList);
        return "web/product-detail";


    }

    @RequestMapping("/productCompare")
    public String productCompare(Long[] ids,Model model){

        if(ids!=null){

            List<Map<String,String>> list=new ArrayList<>();

            List<String> processor=new ArrayList<>();
            //系统型号
            List<String> modelList=new ArrayList<>();
            //芯片组
            List<String> chipsList=new ArrayList<>();
            List<String> memoryList=new ArrayList<>();
            List<String> storageFunctionList=new ArrayList<>();
            List<String> hardDiskSlotList=new ArrayList<>();
            List<String> networkList=new ArrayList<>();
            List<String> expansionSlotsList=new ArrayList<>();
            List<String> inputOutputList=new ArrayList<>();
            List<String> powerSupplyList=new ArrayList<>();
            List<String> operatingSystemList=new ArrayList<>();
            List<String> sizeList=new ArrayList<>();
            List<String> environmentalScienceList=new ArrayList<>();
            List<String> typesList=new ArrayList<>();



            for(Long id:ids){
                Product product =productService.getById(id);
                Map<String,String> name=new HashMap<>();
                List<ProductImageModel> imageModelList  = productImageService.getByProductId(id);
                name.put("name",product.getProductName());
                name.put("imageUrl",imageModelList.get(0).getImageUrl());
                list.add(name);
                processor.add(product.getProcessor());
                modelList.add(product.getModel());
                chipsList.add(product.getChips());
                memoryList.add(product.getMemory());
                storageFunctionList.add(product.getStorageFunction());
                hardDiskSlotList.add(product.getHardDiskSlot());
                networkList.add(product.getNetwork());
                expansionSlotsList.add(product.getExpansionSlots());
                inputOutputList.add(product.getInputOutput());
                powerSupplyList.add(product.getPowerSupply());
                operatingSystemList.add(product.getOperatingSystem());
                sizeList.add(product.getSize());
                environmentalScienceList.add(product.getEnvironmentalScience());
                typesList.add(product.getTypes());

            }
            model.addAttribute("name",list);
            model.addAttribute("processorList",processor);
            model.addAttribute("chipsList",chipsList);
            model.addAttribute("memoryList",memoryList);
            model.addAttribute("storageFunctionList",storageFunctionList);
            model.addAttribute("hardDiskSlotList",hardDiskSlotList);
            model.addAttribute("networkList",networkList);
            model.addAttribute("expansionSlotsList",expansionSlotsList);
            model.addAttribute("inputOutputList",inputOutputList);
            model.addAttribute("powerSupplyList",powerSupplyList);
            model.addAttribute("operatingSystemList",operatingSystemList);
            model.addAttribute("sizeList",sizeList);
            model.addAttribute("environmentalScienceList",environmentalScienceList);
            model.addAttribute("typesList",typesList);



        }
//        map.put("name","windows98");
//        map.put("imgaeUrl",);
        return "web/product-comparative";
    }


}
