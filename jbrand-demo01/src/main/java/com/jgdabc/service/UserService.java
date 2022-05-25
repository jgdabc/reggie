package com.jgdabc.service;

import com.jgdabc.mapper.UserMapper;
import com.jgdabc.pojo.User;
import com.jgdabc.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

public class UserService {
    public User login(String username,String password)
    {

        SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();
        SqlSession sqlSession = factory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        //调用方法
        User user = mapper.select(username, password);
        sqlSession.close();


        return user;

    }
//    service里面的注册功能
    public boolean register(User user)
    {
        SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();
        SqlSession sqlSession = factory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User u = mapper.selectByUsername(user.getUsername());
        if (u==null)
        {
            mapper.add(user);
            sqlSession.commit();

        }
        sqlSession.close();
        return u == null;


    }

}
