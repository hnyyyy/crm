package com.atguigu.crm.test;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.atguigu.crm.entity.User;
import com.atguigu.crm.mapper.UserMapper;

public class ApplicationContextTest {
	private ApplicationContext ctx=null;
	private UserMapper userMapper =null;
	{
		ctx=new ClassPathXmlApplicationContext("spring-context.xml");
		userMapper=ctx.getBean(UserMapper.class);
	}
	
	@Test
	public void testDataSource() throws SQLException{
		DataSource dataSource = ctx.getBean(DataSource.class);
		Connection connection = dataSource.getConnection();
		System.out.println(connection);
	}
	
	@Test
	public void testUserMapper(){
		User user=userMapper.getUserByName("admin");
		System.out.println(user.getPassword());
		System.out.println(user.getRole().getName());
	}
}
