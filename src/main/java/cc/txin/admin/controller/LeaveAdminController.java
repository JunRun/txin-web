package cc.txin.admin.controller;


import cc.txin.admin.entity.Leave;
import cc.txin.admin.service.ILeaveService;
import cc.txin.common.controller.BaseController;
import cc.txin.common.model.PageModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/admin/leave")
public class LeaveAdminController extends BaseController {

    @Autowired
    private ILeaveService leaveService;

    @RequestMapping("/listPage")
    public String listPage(){
        return "admin/leave/list";
    }
    @RequestMapping("list")
    @ResponseBody
    public PageModel<Leave> list(Leave leave) {
        return leaveService.findByQuery(leave);
    }


}
