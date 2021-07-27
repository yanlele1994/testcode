package com.bjpowernode.service.impl;

import com.bjpowernode.dao.TypeDao;
import com.bjpowernode.dao.impl.TypeDaoImpl;
import com.bjpowernode.pojo.Type;
import com.bjpowernode.service.TypeService;

import java.util.List;

public class TypeServiceImpl implements TypeService {

    private TypeDao typeDao = new TypeDaoImpl();

    public List<Type> getAll() {
        return typeDao.getAll();
    }
}
