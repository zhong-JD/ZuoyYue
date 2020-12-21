package com.icis.mapper;

import com.icis.pojo.BaseAttrInfo;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface BaseAttrInfoMapper extends Mapper<BaseAttrInfo>{
    //根据三级分类Id获得平台属性和平台属性值
    List<BaseAttrInfo> attrInfoList(String catalog3Id);
}
