package com.icis.controller;

import com.icis.pojo.UserInfo;
import com.icis.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//用户控制器
@RestController //@Controller+@ResponseBody
public class UserInfoController {
    //注入Service
    @Autowired
    private UserInfoService userInfoService;

    //查询所有用户数据
    @GetMapping("/findAll")
    public List<UserInfo>  findAll(){
        //调用service查询所有用户数据
        List<UserInfo> allUser = this.userInfoService.findAll();
        return allUser;

    }
}
