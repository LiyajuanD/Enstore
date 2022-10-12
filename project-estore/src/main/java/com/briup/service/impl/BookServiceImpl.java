package com.briup.service.impl;

import com.briup.bean.Book;
import com.briup.dao.BookMapper;
import com.briup.dao.CategoryMapper;
import com.briup.service.IBookService;
import com.briup.util.MyBatisSqlSessionFactory;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * @author dell
 * 把主页下方的图书信息从数据库中查询出来
 */
public class BookServiceImpl implements IBookService {
    SqlSession sqlSession = MyBatisSqlSessionFactory.openSession(true);
    BookMapper mapper = sqlSession.getMapper(BookMapper.class);
    @Override
    public List<Book> findAllBooks() {
        List<Book> allBooks = mapper.findAllBooks();
        return allBooks;
    }

    @Override
    public Book findBookById(Integer id) {
//        在主页上点击图书图片或点击“更多精彩点击进入”跳转到图书详情页并展示具体详情
        //根据书的id查询数据
        Book bookById = mapper.findBookById(id);
        return bookById;
    }
}
