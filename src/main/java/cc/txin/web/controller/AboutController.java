package cc.txin.web.controller;

import cc.txin.common.controller.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 关于我们控制器
 * @author: 印修河
 * @date: 2018/10/27 15:10
 */
@Controller
public class AboutController extends BaseController {

    /**
     * 公司简介
     * @return
     */
    @RequestMapping("/about")
    public String about(){
        return "web/about";
    }
}
