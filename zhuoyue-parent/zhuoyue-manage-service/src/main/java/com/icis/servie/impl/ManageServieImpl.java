package com.icis.servie.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.icis.mapper.*;
import com.icis.pojo.*;
import com.icis.service.ManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//管理接口实现类
@Service
public class ManageServieImpl implements ManageService{
    //注入对应的mapper
    @Autowired
    private BaseCatalog1Mapper baseCatalog1Mapper;
    @Autowired
    private BaseCatalog2Mapper baseCatalog2Mapper;
    @Autowired
    private BaseCatalog3Mapper baseCatalog3Mapper;
    @Autowired
    private BaseAttrInfoMapper baseAttrInfoMapper;

    //注入SpuMapper
    @Autowired
    private SpuMapper spuMapper;
    //销售属性Mapper
    @Autowired
    private SpuSaleAttrMapper spuSaleAttrMapper;
    //销售属性值Mapper
    @Autowired
    private SpuSaleAttrValueMapper spuSaleAttrValueMapper;
    //spuImageMapper
    @Autowired
    private SpuImageMapper spuImageMapper;

    //注入平台属性值mapper
    @Autowired
    private BaseAttrValueMapper baseAttrValueMapper;

    //注入销售属性Mapper
    @Autowired
    private BaseSaleAttrMapper baseSaleAttrMapper;

    //注入sku相关的mapper
    @Autowired
    private SkuInfoMapper skuInfoMapper;
    @Autowired
    private SkuSaleAttrValueMapper skuSaleAttrValueMapper;
    @Autowired
    private SkuAttrValueMapper skuAttrValueMapper;
    @Autowired
    private  SkuImageMapper skuImageMapper;


    @Override
    public List<BaseCatalog1> getCal1() {
        return this.baseCatalog1Mapper.selectAll();
    }

    @Override
    public List<BaseCatalog2> getCal2(BaseCatalog1 catalog1) {
        //创建二级分类对象
        BaseCatalog2 catalog2=new BaseCatalog2();
        //设置一级分类id
        catalog2.setCatalog1Id(catalog1.getId());
        //二级分裂根据一级分类查询
        return this.baseCatalog2Mapper.select(catalog2);
    }

    @Override
    public List<BaseCatalog3> getCal3(BaseCatalog2 catalog2) {
        BaseCatalog3 catalog3=new BaseCatalog3();
        //设置条件  二级分类id
        catalog3.setCatalog2Id(catalog2.getId());
        return this.baseCatalog3Mapper.select(catalog3);
    }

    @Override
    public List<BaseAttrInfo> getBaseAttrInfo(BaseCatalog3 catalog3) {
        BaseAttrInfo baseArriInfo=new BaseAttrInfo();
        //设置三级分类id
        baseArriInfo.setCatalog3Id(catalog3.getId());

        return this.baseAttrInfoMapper.select(baseArriInfo);
    }
    //添加平台属性实现
    @Override
    @Transactional
    public void saveAttrInfo(BaseAttrInfo baseAttrInfo) {
        //如果有id 或者id 不为空  做修改操作
        if(baseAttrInfo.getId()!=null && baseAttrInfo.getId().length()>0){
            //执行修改平台属性
            this.baseAttrInfoMapper.updateByPrimaryKeySelective(baseAttrInfo);
        }else {
            //如果没有id 做添加操作
            //1.添加平台属性
            this.baseAttrInfoMapper.insertSelective(baseAttrInfo);
        }
        //清空当前平台属性的平台属性值
        BaseAttrValue delBaseArrtValue=new BaseAttrValue();
        delBaseArrtValue.setAttrId(baseAttrInfo.getId());
        this.baseAttrValueMapper.delete(delBaseArrtValue);
        //2.添加平台属性值
        //2.1 获得对应的平台属性值
        List<BaseAttrValue> attrValueList = baseAttrInfo.getAttrValueList();
        //2.2遍历集合  添加数据
        if(attrValueList!=null && attrValueList.size()>0){
            for (BaseAttrValue baseAttrValue : attrValueList) {
                //2.3 给平台属性值设置对应的平台属性
                baseAttrValue.setAttrId(baseAttrInfo.getId());
                baseAttrValueMapper.insertSelective(baseAttrValue);
            }
        }


    }

    @Override
    public List<BaseAttrValue> getAttrValueList(String attrId) {
        BaseAttrValue baseAttrValue=new BaseAttrValue();
        //设置条件
        baseAttrValue.setAttrId(attrId);
        //构建一个平台属性对象
        return this.baseAttrValueMapper.select(baseAttrValue);
    }
    //获得spu集合实现方法
    @Override
    public List<SpuInfo> getSpuList(SpuInfo spuInfo) {
        return this.spuMapper.select(spuInfo);
    }

