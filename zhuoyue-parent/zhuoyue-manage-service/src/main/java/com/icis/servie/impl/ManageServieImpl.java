package com.icis.servie.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.icis.mapper.BaseAttrInfoMapper;
import com.icis.mapper.BaseCatalog1Mapper;
import com.icis.mapper.BaseCatalog2Mapper;
import com.icis.mapper.BaseCatalog3Mapper;
import com.icis.pojo.BaseAttrInfo;
import com.icis.pojo.BaseCatalog1;
import com.icis.pojo.BaseCatalog2;
import com.icis.pojo.BaseCatalog3;
import com.icis.service.ManageService;
import org.springframework.beans.factory.annotation.Autowired;

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
}
