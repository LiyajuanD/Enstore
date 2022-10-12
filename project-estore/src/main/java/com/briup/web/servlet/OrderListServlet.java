package com.briup.web.servlet;

import com.briup.bean.Customer;
import com.briup.bean.OrderForm;
import com.briup.service.IOrderFormService;
import com.briup.service.impl.OrderFormServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "orderListServlet", value = "/orderList")
public class OrderListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        IOrderFormService orderFormService = new OrderFormServiceImpl();
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");
//        customer.getId()通过这个方法获取到用户的id
        Integer customerId = customer.getId();
        List<OrderForm> orderFormList = orderFormService.findOrderFormByCustomerId(customerId);
        session.setAttribute("orderList",orderFormList);
        response.sendRedirect("/orderlist.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
