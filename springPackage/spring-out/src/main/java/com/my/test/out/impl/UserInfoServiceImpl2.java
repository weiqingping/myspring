package com.my.test.out.impl;

import java.util.ArrayList;
import java.util.List;

import com.my.test.api.interfaces.UserInfoService;
import com.my.test.api.model.User;
import com.my.test.dubbo.config.annotation.Service;
import com.my.test.dubbo.config.util.LoadBanlance;

@Service(protocol="http,dubbo", version="2.0",registry="zookeeper",serialize="hessian",loadBanlance=LoadBanlance.RANDOM)
public class UserInfoServiceImpl2 implements UserInfoService {

	@Override
	public List<User> queryUserInfos(User user) throws Exception {
		System.out.println("the service2 is called"+user);
		List<User>userList=new ArrayList<User>(2);
		
		userList.add(new User(1, "ZHANGSAN"));
		userList.add(new User(2, "LISI"));
		return userList;
	}

	@Override
	public User queryUserInfo(User user) throws Exception {
		// TODO Auto-generated method stub
		return new User(2, "version2.0");

	}

}
