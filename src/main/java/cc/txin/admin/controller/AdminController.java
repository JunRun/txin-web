package cc.txin.admin.controller;

import cc.txin.admin.entity.User;
import cc.txin.admin.service.IUserService;
import cc.txin.common.controller.BaseController;
import cc.txin.common.model.AjaxResult;
import cc.txin.common.util.MyException;
import cc.txin.common.util.PasswordUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@Slf4j
@Controller
public class AdminController extends BaseController {
    @Autowired
    IUserService userService;

    @RequestMapping("/admin")
    public String admin(){
        return "admin/login";
    }

    @RequestMapping("/admin/index")
    public String adminIndex(){
        return "admin/index";
    }

    @RequestMapping("/admin/indexContext")
    public String adminIndexContext(){
        return "admin/indexContext";
    }
    @RequestMapping("/admin/login")
    public String loginPage(){
        return "admin/user/login";
    }

    @RequestMapping("/admin/checkUser")

    @ResponseBody
    public AjaxResult checkUser(String username, String password, HttpServletRequest request){
        //校验用户名密码是否正确
        User user = userService.findByUsername(username,password);

        if (user==null){
            throw  new MyException("用户名密码错误");
        }

        request.getSession().setAttribute("user",user);
        return new AjaxResult("登录成功");
    }
    @RequestMapping("/admin/loginOut")
    public void loginOut(HttpServletRequest request, HttpServletResponse response) throws  Exception{
        request.getSession().removeAttribute("user");
        response.sendRedirect(request.getContextPath()+"/admin/login");

    }
}
