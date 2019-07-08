package com.atguigu.atcrowdfunding.service;

import javax.xml.ws.soap.Addressing;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class HelloService {
	
	@Autowired
	SqlSessionFactory sessionFactory;
	
	public void hello(){
		
	}

}
