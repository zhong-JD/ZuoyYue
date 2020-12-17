package com.icis.pojo;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

//基本属性值
@Data
public class BaseAttrValue implements Serializable{
    @Id
    @Column
    private String id;
    @Column
    private String valueName;
    @Column
    private String attrId;
}