    @Override
    public List<BaseSaleAttr> getBaseSaleAttrList() {
        //调用mapper实现数据的查询
       return this.baseSaleAttrMapper.selectAll();

    }
    //实现保存Spu
    @Override
    @Transactional
    public void saveSpuInfo(SpuInfo spuInfo) {
       //1.判断spuInfo
        if(spuInfo!=null){
            //2.保存spu
            this.spuMapper.insertSelective(spuInfo);
            //3.获得销售属性列表  保存
            List<SpuSaleAttr> spuSaleAttrList = spuInfo.getSpuSaleAttrList();
            if(spuSaleAttrList!=null && spuSaleAttrList.size()>0){
                for (SpuSaleAttr spuSaleAttr : spuSaleAttrList) {
                    //保存销售属性
                    //设置spuId
                    spuSaleAttr.setSpuId(spuInfo.getId());
                    //保存
                    this.spuSaleAttrMapper.insertSelective(spuSaleAttr);
                    //获得当前销售属性的销售属性值
                    List<SpuSaleAttrValue> spuSaleAttrValueList = spuSaleAttr.getSpuSaleAttrValueList();
                    if(spuSaleAttrValueList!=null && spuSaleAttrValueList.size()>0){
                        for (SpuSaleAttrValue spuSaleAttrValue : spuSaleAttrValueList) {
                            //设置SpuId
                            spuSaleAttrValue.setSpuId(spuInfo.getId());
                            //保存
                            this.spuSaleAttrValueMapper.insertSelective(spuSaleAttrValue);
                        }
                    }
                }
            }

            //3.1 保存完销售属性  根据销售属性获得销售属性值  保存
            //4.获得spu图片列表 保存  注意  设置SpuId
            List<SpuImage> spuImageList = spuInfo.getSpuImageList();
            if(spuImageList!=null && spuImageList.size()>0){
                for (SpuImage spuImage : spuImageList) {
                    //设置SpuId
                    spuImage.setSpuId(spuInfo.getId());
                    //添加数据
                    this.spuImageMapper.insertSelective(spuImage);
                }
            }

        }else {
            return;
        }
    }

    @Override
    public List<SpuImage> getSpuImageList(SpuImage spuImage) {
        return this.spuImageMapper.select(spuImage);
    }
    //根据三级分类Id 获得平台属性和平台属性值
    @Override
    public List<BaseAttrInfo> attrInfoList(String catalog3Id) {
        return baseAttrInfoMapper.attrInfoList(catalog3Id);
    }
    //实现  根据SpuId 查询销售属性和销售属性值
    @Override
    public List<SpuSaleAttr> getSpuSaleAttrList(String spuId) {
        return spuSaleAttrMapper.getSpuSaleAttrList(spuId);
    }
    //保存sku数据
    // 存储哪些业务需要?  1. 老项目或者传统 erp oa 进销存 2. 业务单一 一个业务 需要执行sql 60行
    @Override
    @Transactional
    public void saveSkuInfo(SkuInfo skuInfo) {

        if(skuInfo!=null){
            //1. 保存sku
            this.skuInfoMapper.insertSelective(skuInfo);
            //2. 根据sku 获得平台属性值 保存
            List<SkuAttrValue> skuAttrValueList = skuInfo.getSkuAttrValueList();
            if(skuAttrValueList!=null && skuAttrValueList.size()>0){
                //2.1 循环遍历  添加数据
                for (SkuAttrValue skuAttrValue : skuAttrValueList) {
                    //设置skuId
                    skuAttrValue.setSkuId(skuInfo.getId());
                    this.skuAttrValueMapper.insertSelective(skuAttrValue);
                }
            }

            //3.根据sku 获得销售属性值  保存
            List<SkuSaleAttrValue> skuSaleAttrValueList = skuInfo.getSkuSaleAttrValueList();
            if(skuSaleAttrValueList!=null && skuSaleAttrValueList.size()>0){
                for (SkuSaleAttrValue skuSaleAttrValue : skuSaleAttrValueList) {
                    //设置skuId
                    skuSaleAttrValue.setSkuId(skuInfo.getId());
                    //保存销售属性值
                    this.skuSaleAttrValueMapper.insertSelective(skuSaleAttrValue);
                }
            }

            //4. 根据sku 获得sku图片集合  保存
            List<SkuImage> skuImageList = skuInfo.getSkuImageList();
            if(skuImageList!=null && skuImageList.size()>0){
                //遍历 保存数据
                for (SkuImage skuImage : skuImageList) {
                    //设置skuId
                    skuImage.setSkuId(skuInfo.getId());
                    this.skuImageMapper.insertSelective(skuImage);
                }
            }

        }


    }

}
