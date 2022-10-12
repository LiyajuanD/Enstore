package com.briup.dao;

import com.briup.bean.ShopCar;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShopCarMapper {
    //保存购物车信息
    void saveShopCar(ShopCar sc);
    //更新购物车信息
    void updateShopCar(ShopCar sc);

    //根据书籍ID和顾客ID查找购物车信息
    ShopCar queryShopCarByCidAndBId(@Param("cid") int cid, @Param("bid") int bid);
    //根据用户ID查找购物车信息
    List<ShopCar> findShopCarsByCustomer(@Param("id") Integer id);
    //根据用户id和书本id删除购物车信息
    void deleteShopCarByCidAndBId(@Param("cid") int cid, @Param("bid") int bid);

    //根据用户id删除购物车
    void deleteShopCarByCid(Integer id);
}
