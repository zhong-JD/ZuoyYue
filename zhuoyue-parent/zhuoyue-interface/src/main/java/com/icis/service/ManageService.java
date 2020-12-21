package com.icis.service;

import com.icis.pojo.*;

import java.util.List;

//管理服务接口
public interface ManageService {
    //查询一级分类列表
    public List<BaseCatalog1> getCal1();
    //根据一级查询二级分类
    public List<BaseCatalog2> getCal2(BaseCatalog1 catalog1);
    //根据二级分类查询三级分类
    public List<BaseCatalog3> getCal3(BaseCatalog2 catalog2);
    //根据三级分裂  获得平台属性
    public List<BaseAttrInfo> getBaseAttrInfo(BaseCatalog3 catalog3);
    //添加平台属性
    void saveAttrInfo(BaseAttrInfo baseAttrInfo);
    //根据平台属性id 查询平台属性值
    List<BaseAttrValue> getAttrValueList(String attrId);
    //根据三级分类id 获得spu集合
    List<SpuInfo> getSpuList(SpuInfo spuInfo);
    //获得基本销售属性 数据
    List<BaseSaleAttr> getBaseSaleAttrList();
    //保存spu方法
    void saveSpuInfo(SpuInfo spuInfo);
    //根据SpuId获得图片列表
    List<SpuImage> getSpuImageList(SpuImage spuImage);
    //根据三级分类获得平台属性和平台属性值
    List<BaseAttrInfo> attrInfoList(String catalog3Id);

    //根据平台属性获得平台属性值
    //public List<BaseAttrValue> getBaseAttrValues(BaseAttrInfo baseAttrInfo);
}
