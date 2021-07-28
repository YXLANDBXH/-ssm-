package com.xl.service;

import com.xl.domain.Type;

import java.util.List;

/**
 * @author XLong
 * @create 2021-07-17 16:08
 */
public interface TypeService {
    //职位查询
    List<Type> selectAllTypelist();
    //根据id编辑职位信息
    void updateType(Type type);
    //根据id查询
    Type getTypeById(String id);
    //插入数据
    void insertType(Type type);
    //模糊查询
    List<Type> selectTypeLike(Type type);
    //批量删除
    void deleteTypes(String ids[]);
}
