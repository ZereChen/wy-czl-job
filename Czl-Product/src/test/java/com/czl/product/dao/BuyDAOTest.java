//package com.czl.product.dao;
//
//import com.czl.entity.buy.BuyEntity;
//import com.czl.req.buy.BuyQueryReq;
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.util.List;
//
//import static org.junit.Assert.*;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class BuyDAOTest {
//    @Autowired
//    private BuyDAO buyDAO;
//
//    @Test
//    public void testFindBuy() throws Exception {
//
//        BuyQueryReq buyQueryReq = new BuyQueryReq();
//        buyQueryReq.setUserId("USERee31ea93e5ac4f7ea0d8b044dff641ec");
//        buyQueryReq.setProductId("5");
//        List<BuyEntity> result = buyDAO.findBuys(buyQueryReq);
//        Assert.assertEquals(2, result.size());
//    }
//
//}