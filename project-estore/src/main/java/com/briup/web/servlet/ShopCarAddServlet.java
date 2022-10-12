package com.briup.web.servlet;

import com.briup.bean.Book;
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
 */
@WebServlet("/ShopCarAdd")
public class ShopCarAddServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取表单提交的基本数据
        int num = Integer.parseInt(req.getParameter("num"));
        int bookid = Integer.parseInt(req.getParameter("bookid"));
//        由于customer对象可以通过session获取，然后获取customer的id
        Customer customer = (Customer) req.getSession().getAttribute("customer");
        Integer customerid = customer.getId();
        //将获取的数据封装为shopcar对象
        ShopCar shopCar = new ShopCar(num,bookid,customerid);
        //调用service层的方法实现插入到数据库的操作
        IShopCarService iShopCarService = new ShopCarServiceImpl();
        iShopCarService.saveShopCar(shopCar);
        //把所有的购物车里的数据都查询出来
        List<ShopCar> shopCars = iShopCarService.findShopCarsByCustomer(customerid);
        HttpSession session = req.getSession();
        session.setAttribute("shopCars",shopCars);
        resp.sendRedirect("/shopCar.jsp");
    }
}
