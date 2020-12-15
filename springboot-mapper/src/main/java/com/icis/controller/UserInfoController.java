package com.icis.controller;

import com.icis.pojo.UserInfo;
import com.icis.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    //根据用户实体查询用户数据
    @GetMapping("findByUserInfo")
    public List<UserInfo>  findByUserInfo(UserInfo userInfo){
        //调用service查询所有用户数据
        List<UserInfo> allUser = this.userInfoService.findByUserInfo(userInfo);
        return allUser;

    }
    //根据名字模糊查询用户
    @GetMapping("/findUserByName")
    public List<UserInfo>  findUserByName(UserInfo userInfo){
        //调用service查询所有用户数据
        List<UserInfo> allUser = this.userInfoService.findByUserLike(userInfo);
        return allUser;

    }
    //添加用户数据
    @PostMapping("/addUser")
    public String addUser(@RequestBody  UserInfo userInfo){
        //调用service 保存数据
        this.userInfoService.addUser(userInfo);
        return  "ok";
    }
    //添加用户数据  有选择性的添加   null值不添加
    @PostMapping("/addUserSelective")
    public String addUserSelective(@RequestBody  UserInfo userInfo){
        //调用service 保存数据
        this.userInfoService.addUserSelective(userInfo);
        return  "ok";
    }
    //更新用户数据
    @PostMapping("/updateUser")
    public String updateUser(@RequestBody  UserInfo userInfo){
        //调用service 保存数据
        this.userInfoService.updateUser(userInfo);
        return  " update ok";
    }
    //有选择性更新数据  update table set where id=?
    @PostMapping("/updateUserSelective")
    public String updateUserSelective(@RequestBody  UserInfo userInfo){
        //调用service 保存数据
        this.userInfoService.updateUserSelective(userInfo);
        return  " updateSelective ok";
    }
    //安装条件更新  更新名字有猪子的
    @PostMapping("/updateUserByName")
    public String updateUserByName(@RequestBody UserInfo userInfo){
        //调用service 保存数据
        this.userInfoService.updateUserByName(userInfo);
        return  " updates ok";
    }

    //删除数据 根据主键删除
    @GetMapping("/delUserByPrimaryKey")
    public String delUserByPrimaryKey( UserInfo userInfo){
        //调用service 保存数据
        this.userInfoService.delUserByPrimaryKey(userInfo);
        return  " delete ok";
    }
    //根据条件删除数据
    @GetMapping("/deleteUserByName")
    public String deleteUserByName( UserInfo userInfo){
        //调用service 保存数据
        this.userInfoService.deleteUserByName(userInfo);
        return  " deletes ok";
    }



}
