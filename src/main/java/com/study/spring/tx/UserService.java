package com.study.spring.tx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;
    @Transactional(rollbackFor = Exception.class)
    public void insertUser(){
        userDao.saveCustomer(1,2);
        System.out.println("插入完成");
        int a=1/0;
    }
}
