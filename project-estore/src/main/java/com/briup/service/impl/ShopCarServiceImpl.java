package com.briup.service.impl;

import com.briup.bean.ShopCar;
import com.briup.dao.BookMapper;
import com.briup.dao.ShopCarMapper;
import com.briup.service.IShopCarService;
import com.briup.util.MyBatisSqlSessionFactory;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * @author dell
 */
public class ShopCarServiceImpl implements IShopCarService {
    SqlSession sqlSession = MyBatisSqlSessionFactory.openSession(true);
    ShopCarMapper mapper = sqlSession.getMapper(ShopCarMapper.class);
    @Override
    public void saveShopCar(ShopCar sc) {
//        获取穿过来的sc的cid和bid
        int customerId = sc.getCustomerId();
        int bookId = sc.getBookId();
//        在数据库中查找是否含有这条数据
        ShopCar shopCar = mapper.queryShopCarByCidAndBId(customerId,bookId);
//        如果不含有这条数据，就执行插入操作
        if(shopCar==null){
            mapper.saveShopCar(sc);
        }else{
            //要是有，获取查询出来的shopCar的num值与当前传过来的sc的数量相加，封装为一个新的shopCar对象，然后更新数据库操作
            int num = shopCar.getNum();
            shopCar.setNum(num+sc.getNum());
            //执行更新，更新表中的数量
            mapper.updateShopCar(shopCar);
        }
    }

    @Override
    public void updateShopCar(ShopCar sc) {
        mapper.updateShopCar(sc);
    }

    @Override
    public ShopCar queryShopCarByCidAndBId(int cid, int bid) {
        return mapper.queryShopCarByCidAndBId(cid,bid);
    }

    @Override
    public List<ShopCar> findShopCarsByCustomer(Integer id) {
        return mapper.findShopCarsByCustomer(id);
    }

    @Override
    public void deleteShopCarByCidAndBId(int cid, int bid) {
        mapper.deleteShopCarByCidAndBId(cid,bid);
    }

    @Override
    public void deleteShopCarByCid(Integer id) {
        mapper.deleteShopCarByCid(id);
    }
}
