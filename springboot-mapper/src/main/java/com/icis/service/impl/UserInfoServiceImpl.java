package com.icis.service.impl;

import com.icis.mapper.UserInfoMapper;
import com.icis.pojo.UserInfo;
import com.icis.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

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
    //根据用户实体查询用户数据
    @Override
    public List<UserInfo> findByUserInfo(UserInfo userInfo) {
        return userInfoMapper.select(userInfo);
    }
    //模糊查询
    @Override
    public List<UserInfo> findByUserLike(UserInfo userInfo) {
        //构建一个Example条件查询对象
        Example example=new Example(UserInfo.class);
        //构建条件 集合
        Example.Criteria criteria = example.createCriteria();
        //添加具体条件 property 字段名 字段值
        criteria.andLike("name","%"+userInfo.getName()+"%");
        List<UserInfo> infoList = userInfoMapper.selectByExample(example);
        return infoList;
    }
}
