package com.briup.web.servlet;

import com.briup.bean.Customer;
import com.briup.bean.ShopAddress;
import com.briup.bean.ShopCar;
import com.briup.service.IShopAddressService;
import com.briup.service.IShopCarService;
import com.briup.service.impl.ShopAddressServiceImpl;
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
 * 实现购物车页面的去提交按钮，提交的时候需要删除原本购物车的信息和移除原来的session
 */
@WebServlet("/shopcarcommit")
public class ShopCarCommitServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //首先你要查询当前用户的所有收货地址存到session以便cofirm显示/清空当前用户的购物车
        HttpSession session = req.getSession();
        Customer customer = (Customer) session.getAttribute("customer");
        IShopAddressService iShopAddressService = new ShopAddressServiceImpl();
        List<ShopAddress> address = iShopAddressService.findAddressByCustomerId(customer.getId());
        //把查询的地址信息存到session中
        session.setAttribute("Address",address);
        //把session里的购物车记录放到一个confirm,相当于备份到另外一个session
        List<ShopCar> shopCars = (List<ShopCar>) session.getAttribute("shopCars");
        session.setAttribute("confrim" ,shopCars);
        // 删除原本的购物车session
        session.removeAttribute("shopCars");
       //清空当前用户的购物车
        IShopCarService iShopCarService = new ShopCarServiceImpl();
        iShopCarService.deleteShopCarByCid(customer.getId());
        //跳转到提交页面
        resp.sendRedirect("/confirm.jsp");
    }
}
