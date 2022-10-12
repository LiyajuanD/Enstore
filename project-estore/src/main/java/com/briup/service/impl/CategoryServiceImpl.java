package com.briup.service.impl;

import com.briup.bean.Category;
import com.briup.dao.CategoryMapper;
import com.briup.dao.CustomerMapper;
import com.briup.service.ICategoryService;
import com.briup.util.MyBatisSqlSessionFactory;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * @author dell
 */
public class CategoryServiceImpl implements ICategoryService {
    SqlSession sqlSession = MyBatisSqlSessionFactory.openSession(true);
    CategoryMapper mapper = sqlSession.getMapper(CategoryMapper.class);
    @Override
    public List<Category> findAllCategorys() {

        return mapper.findAllCategorys();
    }
}
