package com.icis.ctroller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.icis.pojo.BaseAttrInfo;
import com.icis.pojo.BaseCatalog1;
import com.icis.pojo.BaseCatalog2;
import com.icis.pojo.BaseCatalog3;
import com.icis.service.ManageService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//后台管理控控制器
@RestController
//解决跨域问题
@CrossOrigin
public class ManagerController {
    //引用服务
    @Reference
    private ManageService manageService;

    //构建请求路径  会的一级分类列表
    @PostMapping("/getCatalog1")
    public List<BaseCatalog1> getCatalog1(){
        //获得一级分类列表
        List<BaseCatalog1> baseCatalog1s = this.manageService.getCal1();
        return baseCatalog1s;
    }
    // 查询二级分类列表 /getCatalog2?catalog1Id=4
    @RequestMapping("/getCatalog2")
    public List<BaseCatalog2> getCatalog2(String catalog1Id){
         BaseCatalog1 catalog1=new BaseCatalog1();
         catalog1.setId(catalog1Id);
        //获得一级分类列表
        List<BaseCatalog2> baseCatalog2s = this.manageService.getCal2(catalog1);
        return baseCatalog2s;
    }
    //查询三级分裂
    @RequestMapping("/getCatalog3")
    public List<BaseCatalog3> getCatalog3(String catalog2Id){
        BaseCatalog2 catalog2=new BaseCatalog2();
        //获得一级分类列表
        catalog2.setId(catalog2Id);

        List<BaseCatalog3> baseCatalog3s = this.manageService.getCal3(catalog2);
        return baseCatalog3s;
    }
    //根据三级分类列表获得平台属性列表  /attrInfoList?catalog3Id=1
    @RequestMapping("/attrInfoList")
    public List<BaseAttrInfo> getAttrInfoList(String catalog3Id){
        BaseCatalog3 baseCatalog3=new BaseCatalog3();
        baseCatalog3.setId(catalog3Id);
        //根据三级分类 获得 平台属性
        List<BaseAttrInfo> baseAttrInfos = this.manageService.getBaseAttrInfo(baseCatalog3);
        return baseAttrInfos;

    }



}
