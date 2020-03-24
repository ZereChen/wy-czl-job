//package com.czl.user.dao;
//
//import com.czl.entity.user.UserEntity;
//import com.czl.req.user.UserQueryReq;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.List;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class UserDAOTest {
//
//    @Autowired
//    private UserDAO userDAO;
//
//    @Test
//    public void findUsers() throws Exception {
//        List<UserEntity> userEntityList = userDAO.findUsers(new UserQueryReq());
//        System.out.println(userEntityList);
//    }
//
//}