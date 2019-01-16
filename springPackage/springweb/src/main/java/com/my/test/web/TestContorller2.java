package com.my.test.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.my.test.api.interfaces.UserInfoService;
import com.my.test.api.model.User;
import com.my.test.dao.entity.Page;
import com.my.test.dubbo.config.annotation.Reference;

@Controller
@RequestMapping("/test2")
public class TestContorller2 {
	
	private UserInfoService userInfoService;

	public UserInfoService getUserInfoService() {
		return userInfoService;
	}
	@Reference(registry="zookeeper",protocol="dubbo",version="2.0")
	public void setUserInfoService(UserInfoService userInfoService) {
		this.userInfoService = userInfoService;
	}

	@RequestMapping("/userlist")
	public @ResponseBody List<User> listInventory(User user) throws Exception {
		return userInfoService.queryUserInfos(user);

	}
	
	@RequestMapping("/user")
	public @ResponseBody User queryUser(@RequestBody User user) throws Exception {
		return userInfoService.queryUserInfo(user);

	}
	
	public static void main(String[] args) {
		try {
			Class.forName("com.my.test.api.interfaces.UserInfoService");
			System.out.println("com.my.test.api.interfaces.UserInfoService");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
