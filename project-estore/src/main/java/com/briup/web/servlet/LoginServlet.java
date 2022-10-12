package com.briup.web.servlet;

import com.briup.bean.Customer;
import com.briup.bean.ShopCar;
import com.briup.service.ICustomerService;
import com.briup.service.IShopCarService;
import com.briup.service.impl.CustomerServiceImpl;
import com.briup.service.impl.ShopCarServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * @author dell
 */
@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String password = req.getParameter("password");

        ICustomerService iCustomerService = new CustomerServiceImpl();
        Customer customer;
        HttpSession session = req.getSession();
        try {
            //调用接口的登录方法
             customer = iCustomerService.login(name, password);
             //将登录成功的用户放在session中
             session.setAttribute("customer",customer);

            //这里是登录上之后就会有对应的购物车信息所以在登录之后就查询购物车信息并存到session里面，等到再进行购物车添加操作的时候
            // 如果原来session中有值，重复提交相同名字的session会覆盖原来的session值
            IShopCarService iShopCarService = new ShopCarServiceImpl();
            List<ShopCar> shopCars = iShopCarService.findShopCarsByCustomer(customer.getId());
            session.setAttribute("shopCars",shopCars);

             //登录成功后跳转到首页
             resp.sendRedirect("/index.jsp");
        }catch (Exception e){
            //打印错误信息
            e.printStackTrace();
            String message = e.getMessage();
            //将错误信息存到session域中
            session.setAttribute("error",message);
            //跳转到注册页
            resp.sendRedirect("/login.jsp");
        }
    }
}
