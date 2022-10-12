package com.briup.web.servlet;

import com.briup.bean.Customer;
import com.briup.service.ICustomerService;
import com.briup.service.impl.CustomerServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author dell
 * 实现注册
 * 接收参数后交给service处理
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取service层的实现类对象，
        ICustomerService iCustomerService = new CustomerServiceImpl();
        //1.接收前端的参数
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        String zipCode = req.getParameter("zipCode");
        String address = req.getParameter("address");
        String telephone = req.getParameter("telephone");
        String email = req.getParameter("email");
        //将参数封装为customer对象
        Customer customer = new Customer(name,password,zipCode,address,telephone,email);
        //将对象传给Service层实现方法
        HttpSession session = req.getSession();
        try {
            iCustomerService.register(customer);
            //跳转到登录
            resp.sendRedirect("/login.jsp");
        }catch (Exception e){
            e.printStackTrace();
            String message = e.getMessage();
            session.setAttribute("error",message);
            //跳转到注册页
            resp.sendRedirect("/register.jsp");
        }
    }
}
