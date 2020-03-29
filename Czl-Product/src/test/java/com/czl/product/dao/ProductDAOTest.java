//package com.czl.product.dao;
//
//import com.czl.entity.product.ProductEntity;
//import com.czl.req.product.ProdQueryReq;
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
//}