package com.my.test.service.task;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.my.test.dao.entity.UserInfo;
import com.my.test.dao.interfaces.UserInfoMapper;
import com.my.test.task.annotation.MethodName;
@Component("testTask")
public class TaskTest {
 @Autowired
  private UserInfoMapper mapper;
  @MethodName("testCase1")
  public void test(String test1,String test2){
	  System.out.println("-------------------task开始了-------------------"+test1+" "+test2);
	  System.out.println("Autowired引入查询:"+(mapper.selectByExample(null)).toString());
  }
  
  @MethodName("testCase2")
  public void test(String test1,UserInfo user,UserInfoMapper userService ){
	  System.out.println("test1:"+test1);
	  System.out.println("userInfo:"+user.toString());
	  List<UserInfo>result=userService.selectByExample(null);
	  System.out.println("参数引入查询:"+result.toString());

  }
  
  @MethodName("testCase3")
  public void test(UserInfo user){
	  System.out.println("testCase3:userInfo:"+user.toString());
  }
  
  
  public static void main(String[] args) {
	  UserInfo user=new UserInfo();
	  user.setUserId(4);
	  user.setUserName("weiqpqiiiqq");
	  System.out.println(JSON.toJSONString(user));
	  
}
}
