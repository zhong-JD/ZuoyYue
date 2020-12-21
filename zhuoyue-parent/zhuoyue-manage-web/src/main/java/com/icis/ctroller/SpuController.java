package com.icis.ctroller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.icis.pojo.BaseSaleAttr;
import com.icis.pojo.SpuInfo;
import com.icis.service.ManageService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//Spu控制器
@RestController
//解决跨域问题
@CrossOrigin
public class SpuController {
    @Reference
    private ManageService manageService;

    //spuList?catalog3Id=1 根据三级分类id 查询spu集合
    @RequestMapping("/spuList")
    public List<SpuInfo> getSpuList(SpuInfo spuInfo){
        //调用service实现查询spu列表
        return manageService.getSpuList(spuInfo);
    }
    //从字典宝中获得 销售属性
    @RequestMapping("/baseSaleAttrList")
    public List<BaseSaleAttr> getBaseSaleAttrList(){
       return this.manageService.getBaseSaleAttrList();
    }
    //保存Spu数据
    @RequestMapping("/saveSpuInfo")
    public void saveSpuInfo(@RequestBody SpuInfo spuInfo){
        //调用service保存
      this.manageService.saveSpuInfo(spuInfo);
    }

}
