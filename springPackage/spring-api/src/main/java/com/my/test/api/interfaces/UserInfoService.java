package com.my.test.api.interfaces;

import java.util.List;

import com.my.test.api.model.User;

public interface UserInfoService {
public List<User>queryUserInfos(User user)throws Exception;
public User queryUserInfo(User user)throws Exception;

}
