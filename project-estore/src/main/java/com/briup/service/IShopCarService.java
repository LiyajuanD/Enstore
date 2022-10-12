package com.briup.service;

import java.util.List;

import com.briup.bean.ShopCar;
import org.apache.ibatis.annotations.Param;

public interface IShopCarService {
	//保存购物车信息
	void saveShopCar(ShopCar sc);
	//更新购物车信息
	void updateShopCar(ShopCar sc);
	//根据书籍ID和顾客ID查找购物车信息
	ShopCar queryShopCarByCidAndBId(@Param("cid") int cid, @Param("bid") int bid);
	//根据书籍ID查找购物车信息
	List<ShopCar> findShopCarsByCustomer(Integer id);

	void deleteShopCarByCidAndBId(@Param("cid") int cid, @Param("bid") int bid);

	//根据用户id删除购物车
	void deleteShopCarByCid(Integer id);
}
