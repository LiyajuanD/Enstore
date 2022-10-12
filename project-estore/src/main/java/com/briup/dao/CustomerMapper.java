package com.briup.dao;

import com.briup.bean.Customer;
import org.apache.ibatis.annotations.Param;

public interface CustomerMapper {

	void saveCustomer(Customer customer);
	Customer findCustomerByName(@Param("name") String name);

}
