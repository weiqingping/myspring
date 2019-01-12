package com.my.test.dao.interfaces;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.my.test.dao.entity.Page;
import com.my.test.dao.entity.UserInfo;

@Component
public interface UserMapper {
	
	List<UserInfo>selectUserByName(@Param("name")String  name)throws Exception;
	
	List<UserInfo>selectUserByName( @Param("name")String  name, Page page)throws Exception;
	
	Page selectUserByPage (@Param("name")String  name, Page page)throws Exception;



}
