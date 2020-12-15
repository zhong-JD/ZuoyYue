package com.icis.service.impl;

import com.icis.mapper.UserInfoMapper;
import com.icis.pojo.UserInfo;
import com.icis.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//用户接口实现类
@Service
public class UserInfoServiceImpl implements UserInfoService{
    //注入mapper
    @Autowired
    private UserInfoMapper userInfoMapper;

    //查询所有用户方法
    @Override
    public List<UserInfo> findAll() {
        //查询所有用户数据
        List<UserInfo> userInfoList = this.userInfoMapper.selectAll();
        return userInfoList;
    }
}
