package com.briup.service.impl;

import com.briup.bean.OrderLine;
import com.briup.dao.OrderLineMapper;
import com.briup.dao.ShopAddressMapper;
import com.briup.service.IOrderLineService;
import com.briup.util.MyBatisSqlSessionFactory;
import org.apache.ibatis.session.SqlSession;

import java.util.List;
import java.util.Map;

/**
 * @author dell
 */
public class OrderLineServiceImpl implements IOrderLineService {
    SqlSession sqlSession = MyBatisSqlSessionFactory.openSession(true);
    OrderLineMapper mapper = sqlSession.getMapper(OrderLineMapper.class);
    @Override
    public void saveOrderLine(OrderLine ol) {
        mapper.saveOrderLine(ol);
    }

    @Override
    public List<OrderLine> findOrderLineByOrderId(Integer id) {
      return   mapper.findOrderLineByOrderId(id);
    }
}
