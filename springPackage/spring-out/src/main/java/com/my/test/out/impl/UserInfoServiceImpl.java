package com.my.test.out.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.my.test.api.interfaces.UserInfoService;
import com.my.test.api.model.User;
import com.my.test.dao.entity.UserInfo;
import com.my.test.dao.interfaces.UserInfoMapper;
import com.my.test.dubbo.config.annotation.Service;
import com.my.test.dubbo.config.util.LoadBanlance;

@Service(protocol="http,dubbo",version="1.0", registry="zookeeper",serialize="hessian",loadBanlance=LoadBanlance.RANDOM)
//@Component
public class UserInfoServiceImpl implements UserInfoService {

	@Autowired
	private UserInfoMapper userMappler;
	@Override
	public List<User> queryUserInfos(User user) throws Exception {
		System.out.println("the service is called"+user);
		List<User>userList=new ArrayList<User>(2);
		
		userList.add(new User(1, "weiqp"));
		userList.add(new User(2, "qfr"));
		return userList;
	}

	@Override
	public User queryUserInfo(User user) throws Exception {
		UserInfo info= userMappler.selectByPrimaryKey(user.getId());
		if(info!=null){
			return new User(2, "version1.0");
		}
		return null;
	}

}
