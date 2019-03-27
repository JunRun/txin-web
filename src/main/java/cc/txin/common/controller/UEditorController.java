package cc.txin.common.controller;

import com.alibaba.fastjson.JSONException;
import com.baidu.ueditor.ActionEnter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
@RequestMapping("ueditor")
public class UEditorController {

    @RequestMapping("/config")
    public void getUEditorConfig(HttpServletRequest request, HttpServletResponse response){
        String rootPath = "classpath:static/js/admin/ueditor/";
        try {
            String exec = new ActionEnter(request, rootPath).exec();
            PrintWriter writer = response.getWriter();
            writer.write(exec);
            writer.flush();
            writer.close();
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }

}
