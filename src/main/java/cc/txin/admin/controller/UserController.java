package cc.txin.admin.controller;


import cc.txin.admin.entity.User;
import cc.txin.admin.service.IUserService;
import cc.txin.common.controller.BaseController;
import cc.txin.common.model.AjaxResult;
import cc.txin.common.model.PageModel;
import cc.txin.common.util.Assert;
import cc.txin.common.util.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/admin/user")
public class UserController extends BaseController {

    @Autowired
    private IUserService userService;

    @RequestMapping("/listPage")
    public String listPage() {
        return "admin/user/list";
    }

    @RequestMapping("list")
    @ResponseBody
    public PageModel<User> list(User user) {
        return userService.findByQuery(user);
    }

    @RequestMapping("/save")
    public String save(User user) {
        String password = PasswordUtil.encript(user.getPassword().trim());
        user.setPassword(password);

        if (user.getId() == null) {
            userService.insert(user);
        } else {
            userService.update(user);
        }
        return "admin/user/list";

    }

    @RequestMapping("delete/{id}")
    @ResponseBody
    public AjaxResult delete(@PathVariable("id") Long id) {
        Assert.notNull(id, "请选择后删除");
        userService.deleteById(id);
        return new AjaxResult("删除成功");
    }

    @RequestMapping("userDetailsPage")
    public String userPage(Long id, Model model) {
        if (id != null) {
            User user = userService.getById(id);
            if (user != null) {
                model.addAttribute("id", user.getId());
                model.addAttribute("username", user.getUsername());

                model.addAttribute("password", PasswordUtil.encript(user.getPassword().trim()));
                model.addAttribute("realname", user.getRealname());
                model.addAttribute("mobile", user.getMobile());
                model.addAttribute("email", user.getEmail());

//                model.addAttribute("show",banner.getExhibition()? "是":"否");
            }
        }
        return "admin/user/details";
    }

}
