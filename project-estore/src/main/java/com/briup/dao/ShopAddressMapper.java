package com.briup.dao;

import java.util.List;

import com.briup.bean.ShopAddress;
import org.apache.ibatis.annotations.Param;

public interface ShopAddressMapper {
//	根据用户id查询当前用户的地址
	List<ShopAddress> findAddressByCustomerId(@Param("id") Integer id);
//	新增地址
	void saveAddress(ShopAddress sd);

	ShopAddress findShopAddressById(Integer id);
}
