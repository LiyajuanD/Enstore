package com.briup.web.listener;

import com.briup.bean.Book;
import com.briup.bean.Category;
import com.briup.service.IBookService;
import com.briup.service.ICategoryService;
import com.briup.service.impl.BookServiceImpl;
import com.briup.service.impl.CategoryServiceImpl;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.List;

/**
 * @author dell
 * 目标：index.jsp首页左侧的图书分类信息从数据库查询并动态显示出来，把主页下方的图书信息从数据库中查询出来
 * 监听ServletContext的生命周期，创建时机为项目启动的时候
 * 启动时就把数据查出来并放到ServletContext中；
 * ServletContext是最大的作用域，别人不管是否登录都会访问到
 */
@WebListener
public class IndexLisener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        System.out.println("ServletContext创建了");
        //获取ServletContext对象
        ServletContext servletContext = servletContextEvent.getServletContext();
        //查询所有的图书
        ICategoryService iCategoryService = new CategoryServiceImpl();
        List<Category> allCategorys = iCategoryService.findAllCategorys();

        //存到作用域中
        servletContext.setAttribute("CategoryList",allCategorys);
        //查询图书信息
        IBookService iBookService = new BookServiceImpl();
        List<Book> allBooks = iBookService.findAllBooks();
        servletContext.setAttribute("allBooks",allBooks);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
