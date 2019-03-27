package cc.txin.conf;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

@Component
public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //在这儿写判断逻辑，判断session是否有那个user
        HttpSession session = request.getSession();
        if (session != null && session.getAttribute("user") != null) {

            return true;
        } else {
            PrintWriter printWriter = response.getWriter();
//            printWriter.write("/admin/login");
            response.sendRedirect(request.getContextPath()+"/admin/login");

            return false;
        }


    }


}
