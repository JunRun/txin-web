package cc.txin.admin.controller;

import cc.txin.admin.entity.SystemParameterEntity;
import cc.txin.admin.entity.SystemParameterTypeEntity;
import cc.txin.admin.service.ISystemParameterService;
import cc.txin.admin.service.ISystemParameterTypeService;
import cc.txin.common.controller.BaseController;
import cc.txin.common.model.AjaxResult;
import cc.txin.common.model.PageModel;
import cc.txin.common.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author:   lcl
 * Date:     2018/10/23 15:03
 * Description: 系统参数控制类
 */
@RequestMapping("admin/systemParameter")
@Controller
public class SystemParameterController extends BaseController {

    @Autowired
    private ISystemParameterService systemParameterService;

    @Autowired
    private ISystemParameterTypeService systemParameterTypeService;

    /**
     * 系统参数页面
     * @return
     */
    @RequestMapping("/listPage")
    public String listPage(){
        return "admin/systemParameter/list";
    }

    /**
     * 系统参数列表
     * @param systemParameterEntity
     * @return
     */
    @RequestMapping("list")
    @ResponseBody
    public PageModel<SystemParameterEntity> list(SystemParameterEntity systemParameterEntity){
        return systemParameterService.findByQuery(systemParameterEntity);
    }


    /**
     * 保存或更新系统参数页面
     * @param systemParameterEntity
     * @return
     */
    @RequestMapping("save")
    public String save(SystemParameterEntity systemParameterEntity){
        if(systemParameterService.getById(systemParameterEntity.getId())==null){
            systemParameterService.insert(systemParameterEntity);
        }else {
            systemParameterService.update(systemParameterEntity);
        }
        return "admin/systemParameter/list";
    }

    /**
     * 系统参数详情页面
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("detailsPage")
    public String detailsPage(String id, Model model){
        if(id!=null){

            SystemParameterEntity systemParameterEntity=systemParameterService.getById(id);
            model.addAttribute("id",systemParameterEntity.getId());
            model.addAttribute("value",systemParameterEntity.getValue());
            model.addAttribute("description",systemParameterEntity.getDescription());
            model.addAttribute("typeId",systemParameterEntity.getTypeId());
            model.addAttribute("seq",systemParameterEntity.getSeq());
        }
        List<SystemParameterTypeEntity> list=systemParameterTypeService.getAll();
//        Map<String,String> map=new HashMap<>();
//        for (SystemParameterTypeEntity systemParameterTypeEntity:list){
//            map.put(systemParameterTypeEntity.getName(),systemParameterTypeEntity.getId());
//        }

        model.addAttribute("typeIdList",list);
        return "admin/systemParameter/details";
    }


    @RequestMapping("delete/{id}")
    @ResponseBody
    public AjaxResult delete(@PathVariable("id") String id){
        Assert.notNull(id,"请选择后删除");
        systemParameterService.deleteById(id);
        return new AjaxResult("删除成功");
    }

}
