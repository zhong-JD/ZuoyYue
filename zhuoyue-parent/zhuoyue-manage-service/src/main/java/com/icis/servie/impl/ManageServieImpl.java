package com.icis.servie.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.icis.mapper.*;
import com.icis.pojo.*;
import com.icis.service.ManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

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

    //注入平台属性值mapper
    @Autowired
    private BaseAttrValueMapper baseAttrValueMapper;

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

}
