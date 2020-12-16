package com.icis.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.icis.pojo.UserInfo;
import com.icis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

//放入Spring容器中
@Controller
public class UserController {
    //注入Servcie
   // @Autowired// 自动注入当前容器的接口实现类  本地服务
    @Reference// 拉取注册中心上的服务
    private UserService userService;
    //配置一个路径
    @GetMapping("/findAll")
    @ResponseBody
    public List<UserInfo> findAll(){
        return  this.userService.findAll();
    }
}
