package com.my.test.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.my.test.dao.entity.Page;
import com.my.test.dao.entity.UserInfo;
import com.my.test.dao.interfaces.UserMapper;

@Service
public class UserService {
	@Autowired
	private UserMapper userMapper;
	public List<UserInfo>selectUserByName( String  name)throws Exception{
		return userMapper.selectUserByName(name);
	}
	
	public List<UserInfo>selectUserByName(String  name, Page page)throws Exception{
		return userMapper.selectUserByName(name, page);
	}
	
	public Page selectUserByPage (@Param("name")String  name, Page page)throws Exception{
		return userMapper.selectUserByPage(name, page);
	}

}
