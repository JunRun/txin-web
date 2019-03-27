package cc.txin.web.controller;

import cc.txin.admin.entity.Leave;
import cc.txin.admin.entity.Partner;
import cc.txin.admin.service.ILeaveService;
import cc.txin.common.controller.BaseController;
import cc.txin.common.model.AjaxResult;
import cc.txin.common.util.Assert;
import cc.txin.common.util.FileUploadUtil;
import cc.txin.common.util.ImageCode;
import cc.txin.common.util.MyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.naming.Name;
import javax.servlet.http.HttpSession;

/**
 * 购买方式控制器
 * @author: 印修河
 * @date: 2018/10/27 14:36
 */
@Controller
public class BuyController extends BaseController {
    @Autowired
    ILeaveService leaveService;

    @RequestMapping("/leaveSave")
    @ResponseBody
    public AjaxResult leaveSave(Leave leave, String imageCode, HttpSession session){
        Assert.notNull(imageCode, "请上送验证码");
        String sessionImageCode = (String) session.getAttribute(ImageCode.getSessionKey());
        if(!imageCode.equalsIgnoreCase(sessionImageCode)){
            throw  new MyException("验证码错误");
        }
        leaveService.insert(leave);
        return new AjaxResult("留言成功");
    }



    /**
     * 购买方式页面
     * @return
     */
    @RequestMapping("/buy")
    public String buy(){
        return "web/buy";
    }

}
