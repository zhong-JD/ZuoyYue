package com.icis.ctroller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.icis.pojo.SpuImage;
import com.icis.service.ManageService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//Sku操作控制器
@RestController
@CrossOrigin
public class SkuController {
    //引用服务
    @Reference
    private ManageService manageService;


    //根据SpuId获得图片列表 /spuImageList?spuId=58
    @RequestMapping("/spuImageList")
    public List<SpuImage> getSpuImageList(SpuImage spuImage){
       return this.manageService.getSpuImageList(spuImage);
    }

    //根据三级分类Id获得平台属性和平台属性值 http://localhost:8082/attrInfoList?catalog3Id=61

}
