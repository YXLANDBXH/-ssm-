package com.xl.service;

import com.xl.domain.Type;
import com.xl.domain.TypeExample;
import com.xl.mapper.TypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author XLong
 * @create 2021-07-17 16:09
 */
@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeMapper typeMapper;

    @Override
    public List<Type> selectAllTypelist() {
        return this.typeMapper.selectByExample(null);
    }

    @Override
    public void updateType(Type type) {
        this.typeMapper.updateByPrimaryKeySelective(type);
    }

    @Override
    public Type getTypeById(String id) {
        return this.typeMapper.selectByPrimaryKey(Integer.parseInt(id));
    }

    @Override
    public void insertType(Type type) {
        this.typeMapper.insertSelective(type);
    }

    @Override
    public List<Type> selectTypeLike(Type type) {
        TypeExample typeExample = new TypeExample();
        TypeExample.Criteria criteria = typeExample.createCriteria();
        if (type!=null) {
            if (type.getName()!=null && !type.getName().equals("")) {
                criteria.andNameLike(type.getName());
            }
        }
        return this.typeMapper.selectByExample(typeExample);
    }

    @Override
    public void deleteTypes(String[] ids) {
        for (int i = 0;i < ids.length; i++) {
            this.typeMapper.deleteByPrimaryKey(Integer.parseInt(ids[i]));
        }
    }
}



























