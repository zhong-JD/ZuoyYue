package com.icis.service;

import com.icis.pojo.UserInfo;

import java.util.List;

//用户Servcie接口
public interface UserInfoService {
    /*
    *
    * 方法：List<T> select(T record);
说明：根据实体中的属性值进行查询，查询条件使用等号

方法：T selectByPrimaryKey(Object key);
说明：根据主键字段进行查询，方法参数必须包含完整的主键属性，查询条件使用等号

方法：List<T> selectAll();
说明：查询全部结果，select(null)方法能达到同样的效果

方法：T selectOne(T record);
说明：根据实体中的属性进行查询，只能有一个返回值，有多个结果是抛出异常，查询条件使用等号

方法：int selectCount(T record);
说明：根据实体中的属性查询总数，查询条件使用等号
    *
    * */
    //1.查询所有用户数据
    public List<UserInfo>  findAll();
    //根据条件查询
    public List<UserInfo> findByUserInfo(UserInfo userInfo);
    //模糊查询
    public List<UserInfo> findByUserLike(UserInfo userInfo);
}
