package com.my.test.web;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.my.test.core.BusinessException;
import com.my.test.dao.entity.Page;
import com.my.test.dao.entity.UserInfo;
import com.my.test.model.Inventory;
import com.my.test.model.User;
import com.my.test.service.UserService;

@Controller
@RequestMapping("/test")
public class TestContorller {
	@Autowired
	private  UserService userService;
	
	@RequestMapping("/hello")
	public @ResponseBody String hello(User user, @RequestParam(required = false) String sex) {

		return "name:" + user.getName() + "sex:" + sex;

	}

	@RequestMapping("/userlist")
	public @ResponseBody Page listInventory(User user) throws Exception {
		
		Page page=new Page();
		page.setPageNumber(0);
		page.setPageSize(5);
		return userService.selectUserByPage(user.getName(), page);

	}
	


	@RequestMapping("/inventory2")
	public @ResponseBody List<Inventory> listInventory(@RequestParam(value="name") String name)throws Exception {
		List<Inventory> result = new ArrayList<Inventory>();
		result.add(new Inventory(1, "inv1"));
		result.add(new Inventory(1, "inv2"));
		throw new Exception("1223444444");

	}

}
