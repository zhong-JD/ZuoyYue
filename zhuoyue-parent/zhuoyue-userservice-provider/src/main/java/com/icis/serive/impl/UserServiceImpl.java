package com.icis.serive.impl;

import com.icis.mapper.UserMapper;
import com.icis.pojo.UserInfo;
import com.icis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//接口实现类
//放入Spring容器中
//@Service  把服务放到本次 不能用了
@com.alibaba.dubbo.config.annotation.Service// 根据你的配置 把服务放置到注册中心
public class UserServiceImpl implements UserService {
    //注入mapper
    @Autowired
    private UserMapper userMapper;
    @Override
    public List<UserInfo> findAll() {
        return userMapper.selectAll();
    }
}
