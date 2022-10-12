package com.briup.service.impl;

import com.briup.bean.Customer;
import com.briup.dao.CustomerMapper;
import com.briup.service.ICustomerService;
import com.briup.util.MyBatisSqlSessionFactory;
import org.apache.ibatis.session.SqlSession;

/**
 * @author dell
 * 操作用户
 * 得到web层传过来的数据后，处理
 *传给dao层实现持久化
 */
public class CustomerServiceImpl implements ICustomerService {
    //注册用户信息
    @Override
    public void register(Customer customer) {
        //1.数据是否合法
        if(customer.getName() == null || customer.getName().trim().equals("")){
            throw  new RuntimeException("用户名不能为空");
        }
        //2.传给dao层实现数据库操作，先获取执行sql的对象，获取相应的mapper接口
        SqlSession sqlSession = MyBatisSqlSessionFactory.openSession(true);
        CustomerMapper mapper = sqlSession.getMapper(CustomerMapper.class);
        //3.判断用户名是否存在
        Customer customerByName = mapper.findCustomerByName(customer.getName());
        if(customerByName !=null){
            throw new RuntimeException("用户名已经存在");
        }else {
            //执行注册方法
            mapper.saveCustomer(customer);
            sqlSession.commit();
        }
    }
    //通过用户名字查找用户信息
    @Override
    public Customer findCustomerByName(String name) {
        return null;
    }

    @Override
    public Customer login(String name, String password) {
        SqlSession sqlSession = MyBatisSqlSessionFactory.openSession(true);
        CustomerMapper mapper = sqlSession.getMapper(CustomerMapper.class);
        Customer customerByName;
        //判断是否合法
        if(name == null || name.trim().equals("")){
            throw new RuntimeException("用户名不能为空");
        } else if (password == null || password.trim().equals("")) {
            throw new RuntimeException("密码不能为空");
        }else {
            //根据输入的用户名去数据库查询用户获得用户密码并与输入密码做对比
            customerByName = mapper.findCustomerByName(name);
            String password1 = customerByName.getPassword();
            if(password1.equals(password)){
                return customerByName;
            }else {
                throw new RuntimeException("密码错误！");
            }
        }
    }
}
