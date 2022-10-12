package com.briup.service.impl;

import com.briup.bean.ShopAddress;
import com.briup.dao.CategoryMapper;
import com.briup.dao.ShopAddressMapper;
import com.briup.service.IShopAddressService;
import com.briup.util.MyBatisSqlSessionFactory;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * @author dell
 */
public class ShopAddressServiceImpl implements IShopAddressService {
    SqlSession sqlSession = MyBatisSqlSessionFactory.openSession(true);
    ShopAddressMapper mapper = sqlSession.getMapper(ShopAddressMapper.class);
    @Override
    public List<ShopAddress> findAddressByCustomerId(Integer id) {
        List<ShopAddress> addressByCustomerId = mapper.findAddressByCustomerId(id);
        return addressByCustomerId;
    }

    @Override
    public void saveAddress(ShopAddress sd) {
        mapper.saveAddress(sd);
    }

    @Override
    public ShopAddress findShopAddressById(Integer id) {
        return mapper.findShopAddressById(id);
    }


}
