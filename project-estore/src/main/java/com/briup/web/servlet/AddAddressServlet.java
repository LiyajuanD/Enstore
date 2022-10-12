package com.briup.web.servlet;

import com.briup.bean.Customer;
import com.briup.bean.ShopAddress;
import com.briup.service.IShopAddressService;
import com.briup.service.IShopCarService;
import com.briup.service.impl.ShopAddressServiceImpl;
import com.briup.service.impl.ShopCarServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author dell
 */
@WebServlet("/addAddress")
public class AddAddressServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Customer customer = (Customer) req.getSession().getAttribute("customer");
        String receiveName = req.getParameter("receiveName");
        String address = req.getParameter("address");
        String phone = req.getParameter("phone");
        IShopAddressService iShopAddressService = new ShopAddressServiceImpl();
        iShopAddressService.saveAddress(new ShopAddress(receiveName,address,phone,customer));
    }
}
