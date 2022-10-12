package com.briup.dao;

import java.util.List;

import com.briup.bean.Book;
import org.apache.ibatis.annotations.Param;

public interface BookMapper {
	List<Book> findAllBooks();
	Book findBookById(@Param("bookid") Integer id);
}
