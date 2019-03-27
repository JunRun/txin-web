package cc.txin.admin.controller;

import cc.txin.admin.entity.SystemParameterTypeEntity;
import cc.txin.admin.service.ISystemParameterTypeService;
import cc.txin.common.controller.BaseController;
import cc.txin.common.model.AjaxResult;
import cc.txin.common.model.PageModel;
import cc.txin.common.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Author:   lcl
 * Date:     2018/10/23 15:05
 * Description: 系统参数类型控制类
 */
@Controller
@RequestMapping("admin/systemParameterType")
public class SystemParameterTypeController extends BaseController {

    @Autowired
    private ISystemParameterTypeService systemParameterTypeService;

    /**
     * 系统参数类型页面
     * @return
     */
    @RequestMapping("/listPage")
    public String listPage(){
        return "admin/systemParameterType/list";
    }

    /**
     * 系统参数类型列表
     * @param systemParameterTypeEntity
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public PageModel<SystemParameterTypeEntity> list(SystemParameterTypeEntity systemParameterTypeEntity){
        return systemParameterTypeService.findByQuery(systemParameterTypeEntity);
    }

    /**
     * 系统参数类型详情页面
     * @param id
     * @param model
     * @return
     */
    @RequestMapping("/detailsPage")
    public String detailsPage(String id, Model model){
        if(id!=null){
            SystemParameterTypeEntity systemParameterTypeEntity=systemParameterTypeService.getById(id);
            model.addAttribute("id",systemParameterTypeEntity.getId());
            model.addAttribute("name",systemParameterTypeEntity.getName());
            model.addAttribute("description",systemParameterTypeEntity.getDescription());
            model.addAttribute("seq",systemParameterTypeEntity.getSeq());
        }
        return "admin/systemParameterType/details";
    }

    @RequestMapping("/save")
    public String save(SystemParameterTypeEntity systemParameterTypeEntity){
        if(systemParameterTypeService.getById(systemParameterTypeEntity.getId())!=null){
            systemParameterTypeService.update(systemParameterTypeEntity);
        }else {
            systemParameterTypeService.insert(systemParameterTypeEntity);
        }
        return "admin/systemParameterType/list";
    }

    /**
     * 根据id删除
     * @param id
     * @return
     */
    @RequestMapping("/delete/{id}")
    @ResponseBody
    public AjaxResult delete(@PathVariable("id") String id){
        Assert.notNull(id,"请选择后删除");
        systemParameterTypeService.deleteById(id);
        return new AjaxResult("删除成功");
    }


}
