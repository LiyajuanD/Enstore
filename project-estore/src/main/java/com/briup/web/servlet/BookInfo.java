package com.briup.web.servlet;

import com.briup.bean.Book;
import com.briup.service.IBookService;
import com.briup.service.impl.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

/**
 * @author dell
 * 目标：在主页上点击图书图片或点击“更多精彩点击进入”跳转到图书详情页并展示具体详情
 * 2.注意：所有的超链接请求都是get请求，servlet中就可以只写doget方法
 */
@WebServlet("/bookInfo")
public class BookInfo extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //接收参数
        String bookid = req.getParameter("bookid");
        System.out.println(bookid);
        //调用service层的方法查询数据
        IBookService iBookService = new BookServiceImpl();
        Book bookById = iBookService.findBookById(Integer.valueOf(bookid));
        System.out.println(bookById);
        //查出来的数据存入session中
        HttpSession session = req.getSession();
        session.setAttribute("book",bookById);
        Date pubDate = bookById.getPubDate();

        //跳转到指定的详情页
        //req.getRequestDispatcher("/viewBook.jsp").forward(req,resp);
        resp.sendRedirect("/viewBook.jsp");
    }
}
