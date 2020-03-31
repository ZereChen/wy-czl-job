//package com.czl.product.dao;
//
//import com.czl.entity.product.ProductEntity;
//import com.czl.enumeration.product.ProdStateEnum;
//import com.czl.req.product.ProdQueryReq;
//import com.czl.req.product.ProdQueryReqForLogin;
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
//public class ProductDAOTest {
//    @Autowired
//    private ProductDAO productDAO;
//
//    @Test
//    public void testFindUsedCategory() throws Exception {
//        ProdQueryReq prodQueryReq = new ProdQueryReq();
//        List<ProductEntity> result = productDAO.findProducts(prodQueryReq);
//        Assert.assertEquals(19,result.size());
//    }
//
//    @Test
//    public void testFindOrdersForBuyer() throws Exception {
//        ProdQueryReqForLogin prodQueryReqForLogin = new ProdQueryReqForLogin();
//        prodQueryReqForLogin.setUserId("USERee31ea93e5ac4f7ea0d8b044dff641ec");
//        prodQueryReqForLogin.setProdStateCode(ProdStateEnum.OPEN.getCode());
//        List<ProductEntity> result = productDAO.findProductsForBuyer(prodQueryReqForLogin);
//        Assert.assertEquals(18,result.size());
//    }
//}