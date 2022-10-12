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
import javax.sound.midi.Soundbank;
import java.io.IOException;
import java.util.List;

/**
 * @author dell
 * 实现购物车页面的取消按钮
 * 获取到当前用户的id和操作的bookid后调用service层的方法
 * 在数据库删除要取消的对应的数据
 */
@WebServlet("/shopCarDelete")
public class shopCarDeleteServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       //获取到当前用户的id和操作的bookid
        HttpSession session = req.getSession();
        IShopCarService iShopCarService = new ShopCarServiceImpl();
        Customer customer = (Customer) session.getAttribute("customer");
        Integer id = customer.getId();
        int  bookId = Integer.parseInt(req.getParameter("bookId"));
       //调用service层的方法
        iShopCarService.deleteShopCarByCidAndBId(id,bookId);
        //删除后重新查购物车的信息存入session后跳转到购物车页面
        List<ShopCar> shopCars = iShopCarService.findShopCarsByCustomer(id);
        session.setAttribute("shopCars",shopCars);
        resp.sendRedirect("/shopCar.jsp");
    }
}
