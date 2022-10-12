package com.briup.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author dell
 * 登出的servlet
 */
@WebServlet("/loginout")
public class LoginOutServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //登出操作，获取session并销毁，然后跳转到主页
        req.getSession().invalidate();
        resp.sendRedirect("/index.jsp");
    }
}
