package com.icis.service;

import com.icis.pojo.UserInfo;

import java.util.List;

//用户Servcie接口
public interface UserInfoService {
    //1.查询所有用户数据
    public List<UserInfo>  findAll();
    //根据条件查询
    public List<UserInfo> findByUserInfo(UserInfo userInfo);
    //模糊查询
    public List<UserInfo> findByUserLike(UserInfo userInfo);

    //保存用户方法
    public void addUser(UserInfo userInfo);
    //有选择性保存用户数据
    void addUserSelective(UserInfo userInfo);
    //更新用户数据
    void updateUser(UserInfo userInfo);
    //有选择性更新
    public void updateUserSelective(UserInfo userInfo);
     //根据名字更新
    void updateUserByName(UserInfo userInfo);
    //根据主键删除
    void delUserByPrimaryKey(UserInfo userInfo);
     //根据条件删除数据
    void deleteUserByName(UserInfo userInfo);
}
