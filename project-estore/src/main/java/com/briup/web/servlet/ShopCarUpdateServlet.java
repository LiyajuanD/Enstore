package com.briup.web.servlet;

import com.briup.bean.Customer;
import com.briup.bean.ShopCar;
import com.briup.service.IShopCarService;
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
 * 实现购物车页面的保存按钮
 * 获取到当前用户的id和操作的bookid后调用service层的方法
 * 在数据库修改要取消的对应的数据
 *
 */
@WebServlet("/shopCarUpdate")
public class ShopCarUpdateServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        获取到当前用户的id和操作的bookid
        HttpSession session = req.getSession();
        Customer customer = (Customer) session.getAttribute("customer");
        Integer customerid = customer.getId();
        //接收修改的bookid和num
        int bookid = Integer.parseInt(req.getParameter("bookId"));
        int num = Integer.parseInt(req.getParameter("num"));

        //调用service的方法
        IShopCarService iShopCarService = new ShopCarServiceImpl();
        //封装为shopcar对象
        iShopCarService.updateShopCar(new ShopCar(num,bookid,customerid));
        //更新完成后重新查询购物车信息存入session后跳转到购物车页面
        List<ShopCar> shopCars = iShopCarService.findShopCarsByCustomer(customerid);
        session.setAttribute("shopCars",shopCars);
        resp.sendRedirect("/shopCar.jsp");
    }
}
