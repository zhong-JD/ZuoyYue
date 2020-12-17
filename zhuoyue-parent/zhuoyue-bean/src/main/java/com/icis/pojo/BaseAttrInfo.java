package com.icis.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.List;

//基本属性
@Data
public class BaseAttrInfo implements Serializable{
    @Id
    @Column
    private String id;
    @Column
    private String attrName;
    @Column
    private String catalog3Id;
    //构建一个平台属性值集合  attrValueList  通用mapper 如果实体类对应的字段和数据库没有对应关系
    // 告诉通用mapper 这个字段是临时的
    @Transient
    private List<BaseAttrValue> attrValueList;
}
