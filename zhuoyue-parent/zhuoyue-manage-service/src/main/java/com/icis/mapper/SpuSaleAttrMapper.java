package com.icis.mapper;

import com.icis.pojo.SpuSaleAttr;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

//Spu基本销售属性Mapper
public interface SpuSaleAttrMapper extends Mapper<SpuSaleAttr>{
    //根据spuId 查询销售属性和销售属性值
    List<SpuSaleAttr> getSpuSaleAttrList(String spuId);
}
