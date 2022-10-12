package com.briup.web.servlet;

import com.briup.bean.*;
import com.briup.service.IOrderFormService;
import com.briup.service.IOrderLineService;
import com.briup.service.IShopAddressService;
import com.briup.service.IShopCarService;
import com.briup.service.impl.OrderFormServiceImpl;
import com.briup.service.impl.OrderLineServiceImpl;
import com.briup.service.impl.ShopAddressServiceImpl;
import com.briup.service.impl.ShopCarServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * @author dell
 */
@WebServlet("/ordercommit")
public class OrderCommitServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IShopAddressService shopAddressService = new ShopAddressServiceImpl();
        IOrderFormService orderFormService = new OrderFormServiceImpl();
        IOrderLineService orderLineService = new OrderLineServiceImpl();
        //            1、接收参数
        String receiveName = req.getParameter("receiveName");
        String address = req.getParameter("address");
        String phone = req.getParameter("phone");
        String shipAddId = req.getParameter("shipAddId");
        //获取当前登录用户
        HttpSession session = req.getSession();
        Customer customer = (Customer) session.getAttribute("customer");
//        ....接收其他参数
//        判断一下地址是新增还是 原来的地址
//        如果说 前端获取到的receiveName，address，phone不为空的话 就认为要新增地址
//        判断receiveName是否为空 还要判断address phone
        ShopAddress shopAddress = new ShopAddress();
        if(receiveName.equals("")&&phone.equals("")&&address.equals("")){
            //使用旧地址
            //没有选择地址情况下
            if(shipAddId.equals("")){
                resp.sendRedirect("/confirm");
                return;
            }else{
                Integer addId = Integer.valueOf(shipAddId);
                shopAddress.setId(addId);
            }
        }else{
//            不为空为新地址
            shopAddress.setAddress(address);
            shopAddress.setReceiveName(receiveName);
            shopAddress.setPhone(phone);
            shopAddress.setCustomer(customer);
            shopAddressService.saveAddress(shopAddress);
        }
//        可以通过接收参数的形式获取旧地址的id
//        通过这个地址id查询地址信息（拿到id之后new ShopAddress只把id放进去）
//        开始准备一个OrderForm对象（时间可以直接newDate）
        List<ShopCar> shopCarList = (List<ShopCar>) session.getAttribute("confrim");
        Double total = 0.0;
        for (ShopCar shopCar : shopCarList) {
            total = total + (shopCar.getNum()*shopCar.getBook().getPrice());
        }

        OrderForm orderForm = new OrderForm();
        orderForm.setCost(total);
        orderForm.setOrderdate(new Date());
        orderForm.setShopAddress(shopAddress);
        orderForm.setCustomer(customer);

//        并且调用对应的方法saveOrderForm（需要selectkey）
        orderFormService.saveOrderForm(orderForm);
//        把他对应的订单项存入数据库当中（需要订单表的id）
        for (ShopCar shopCar : shopCarList) {
            OrderLine orderLine = new OrderLine();
            orderLine.setBook(shopCar.getBook());
            orderLine.setNum(Long.valueOf(shopCar.getNum()));
            orderLine.setCost(shopCar.getNum()*shopCar.getBook().getPrice());
            orderLine.setOrderForm(orderForm);
            orderLineService.saveOrderLine(orderLine);
        }
//        结束
        resp.sendRedirect("/orderList");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
   /* @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //接收参数
        String receiveName = req.getParameter("receiveName");
        String address = req.getParameter("address");
        String phone = req.getParameter("phone");
        String shipAddId = req.getParameter("shipAddId");
        //获取当前登录用户
        Customer customer = (Customer) req.getSession().getAttribute("customer");

        //实例化对地址操作的对象
        IShopAddressService iShopAddressService = new ShopAddressServiceImpl();
        ShopAddress shopAddressById=new ShopAddress();

        //判断是否为新增地址
        if (receiveName.equals("") && address.equals("") && phone.equals("")){
            //查询当前选中的旧地址id
            if (shipAddId.equals("")){
                resp.sendRedirect("/confirm.jsp");
            }else {
                //shopAddressById = iShopAddressService.findShopAddressById(Integer.parseInt(shipAddId));
                shopAddressById .setId(Integer.parseInt(shipAddId));
                System.out.println(shopAddressById);
            }
        }else {
            //如果前端获取到数据不为空的话，就认为是新增地址,如果都为空，就选择旧地址，就地址可以通过接收shipAddId参数的形式获取旧地址的id，通过对应方法查到当前的地址信息
            shopAddressById=new ShopAddress(receiveName,address,phone,customer);
            //把新的地址存入数据库
            iShopAddressService.saveAddress(shopAddressById);

            System.out.println(shopAddressById);
        }
        //获取当前所有的订单信息计算价格
        double total =0;
        List<ShopCar> confrim = (List<ShopCar>) req.getSession().getAttribute("confrim");
        for (ShopCar shopCar : confrim) {
            total=total+shopCar.getBook().getPrice()*shopCar.getNum();
        }
        System.out.println(total);
  //准备一个orderform对象，往表里面塞对象，时间可以直接newdate，然后调用方法保存订单信息
        OrderForm orderForm = new OrderForm(total,new Date(),customer,shopAddressById);
        System.out.println(orderForm);
        //把orderform存入数据库
        IOrderFormService iOrderFormService = new OrderFormServiceImpl();
        iOrderFormService.saveOrderForm(orderForm);
        //把对应的订单项存入数据库当中（orderline）
        for (ShopCar shopCar : confrim) {
            IOrderLineService iOrderLineService = new OrderLineServiceImpl();
            OrderLine orderLine = new OrderLine();
            orderLine.setBook(shopCar.getBook());
            orderLine.setNum((long) shopCar.getNum());
            orderLine.setCost((shopCar.getBook().getPrice()*shopCar.getNum()));
            orderLine.setOrderForm(orderForm);
            System.out.println(orderLine);
           iOrderLineService.saveOrderLine(orderLine);
        }
    }*/

}
